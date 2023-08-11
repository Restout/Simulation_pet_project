package org.example.Logic.Actions;

import org.example.Entities.Creatures.Herbivore;
import org.example.Entities.Creatures.Predator;
import org.example.Entities.Entity;
import org.example.Entities.StaticEntities.EmptyField;
import org.example.Entities.StaticEntities.Grass;
import org.example.Logic.Map;

import java.util.Random;

public class RenderInteractionalObjectsAction extends Action {
    private final static Random random = new Random();
    private static final int NUMBER_OF_HERBIVORES_OBJECTS = 4;
    private static final int NUMBER_OF_PREDATORS_OBJECTS = 4;
    private static final int NUMBER_OF_GRASS_OBJECTS = 8;

    public void action(Map map) {
        renderGrass(map);
        renderHerbivores(map);
        renderPredators(map);
    }

    private static void renderObjects(Map map, int numberOfObjects, Class<? extends Entity> objectType) {
        int index;
        int squareOfMap = map.getMap().size();

        for (int i = 0; i < numberOfObjects; i++) {
            index = random.nextInt(squareOfMap);

            while (!map.getMapField(index).getClass().equals(EmptyField.class)) {
                index = random.nextInt(squareOfMap);
            }
            Entity object = null;

            if (objectType.equals(Grass.class)) {
                object = new Grass();
            } else if (objectType.equals(Herbivore.class)) {
                object = new Herbivore();
            } else if (objectType.equals(Predator.class)) {
                object = new Predator();
            }

            if (object != null) {
                object.setCurrentPosition(index);
                map.setMapField(index, object);
            }
        }
    }

    private static void renderGrass(Map map) {
        renderObjects(map, NUMBER_OF_GRASS_OBJECTS, Grass.class);
    }

    private static void renderHerbivores(Map map) {
        renderObjects(map, NUMBER_OF_HERBIVORES_OBJECTS, Herbivore.class);
    }

    private static void renderPredators(Map map) {
        renderObjects(map, NUMBER_OF_PREDATORS_OBJECTS, Predator.class);
    }


}
