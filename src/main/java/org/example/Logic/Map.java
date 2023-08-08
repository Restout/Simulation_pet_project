package org.example.Logic;

import org.example.Entities.Entity;

import java.util.HashMap;

public class Map<T extends Entity> {
    private final HashMap<Integer, Entity> map = new HashMap();

    public Map() {

    }

    public void setMapField(int filedIndex, T value) {
        map.put(filedIndex, value);
    }

    public Entity getMapField(int filedIndex) {
        return map.get(filedIndex);
    }

    public HashMap<Integer, Entity> getMap() {
        return map;
    }

    public void printMap() {
        int mapSquare = map.size();
        int mapEdge = (int) Math.sqrt(mapSquare);
        for (Integer key : map.keySet()) {
            if (key % mapEdge == 0) {
                System.out.println();
            }
            System.out.print(" " + map.get(key).getColour() + map.get(key).getRenderIcon());
        }
    }

}
