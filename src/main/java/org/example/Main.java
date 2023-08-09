package org.example;

import org.example.Logic.Simulation;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Simulation simulation=new Simulation();
        simulation.creatMap(64);
        simulation.startsSimulation();
    }
}