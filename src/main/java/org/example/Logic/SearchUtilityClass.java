package org.example.Logic;

import org.example.Entities.Creatures.Herbivore;
import org.example.Entities.Creatures.Predator;
import org.example.Entities.Entity;
import org.example.Entities.StaticEntities.EmptyField;
import org.example.Entities.StaticEntities.Grass;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class SearchUtilityClass {
    Entity currentEntity;
    private final HashMap<Integer, Entity> map;
    private final HashMap<Integer, Entity> availableMovesMap;

    private final HashMap<Integer, Integer> priorityOfFieldMap;

    public SearchUtilityClass(Map map, Entity findEntity) {
        currentEntity = findEntity;
        this.map = map.getMap();
        availableMovesMap = new HashMap<>();
        priorityOfFieldMap = new HashMap<>();
    }

    public List<Integer> findMostValuableMoves() {
        findAvailableMoves();
        setPriority();
        if (priorityOfFieldMap.isEmpty()) {
            return new ArrayList<>();
        }
        int maxValue = priorityOfFieldMap
                .entrySet()
                .stream()
                .max(Comparator.comparingInt(Entry::getValue))
                .get().getValue();//обработать Optional Empty
        return priorityOfFieldMap
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue() == maxValue)
                .collect(Collectors
                        .toMap(entry -> entry.getKey(), entry -> entry.getValue()))
                .keySet()
                .stream()
                .toList();
    }

    private void findAvailableMoves() {
        Entity entity;
        for (int i = 0; i < map.size(); i++) {
            entity = map.get(i);
            if (checkIsFieldReachableOnMap(i, currentEntity.getCurrentPosition())) {
                availableMovesMap.put(i, entity);
            }
        }
    }

    private boolean checkIsFieldReachableOnMap(int fieldIndex, int currentPosition) {
        Class fieldClass = map.get(fieldIndex).getClass();
        int size = map.size();
        int mapWidth = (int) Math.sqrt(size);
        if (fieldClass.equals(Grass.class) || fieldClass.equals(Herbivore.class) || fieldClass.equals(EmptyField.class)) {
            int deltaPosition = Math.abs(currentPosition - fieldIndex);
            if (deltaPosition == 1 && currentPosition / mapWidth == fieldIndex / mapWidth) {//если сбоку ошибка при переходе на строку
                return true;
            }
            if (deltaPosition == mapWidth) {//если сверху снизу
                return true;
            }
            if (Math.abs(currentPosition / mapWidth - fieldIndex / mapWidth) == 1) {
                if (deltaPosition == mapWidth + 1) {//ecли по диагонали
                    return true;

                }
                if (deltaPosition == mapWidth - 1) {//если по другой диагонали
                    return true;

                }
            }
        }
        return false;
    }

    private void setPriority() {
        for (int i : availableMovesMap.keySet()) {
            Class fieldClass = availableMovesMap.get(i).getClass();
            if (fieldClass.equals(EmptyField.class)) {
                priorityOfFieldMap.put(i, 1);
            }
            if (fieldClass.equals(Herbivore.class) && currentEntity.getClass().equals(Predator.class)) {
                priorityOfFieldMap.put(i, 2);
            }
            if (fieldClass.equals(Grass.class) && currentEntity.getClass().equals(Herbivore.class)) {
                priorityOfFieldMap.put(i, 2);
            }
        }
    }
}
