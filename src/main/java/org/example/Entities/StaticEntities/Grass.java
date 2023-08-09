package org.example.Entities.StaticEntities;

import org.example.Entities.Entity;
import org.example.Entities.Interactional;
import org.example.Logic.Map;

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
    public void makeMove(Map map) {
        if (healthPoints != 200) {
            healthPoints += 50;
        }
        if(healthPoints==200){
            renderIcon='A';
        }
    }

    @Override
    public void destroy() {
//TODO impl
    }
}
