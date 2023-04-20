package ru.crypto.hw.utils;

import org.apache.commons.codec.digest.DigestUtils;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.util.Objects;

public class CryptUtils {

    private static final String CYPHER_PARAMS = "AES/CBC/PKCS5Padding";
    private static final String PROVIDER = "BC";
    private static final String KEY_ALGORITHM = "AES";
    private static final int KEYSIZE = 256;

    // нужно сохранить для расшифровки
    private static SecretKey key;

    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    public static String hash(String string) {
        return DigestUtils.sha256Hex(string);
    }

    public static byte[] processData(byte[] data, int cipherMode) {
        try {
            Cipher cipher = Cipher.getInstance(CYPHER_PARAMS, PROVIDER);
            cipher.init(cipherMode, getKey(), new IvParameterSpec(new byte[16]));
            return cipher.doFinal(data);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new byte[0];
        }
    }

    private static SecretKey getKey() throws NoSuchAlgorithmException {
        if (Objects.nonNull(key)) {
            return key;
        }
        KeyGenerator keyGenerator = KeyGenerator.getInstance(KEY_ALGORITHM);
        keyGenerator.init(KEYSIZE); //длина
        key = keyGenerator.generateKey();
        return key;
    }

}
