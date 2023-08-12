package org.example;

import org.example.Logic.Simulation;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Simulation simulation = new Simulation(121);
        simulation.startSimulation();
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();

        if (str != null) {
            simulation.pauseSimulation();
        }
        scanner.close();
    }
}