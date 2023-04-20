package ru.crypto.hw.service;

import org.apache.commons.lang3.StringUtils;
import ru.crypto.hw.utils.CryptUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class EncryptionService {

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
        byte[] encryptedResult = CryptUtils.encrypt(hash);
        System.out.println("Hash: " + hash + "\n" +
                "Encrypted data: " + new String(encryptedResult));

        return encryptedResult;
    }

    public String decrypt(byte [] data) {
        String decrypt = new String(CryptUtils.decrypt(data));
        System.out.println("Decrypted data: " + decrypt);

        return decrypt;
    }

    public void checkHash(String hash, String originalData) {
        System.out.println("Check hash sum: " + hash.equals(CryptUtils.hash(originalData)));
    }
}
