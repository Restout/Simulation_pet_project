package org.example.Logic;

import org.example.Logic.Actions.*;

import java.util.Arrays;
import java.util.List;

public class Simulation extends Thread {
    private final Map map;
    private boolean isMapCreated = false;
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
        if (!isMapCreated) {
            creatMap();
            isMapCreated = true;
        }
        while (true) {
            renderMap();
            try {
                Thread.sleep(1000);
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

    public void pauseSimulation() throws InterruptedException {
        this.wait();
        //TODO
    }

    public void continueSimulation() {
        this.notify();
    }

    private void nextTurn() {
        for (Action action : inTurnActions) {
            action.action(map);
        }
    }
}
