package org.example.Security;

import java.nio.file.*;
import java.util.*;
public class LaunchControll {
    private static final int MAX_LAUNCHES = 4;
    private static final String FILE_PATH1 = decryptPath("aGVsbG8vdXNlci9kYXRhL2ZpbGUxLmRhdGE=");
    private static final String FILE_PATH2 = decryptPath("aGVsbG8vdXNlci9kYXRhL2ZpbGUyLmRhdGE=");

    public static void main(String[] args) throws Exception {
        checkLaunches();
    }

    private static void checkLaunches() throws Exception {
        // Получаем количество запусков из нескольких источников
        int launches1 = getLaunchCount(FILE_PATH1);
        int launches2 = getLaunchCount(FILE_PATH2);

        // Проверка на консистентность данных
        if (launches1 != launches2) {
            throw new SecurityException("Launch counts do not match!");
        }

        // Проводим отвлекающую проверку для создания задержки
        fakeCheck();

        if (launches1 >= MAX_LAUNCHES) {
            System.out.println("Application has been launched too many times.");
            System.exit(0);
        }

        // Увеличиваем количество запусков
        launches1++;
        saveLaunchCount(FILE_PATH1, launches1);
        saveLaunchCount(FILE_PATH2, launches1);

        // Отвлекающие действия
        distract();
    }

    // Метод для получения количества запусков
    private static int getLaunchCount(String filePath) throws Exception {
        byte[] encryptedData = Files.readAllBytes(Paths.get(filePath));
        String decryptedData = decryptData(new String(encryptedData));
        return Integer.parseInt(decryptedData);
    }

    // Метод для сохранения количества запусков
    private static void saveLaunchCount(String filePath, int count) throws Exception {
        String encryptedData = encryptData(Integer.toString(count));
        Files.write(Paths.get(filePath), encryptedData.getBytes());
    }

    // Простейший метод шифрования данных
    private static String encryptData(String data) {
        // Простое шифрование методом XOR
        char[] key = {'K', 'C', 'Q'}; // Ключ для XOR
        StringBuilder encrypted = new StringBuilder();
        for (int i = 0; i < data.length(); i++) {
            encrypted.append((char) (data.charAt(i) ^ key[i % key.length]));
        }
        return encrypted.toString();
    }

    // Простейший метод дешифрования данных
    private static String decryptData(String encryptedData) {
        return encryptData(encryptedData); // XOR-шифрование обратимо
    }

    // Простейший метод дешифрования пути (в данном случае base64)
    private static String decryptPath(String encryptedPath) {
        return new String(Base64.getDecoder().decode(encryptedPath));
    }

    // Отвлекающие проверки
    private static void fakeCheck() {
        // Пустая отвлекающая функция
        String fakeString = "Check123";
        System.out.println(fakeString.hashCode());
    }

    // Отвлекающая функция, выполняющая несуществующие проверки
    private static void distract() {
        // Используем просто случайные данные и ненужные проверки
        Random random = new Random();
        int distractor = random.nextInt(1000);
        if (distractor % 2 == 0) {
            System.out.println("Distractor even number: " + distractor);
        } else {
            System.out.println("Distractor odd number: " + distractor);
        }
    }
}
