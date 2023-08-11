package org.example.Entities.Creatures;

import org.example.Entities.StaticEntities.EmptyField;
import org.example.Entities.StaticEntities.Grass;
import org.example.Logic.BFSearchUtilityClass;
import org.example.Logic.Map;

import java.util.Optional;
import java.util.Stack;

public class Herbivore extends Creature {
    Stack<Integer> path;
    public Herbivore() {
       renderIcon = "\uD83E\uDD86";
    }


    @Override
    public void makeMove(Map map) {
        BFSearchUtilityClass searchUtilityClass = new BFSearchUtilityClass(map);
        Optional<Integer> grassNear;
        if (path == null || path.isEmpty()) {
            path = searchUtilityClass.findBestPathBFS(this, Grass.class);
            if(path.isEmpty()){
                return;
            }
        }
        if (!map.getMapField(path.peek()).getClass().equals(EmptyField.class) && !map.getMapField(path.peek()).getClass().equals(Grass.class)) {
            path.clear();
            path.add(searchUtilityClass.getEmptyFields(currentPosition).stream().toList().get(0));
        }
        grassNear = searchUtilityClass.isClassNear(currentPosition, Grass.class);

        grassNear.ifPresent(integer -> path.add(integer));

        int nextPosition = path.pop();
        map.setMapField(currentPosition, new EmptyField());
        map.setMapField(nextPosition, this);
        currentPosition = nextPosition;

    }
}
