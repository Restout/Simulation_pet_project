package org.example.Entities.Creatures;

import org.example.Entities.StaticEntities.EmptyField;
import org.example.Logic.BFSearchUtilityClass;
import org.example.Logic.Map;

import java.util.Optional;
import java.util.Stack;

public class Predator extends Creature {
    Stack<Integer> path;
    public Predator() {
        renderIcon = "\uD83E\uDD8A";
    }
    @Override
    public void makeMove(Map map) {
        BFSearchUtilityClass searchUtilityClass = new BFSearchUtilityClass(map);
        Optional<Integer> herbivoreNear;
        if (path == null || path.isEmpty()) {
            path = searchUtilityClass.findBestPathBFS(this, Herbivore.class);
        }
        if (!map.getMapField(path.peek()).getClass().equals(EmptyField.class) && !map.getMapField(path.peek()).getClass().equals(Herbivore.class)) {
            path.clear();
            path.add(searchUtilityClass.getEmptyFields(currentPosition).stream().toList().get(0));
        }
        herbivoreNear = searchUtilityClass.isClassNear(currentPosition, Herbivore.class);
        herbivoreNear.ifPresent(integer -> path.add(integer));
        int nextPosition = path.pop();
        map.setMapField(currentPosition, new EmptyField());
        map.setMapField(nextPosition, this);
        currentPosition = nextPosition;
    }
}
