package ru.crypto.hw.utils;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.security.spec.KeySpec;

import static javax.crypto.Cipher.ENCRYPT_MODE;

public class PBCryptUtils {

    private static final String PB_ALGORITHM = "PBKDF2WithHmacSHA256";
    private static final String AES = "AES";


    public static SecretKey generateSecretKey(String password) throws Exception {
        SecureRandom secureRandom = new SecureRandom();

        byte[] salt = new byte[16];
        secureRandom.nextBytes(salt);

        KeySpec keySpec = new PBEKeySpec(
                password.toCharArray(),
                salt,
                Short.MAX_VALUE, 256);

        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(PB_ALGORITHM);
        return new SecretKeySpec(keyFactory.generateSecret(keySpec).getEncoded(), AES);

    }

    public static Cipher initCipher(SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance(AES);
        cipher.init(ENCRYPT_MODE, key);
        return cipher;
    }
}
