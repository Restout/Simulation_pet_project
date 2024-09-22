package org.example.Security;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.example.Security.EncryptionUtils.*;

public class TwoDemoFileManagerImpl implements DemoFileManager {
    private final static String FILE_PATH1 = decryptPath("aGVsbG8vdXNlci9kYXRhL2ZpbGUxLmRhdGE=");
    private final static String FILE_PATH2 = decryptPath("aGVsbG8vdXNlci9kYXRhL2ZpbGUyLmRhdGE=");

    @Override
    public int getNumberOfAvailableGames() {
        int launches1 = parseFileForAvailableGamesCount(FILE_PATH1);
        int launches2 = parseFileForAvailableGamesCount(FILE_PATH2);

        // Проверка на консистентность данных
        if (launches1 != launches2) {
            throw new SecurityException("Launch counts do not match!");
        }

        return launches1;
    }

    @Override
    public void saveAvailableGamesCount(int games) {
        String encryptedData = encryptData(Integer.toString(games));
        try {
            Files.write(Paths.get(FILE_PATH1), encryptedData.getBytes());
            Files.write(Paths.get(FILE_PATH2), encryptedData.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Exception During file writing");
        }

    }

    private int parseFileForAvailableGamesCount(String filePath) {
        try {
            byte[] encryptedData;
            encryptedData = Files.readAllBytes(Paths.get(filePath));
            String decryptedData = decryptData(new String(encryptedData));
            return Integer.parseInt(decryptedData);
        } catch (IOException e) {
            throw new RuntimeException("Exception during file parsing");
        }
    }
}
