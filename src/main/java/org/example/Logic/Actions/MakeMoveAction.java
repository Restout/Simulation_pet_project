package org.example.Logic.Actions;

import org.example.Entities.Entity;
import org.example.Entities.Interactional;
import org.example.Logic.Map;

public class MakeMoveAction extends Action {
    public static void action(Map map) {
        for (Entity entity : map.getMap().values()) {
                entity.makeMove(map);
        }

    }
}
