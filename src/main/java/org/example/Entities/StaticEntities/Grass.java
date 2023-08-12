package org.example.Entities.StaticEntities;

import org.example.Entities.Entity;
import org.example.Logic.Map;

public class Grass extends Entity {
    int healthPoints;
    public Grass() {
        healthPoints = 100;
        renderIcon = "\uD83C\uDF31";
    }
    @Override
    public void makeMove(Map map) {
        if (healthPoints != 200) {
            healthPoints += 50;
        }
        if(healthPoints==200){
            renderIcon = "\uD83C\uDF3B";
        }
    }
}
