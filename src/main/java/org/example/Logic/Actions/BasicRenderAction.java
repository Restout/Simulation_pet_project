package org.example.Logic.Actions;

import org.example.Entities.StaticEntities.EmptyField;
import org.example.Logic.Map;

public class BasicRenderAction extends Action{
    public void action(Map map) {
        for (int i = 0; i < map.getMap().size(); i++) {
            map.setMapField(i, new EmptyField());
        }
    }
}
