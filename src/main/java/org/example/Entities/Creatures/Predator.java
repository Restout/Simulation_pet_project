package org.example.Entities.Creatures;

public class Predator extends Creature {
    private final int attackDamage = 25;

    public Predator() {
        renderIcon = '&';
        colour = "\u001B[31m";

    }
@Override
public void makeMove(){

}
    @Override
    public void destroy() {
//TODO impl
    }
}
