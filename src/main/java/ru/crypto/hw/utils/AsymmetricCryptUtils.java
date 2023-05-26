package ru.crypto.hw.utils;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.Signature;

import static javax.crypto.Cipher.ENCRYPT_MODE;

public class AsymmetricCryptUtils {

    public static final String RSA = "RSA";
    public static final String SHA_256_WITH_RSA = "SHA256withRSA";

    public static KeyPair generateKeyPair(int keySize) {
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(RSA);
            keyPairGenerator.initialize(keySize);
            return keyPairGenerator.generateKeyPair();

        } catch (NoSuchAlgorithmException e) {
            System.out.println(e.getLocalizedMessage());
            return null;
        }
    }

    public static Cipher initCipher(KeyPair pair) {
        try {
            if (pair == null) {
                return null;
            }
            Cipher cipher = Cipher.getInstance(RSA);
            cipher.init(ENCRYPT_MODE, pair.getPrivate());
            return cipher;
        } catch (InvalidKeyException | NoSuchPaddingException | NoSuchAlgorithmException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static Signature getSignature() {
        try {
            Signature sign = Signature.getInstance(SHA_256_WITH_RSA);
            KeyPair keyPair = generateKeyPair(2048);
            if (keyPair != null) {
                sign.initSign(keyPair.getPrivate());
            }
            return sign;
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            System.out.println(e.getMessage());
            return null;
        }

    }
}
