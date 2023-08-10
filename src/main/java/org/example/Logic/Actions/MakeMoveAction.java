package org.example.Logic.Actions;

import org.example.Entities.Entity;
import org.example.Logic.Map;

import java.util.ArrayList;
import java.util.List;

public class MakeMoveAction extends Action {
    public static void action(Map map) {
        List<Entity> madeMove=new ArrayList<>();//переделать в трисет
        for (Entity entity : map.getMap().values()) {
            if(!madeMove.contains(entity)) {
                entity.makeMove(map);
                madeMove.add(entity);
            }
        }

    }
}
