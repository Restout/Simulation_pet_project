package org.example.Logic;

import org.example.Entities.Entity;
import org.example.Entities.Interactional;

import java.util.HashMap;

public class Map {
    private final HashMap<Integer, Entity> map = new HashMap(64);
    private final HashMap<Integer, Interactional> mapOfInteractionalObjects = new HashMap<>();

    public Map() {
for(int i=0;i<64;i++){
    map.put(i,null);
}
    }

    public void setMapField(int filedIndex, Entity value) {
        map.put(filedIndex, value);
    }

    public void setMapOfInteractionalObjectsFiled(int filedIndex, Interactional value) {
        mapOfInteractionalObjects.put(filedIndex, value);
    }

    public Entity getMapField(int filedIndex) {
        return map.get(filedIndex);
    }

    public HashMap<Integer, Interactional> getMapOfInteractionalObjects() {
        return mapOfInteractionalObjects;
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
            System.out.print(map.get(key).getRenderIcon()+" ");
        }
    }

}
