package ru.crypto.finalexam.utils;

import javax.crypto.Cipher;
import java.security.Key;
import java.security.PrivateKey;
import java.security.Signature;

public class CryptUtils {

    private static final String RSA = "RSA";

    public static Cipher initCipher(Key publicKey, int mode) throws Exception{
        Cipher cipher = Cipher.getInstance(RSA);
        cipher.init(mode, publicKey);

        return cipher;
    }

    public static Signature initSign(PrivateKey privateKey) throws Exception{
        Signature sign = Signature.getInstance("SHA256withRSA");
        sign.initSign(privateKey);
        return sign;
    }
}
