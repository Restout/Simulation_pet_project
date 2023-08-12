package org.example.Logic;

import org.example.Logic.Actions.*;

import java.util.Arrays;
import java.util.List;

public class Simulation extends Thread {
    private final Map map;
    private boolean paused = false;
    List<Action> initActions = Arrays.asList(new BasicRenderAction(), new RenderStaticObjectsAction(), new RenderInteractionalObjectsAction());
    List<Action> inTurnActions = Arrays.asList(new MakeMoveAction());

    public Simulation(int sizeOfMap) {
        map = new Map(sizeOfMap);
    }

    public void creatMap() {
        for (Action action : initActions) {
            action.action(map);
        }
    }

    public void renderMap() {
        map.printMap();
    }

    @Override
    public void run() {
        creatMap();
        while (!paused) {
            renderMap();
            try {
                Thread.sleep(1500);
            } catch (Exception e) {
                System.out.println(e);
            }
            nextTurn();
            clsWindow();
        }
    }

    private void clsWindow() {
        for (int i = 0; i < 20; i++) {
            System.out.println();
        }
    }

    public void pauseSimulation() {
        paused = true;
    }

    public void startSimulation() {
        paused = false;
        this.start();

    }

    private void nextTurn() {
        for (Action action : inTurnActions) {
            action.action(map);
        }
    }
}
