package org.example;

import org.example.Logic.Simulation;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Simulation simulation=new Simulation(121);
        simulation.startSimulation();
        Scanner scanner = new Scanner(System.in);
        var ch = scanner.nextLine();

        if (ch != null) {
            simulation.pauseSimulation();
        }
        scanner.close();
    }
}