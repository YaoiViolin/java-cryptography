package ru.crypto.keytool;

import javax.crypto.Cipher;
import java.security.Key;

public class CryptUtils {

    private static final String RSA = "RSA";

    public static Cipher initCipher(Key publicKey, int mode) throws Exception{
        Cipher cipher = Cipher.getInstance(RSA);
        cipher.init(mode, publicKey);

        return cipher;
    }
}
