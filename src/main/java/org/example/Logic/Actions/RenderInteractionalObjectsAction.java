package org.example.Logic.Actions;

import org.example.Entities.Creatures.Herbivore;
import org.example.Entities.Creatures.Predator;
import org.example.Entities.StaticEntities.EmptyField;
import org.example.Entities.StaticEntities.Grass;
import org.example.Logic.Map;

import java.util.Random;

public class RenderInteractionalObjectsAction extends Action {
    private final static Random random=new Random();
    private static final int NUMBER_OF_HERBIVORES_OBJECTS = 4;
    private static final int NUMBER_OF_PREDATORS_OBJECTS = 4;
    private static final int NUMBER_OF_GRASS_OBJECTS = 8;

    public static void renderInteractionalObjects(Map map, int squareOfMap) {
        renderGrass(map, squareOfMap);
        renderHerbivores(map, squareOfMap);
        renderPredators(map, squareOfMap);
    }

    private static void renderGrass(Map map, int squareOfMap) {
        int index;
        for (int i = 0; i < NUMBER_OF_GRASS_OBJECTS; i++) {
            index = random.nextInt(squareOfMap);
            while (map.getMapField(index).getClass() != EmptyField.class) {
                index = random.nextInt(squareOfMap);
            }
            map.setMapField(index, new Grass());
        }
    }

    private static void renderHerbivores(Map map, int squareOfMap) {
        int index;
        for (int i = 0; i < NUMBER_OF_HERBIVORES_OBJECTS; i++) {
            index = random.nextInt(squareOfMap);
            while (map.getMapField(index).getClass() != EmptyField.class) {
                index = random.nextInt(squareOfMap);
            }
            map.setMapField(index, new Herbivore());
        }
    }

    private static void renderPredators(Map map, int squareOfMap) {
        int index;
        for (int i = 0; i < NUMBER_OF_PREDATORS_OBJECTS; i++) {
            index = random.nextInt(squareOfMap);
            while (map.getMapField(index).getClass() != EmptyField.class) {
                index = random.nextInt(squareOfMap);
            }
            map.setMapField(index, new Predator());
        }
    }

}
