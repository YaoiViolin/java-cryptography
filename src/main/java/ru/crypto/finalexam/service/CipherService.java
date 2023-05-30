package ru.crypto.finalexam.service;

import lombok.SneakyThrows;
import ru.crypto.finalexam.utils.CryptUtils;

import javax.crypto.Cipher;
import java.nio.charset.StandardCharsets;
import java.security.KeyPair;
import java.security.PrivateKey;

import static javax.crypto.Cipher.DECRYPT_MODE;
import static javax.crypto.Cipher.ENCRYPT_MODE;

public class CipherService {

    private static final String RSA = "RSA";

    @SneakyThrows
    public byte[] encrypt(String data, KeyPair keyPair) {
        Cipher cipher = CryptUtils.initCipher(keyPair.getPublic(), ENCRYPT_MODE);
        return cipher.doFinal(data.getBytes(StandardCharsets.UTF_8));
    }

    @SneakyThrows
    public byte[] decrypt(byte[] data, PrivateKey key) {
        Cipher cipher = CryptUtils.initCipher(key, DECRYPT_MODE);
        return cipher.doFinal(data);
    }
}
