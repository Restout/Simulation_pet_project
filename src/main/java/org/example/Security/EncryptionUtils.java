package org.example.Security;

import java.util.Base64;

public class EncryptionUtils {
    private EncryptionUtils() {
    }

    public static String decryptPath(String encryptedPath) {
        return new String(Base64.getDecoder().decode(encryptedPath));
    }

    public static String encryptData(String data) {
        // Простое шифрование методом XOR
        char[] key = {'K', 'C', 'Q'}; // Ключ для XOR
        StringBuilder encrypted = new StringBuilder();
        for (int i = 0; i < data.length(); i++) {
            encrypted.append((char) (data.charAt(i) ^ key[i % key.length]));
        }
        return encrypted.toString();
    }

    public static String decryptData(String encryptedData) {
        return encryptData(encryptedData); // XOR-шифрование обратимо
    }
}
