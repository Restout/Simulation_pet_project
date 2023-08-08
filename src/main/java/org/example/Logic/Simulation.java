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

    public void creatMap(int squareOfMap) {
        BasicRenderAction.renderMapWithEmptySpaces(map, squareOfMap);
        RenderStaticObjectsAction.renderMapWithStaticObjects(map, squareOfMap);
        RenderInteractionalObjectsAction.renderInteractionalObjects(map, squareOfMap);
    }

    public void renderMap() {
        map.printMap();
    }

    public void startsSimulation() {

    }

    public void pauseSimulation() {

    }

    private void nextTurn() {

    }
}
