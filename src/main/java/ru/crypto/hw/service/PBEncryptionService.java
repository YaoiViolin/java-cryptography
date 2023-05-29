package ru.crypto.hw.service;

import ru.crypto.hw.utils.PBCryptUtils;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class PBEncryptionService implements EncryptionService {


    @Override
    public byte[] encrypt(String data) throws Exception {

        System.out.println("Введите пароль для шифрования:");
        Scanner in = new Scanner(System.in);
        String password = in.nextLine();

        SecretKey secretKey = PBCryptUtils.generateSecretKey(password);

        Cipher cipher = PBCryptUtils.initCipher(secretKey);

        byte[] bytes = cipher.doFinal(data.getBytes(StandardCharsets.UTF_8));

        System.out.println("Сообщение зашифровано: " + new String(bytes));
        return bytes;
    }
}
