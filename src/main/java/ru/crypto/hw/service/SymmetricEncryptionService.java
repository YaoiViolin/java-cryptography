package ru.crypto.hw.service;

import org.apache.commons.lang3.StringUtils;
import ru.crypto.hw.utils.CryptUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import static javax.crypto.Cipher.DECRYPT_MODE;
import static javax.crypto.Cipher.ENCRYPT_MODE;

public class SymmetricEncryptionService implements EncryptionService{

    public String readData() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {
            System.out.println("Введите секретное слово и нажмите Enter");
            String data = reader.readLine();
            reader.close();
            return data;
        } catch (IOException e) {
            System.out.println("Error occurred while reading data from console");
            return null;
        }
    }

    public byte[] encrypt(String data) {
        if (StringUtils.isBlank(data)) {
            return null;
        }
        String hash = CryptUtils.hash(data);
        byte[] encryptedResult = CryptUtils.processData(hash.getBytes(StandardCharsets.UTF_8), ENCRYPT_MODE);
        System.out.println("Hash: " + hash + "\n" +
                "Encrypted data: " + new String(encryptedResult));

        return encryptedResult;
    }

    public String decrypt(byte [] data) {
        String decrypt = new String(CryptUtils.processData(data, DECRYPT_MODE));
        System.out.println("Decrypted data: " + decrypt);

        return decrypt;
    }

    public void checkHash(String hash, String originalData) {
        System.out.println("Check hash sum: " + hash.equals(CryptUtils.hash(originalData)));
    }
}
