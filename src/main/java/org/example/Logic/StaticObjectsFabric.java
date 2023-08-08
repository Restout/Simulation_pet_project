package org.example.Logic;

import org.example.Entities.Entity;
import org.example.Entities.StaticEntities.Rock;
import org.example.Entities.StaticEntities.Tree;

import java.util.Random;

public class StaticObjectsFabric {
    static public Entity createEntity() {
        Random random = new Random();
        int entityValue = random.nextInt(1, 3);
        if (entityValue == 1) {
            return new Tree();
        } else {
            return new Rock();
        }
    }
}
