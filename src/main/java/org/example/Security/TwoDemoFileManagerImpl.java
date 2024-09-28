package org.example.Security;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileTime;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import static org.example.Security.EncryptionUtils.*;

public class TwoDemoFileManagerImpl implements DemoFileManager {
    private final static String FILE_PATH1 = decryptPath("QzpcUHJvZ3JhbURhdGFcc2ltXGZpbGUxLnR4dA==");
    private final static String FILE_PATH2 = decryptPath("QzpcUHJvZ3JhbURhdGFcYWRhdFxmaWxlMi50eHQ=");


    private final LocalDateTime fileCreationDate;

    public TwoDemoFileManagerImpl() {
        this.fileCreationDate = LocalDateTime.of(2017, 11, 5, 23, 33);
    }

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
            setCustomLastModifiedDate(FILE_PATH1);
            setCustomLastModifiedDate(FILE_PATH2);
        } catch (IOException e) {
            throw new RuntimeException("Exception During file writing");
        }

    }

    private int parseFileForAvailableGamesCount(String filePath) {
        try {
            Path path = Paths.get(filePath);
            if (!Files.exists(path)) {
                path.toFile().getParentFile().mkdirs();

                String encryptedData = encryptData(Integer.toString(1));
                Files.write(path, encryptedData.getBytes());
                setFileCreationDate(filePath);
                setCustomLastModifiedDate(filePath);
                return 1;
            }

            // Если файл существует, читаем и расшифровываем данные
            byte[] encryptedData = Files.readAllBytes(path);
            String decryptedData = decryptData(new String(encryptedData));
            return Integer.parseInt(decryptedData);

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Exception during file parsing");
        }
    }

    private void setCustomLastModifiedDate(String filePath) throws IOException {
        var file = Paths.get(filePath);
        var instant = fileCreationDate.toInstant(ZoneOffset.UTC);
        Files.setLastModifiedTime(file, FileTime.from(instant));
        Files.setAttribute(file, "lastAccessTime", FileTime.from(instant));

        Files.setLastModifiedTime(file.getParent(), FileTime.from(instant));
        Files.setAttribute(file.getParent(), "lastAccessTime", FileTime.from(instant));
    }

    private void setFileCreationDate(String filePath) throws IOException {
        var file = Paths.get(filePath);
        var instant = fileCreationDate.toInstant(ZoneOffset.UTC);
        Files.setAttribute(file, "creationTime", FileTime.from(instant));

        Files.setAttribute(file.getParent(), "creationTime", FileTime.from(instant));

    }
}
