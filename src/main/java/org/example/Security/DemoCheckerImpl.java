package org.example.Security;


import java.util.Random;

public class DemoCheckerImpl implements DemoChecker {
    private static final int MAX_LAUNCHES = 4;

    private final DemoFileManager demoFileManager;

    public DemoCheckerImpl(DemoFileManager demoFileManager) {
        this.demoFileManager = demoFileManager;
    }

    @Override
    public boolean checkDemo() {
        return true;
    }

    @Override
    public boolean checkNumberOfGamesAvailable() {
        int games = demoFileManager.getNumberOfAvailableGames();

        fakeCheck();// Проводим отвлекающую проверку для создания задержки
        if (games >= MAX_LAUNCHES) {
            System.out.println("Application has been launched too many times.");
            System.out.println(games);
            System.exit(0);
            return false;
        }

        distract();// Отвлекающие действия
        increaseGamesAndSave(games);

        return true;
    }

    private void increaseGamesAndSave(int games) {
        games++;
        demoFileManager.saveAvailableGamesCount(games);
        demoFileManager.saveAvailableGamesCount(games);
    }

    public void fakeCheck() {
        String fakeString = "Check123";
        System.out.println(fakeString.hashCode());
    }

    private void distract() {
        Random random = new Random();
        int distractor = random.nextInt(1000);
        if (distractor % 2 == 0) {
            System.out.println("Distractor even number: " + distractor);
        } else {
            System.out.println("Distractor odd number: " + distractor);
        }
    }

}
