package org.example.Logic.Actions;

import org.example.Entities.Entity;
import org.example.Entities.StaticEntities.EmptyField;
import org.example.Logic.Map;
import org.example.Logic.StaticObjectsFabric;

import java.util.Random;

public class RenderStaticObjectsAction extends Action {
    private static final int NUMBER_OF_STATIC_OBJECTS = 32;

    public void action(Map map) {
        Random random = new Random();
        int index;
        Entity entity;
        int squareOfMap = map.getMap().size();
        for (int i = 0; i < NUMBER_OF_STATIC_OBJECTS; i++) {
            index = random.nextInt(squareOfMap);
            while (map.getMapField(index).getClass() != EmptyField.class) {
                index = random.nextInt(squareOfMap);
            }
            entity = StaticObjectsFabric.createEntity();
            map.setMapField(index, entity);
        }
    }
}
