package org.example.Entities;

public abstract class Entity {
    protected int moveSpeed = 0;
    protected int healthPoints = 1;
    protected char renderIcon = '*';
    protected String colour = "\u001B[0m";

    public int getMoveSpeed() {
        return moveSpeed;
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public char getRenderIcon() {
        return renderIcon;
    }

    public String getColour() {
        return colour;
    }

    public void setMoveSpeed(int moveSpeed) {
        this.moveSpeed = moveSpeed;
    }

    public void setHealthPoints(int healthPoints) {
        this.healthPoints = healthPoints;
    }

}
