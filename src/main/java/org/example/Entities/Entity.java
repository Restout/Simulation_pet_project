package org.example.Entities;

import org.example.Logic.Map;

public abstract class Entity {
    protected String renderIcon = "🟫";
    protected int currentPosition = 0;
    public String getRenderIcon() {
        return renderIcon;
    }
    public int getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(int currentPosition) {
        this.currentPosition = currentPosition;
    }

    public void makeMove(Map map) {
    }
}
