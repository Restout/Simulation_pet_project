package org.example;

import org.example.Logic.Simulation;
import org.example.Security.DemoChecker;
import org.example.Security.DemoCheckerImpl;
import org.example.Security.DemoFileManager;
import org.example.Security.TwoDemoFileManagerImpl;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Simulation simulation = new Simulation(121);
        DemoFileManager demoFileManager = new TwoDemoFileManagerImpl();
        DemoChecker demoChecker = new DemoCheckerImpl(demoFileManager);
        if (demoChecker.checkNumberOfGamesAvailable()) {
            simulation.startSimulation();
            Scanner scanner = new Scanner(System.in);
            String str = scanner.nextLine();

            if (str != null) {
                simulation.pauseSimulation();
            }
            scanner.close();
        }
    }
}