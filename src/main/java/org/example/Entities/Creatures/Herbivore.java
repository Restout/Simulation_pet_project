package org.example.Entities.Creatures;

public class Herbivore extends Creature {
    public Herbivore() {
        renderIcon = '@';
        colour = "\u001B[36m";

    }

    @Override
    public void makeMove() {

    }

    @Override
    public void destroy() {
//TODO impl
    }
}
