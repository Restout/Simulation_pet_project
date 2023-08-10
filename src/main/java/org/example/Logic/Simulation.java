package org.example.Logic;

import org.example.Logic.Actions.*;

import java.util.Arrays;
import java.util.List;

public class Simulation {
    private Map map;
    List<Action> initActions = Arrays.asList(new BasicRenderAction(), new RenderStaticObjectsAction(), new RenderInteractionalObjectsAction());
    List<Action> inTurnActions = Arrays.asList(new MakeMoveAction(), new AttackAction());

    public Simulation() {
        map = new Map();
    }

    public void creatMap() {
        BasicRenderAction.action(map);
        RenderStaticObjectsAction.action(map);
        RenderInteractionalObjectsAction.action(map);
    }

    public void renderMap() {
        map.printMap();
    }

    public void startsSimulation() throws InterruptedException {
        creatMap();
        while (true) {
            renderMap();
            Thread.sleep(3000);
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

    }

    private void nextTurn() {
        MakeMoveAction.action(map);
    }
}
