package org.example.Entities.Creatures;

import org.example.Entities.StaticEntities.EmptyField;
import org.example.Logic.Map;
import org.example.Logic.SearchUtilityClass;

import java.util.List;
import java.util.Random;

public class Herbivore extends Creature {
    public Herbivore() {
        renderIcon = '@';
        colour = "\u001B[36m";
    }


    @Override
    public void makeMove(Map map) {
        SearchUtilityClass searchUtilityClass;
        Random random = new Random();
        searchUtilityClass = new SearchUtilityClass(map, this);
        List<Integer> mostValuableMoves = searchUtilityClass.findMostValuableMoves();
        if (mostValuableMoves.isEmpty()) {
            return;
        }
        int nextPosition = mostValuableMoves.get(random.nextInt(mostValuableMoves.size()));
        map.setMapField(currentPosition, new EmptyField());
        map.setMapField(nextPosition, this);
        currentPosition=nextPosition;
       /* map.setMapOfInteractionalObjectsFiled(nextPosition, this);//переделать
        map.getMapOfInteractionalObjects().remove(currentPosition);*/
    }

    @Override
    public void destroy() {
//TODO impl
    }
}
