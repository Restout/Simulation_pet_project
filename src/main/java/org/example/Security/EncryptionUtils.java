package org.example.Security;

import java.util.Base64;

public class EncryptionUtils {
    private EncryptionUtils() {
    }

    public static String decryptPath(String encryptedPath) {
        return new String(Base64.getDecoder().decode(encryptedPath));
    }

    public static String encryptData(String data) {
        StringBuilder encrypted = new StringBuilder();

        // Проходим по каждой цифре строки
        for (int i = 0; i < data.length(); i++) {
            char currentChar = data.charAt(i);

            // Преобразуем символ в число
            int number = Character.getNumericValue(currentChar);

            // Применяем побитовый сдвиг и добавляем фиктивное число
            int shiftedNumber = (number << 1) + i % 3;  // Сдвиг влево на 1 бит + смещение по индексу

            // Преобразуем обратно в символ
            encrypted.append((char)(shiftedNumber + 'A'));  // Преобразуем в символ от 'A'

            // Вставляем фиктивный символ для усложнения
            if (i % 2 == 0) {
                encrypted.append((char)('X' + i % 5));  // Вставляем фиктивный символ
            } else {
                encrypted.insert(0, (char)('P' + i % 4));  // Вставляем фиктивный символ в начало
            }
        }

        return encrypted.toString();
    }

    public static String decryptData(String encryptedData) {
        StringBuilder decrypted = new StringBuilder();

        // Удаляем фиктивные символы, зная их местоположение
        StringBuilder cleanData = new StringBuilder();
        for (int i = 0; i < encryptedData.length(); i++) {
            if (i % 2 == 0 || i % 3 == 0) {  // Пропускаем символы на четных или кратных 3 позициях
                cleanData.append(encryptedData.charAt(i));
            }
        }

        // Восстанавливаем данные
        for (int i = 0; i < cleanData.length(); i++) {
            char currentChar = cleanData.charAt(i);

            // Преобразуем символ обратно в число
            int shiftedNumber = currentChar - 'A';  // Преобразуем символ в число

            // Обратный побитовый сдвиг
            int originalNumber = (shiftedNumber - (i % 3)) >> 1;

            // Преобразуем число обратно в символ
            decrypted.append(originalNumber);
        }

        return decrypted.toString();
    }
}
