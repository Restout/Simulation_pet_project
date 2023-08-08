package org.example.Entities.StaticEntities;

import org.example.Entities.Entity;
import org.example.Entities.Interactional;

public class Grass extends Entity implements Interactional {
    public Grass() {
        healthPoints = 100;
        renderIcon = '#';
        colour = "\u001B[33m";
    }


    public boolean canBeEaten() {
        return healthPoints == 200;
    }

    @Override
    public void makeMove() {
        if (healthPoints != 200) {
            healthPoints += 50;
        }
    }

    @Override
    public void destroy() {
//TODO impl
    }
}
