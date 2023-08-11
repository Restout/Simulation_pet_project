package org.example.Logic;

import org.example.Entities.Entity;
import org.example.Entities.StaticEntities.EmptyField;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BFSearchUtilityClass {
    Map map;
    Deque<Integer> fieldsToSee;
    Deque<Integer> wasSeenFiled;
    HashMap<Integer, Integer> path;

    public BFSearchUtilityClass(Map map) {
        this.map = map;
        fieldsToSee = new ArrayDeque<>();
        wasSeenFiled = new ArrayDeque<>();
        path = new HashMap<>();
    }

    public Stack<Integer> findBestPathBFS(Entity startEntity, Class findClass) {
        int cur = startEntity.getCurrentPosition();
        Set<Integer> fields = getEmptyFields(cur);
        fieldsToSee.addAll(fields);
        for (Integer x : fields) {
            path.put(x, cur);
        }
        while (!fieldsToSee.isEmpty()) {
            cur = fieldsToSee.pollFirst();
            if (isClassNear(cur, findClass).isPresent()) {
                break;
            }
            wasSeenFiled.add(cur);
            fields = getEmptyFields(cur)
                    .stream()
                    .filter(x -> !wasSeenFiled
                            .contains(x))
                    .collect(Collectors
                            .toSet());
            for (Integer x : fields) {
                path.put(x, cur);
            }
            fieldsToSee.addAll(fields);
        }
        Stack<Integer> smallestWay = new Stack<>();
        while (path.containsKey(cur)) {
            smallestWay.add(cur);
            cur = path.get(cur);
        }
        return smallestWay;
    }

    public Optional<Integer> isClassNear(int currentPosition, Class findClass) {
        return getAvailableFields(currentPosition)
                .filter(x -> map.getMapField(x) != null && map.getMapField(x).getClass().equals(findClass))
                .toList()
                .stream()
                .findFirst();
    }

    public Set<Integer> getEmptyFields(int currentPosition) {
        return getAvailableFields(currentPosition)
                .filter(x -> map.getMapField(x) != null && map.getMapField(x).getClass().equals(EmptyField.class))
                .collect(Collectors.toSet());
    }

    private Stream<Integer> getAvailableFields(int currentPosition) {
        int size = map.getMap().size();
        int mapWidth = (int) Math.sqrt(size);
        return Stream.of(currentPosition + 1,
                currentPosition - 1,
                currentPosition + mapWidth,
                currentPosition - mapWidth,
                currentPosition + 1 + mapWidth,
                currentPosition + 1 - mapWidth,
                currentPosition - 1 + mapWidth,
                currentPosition - 1 - mapWidth);
    }
}