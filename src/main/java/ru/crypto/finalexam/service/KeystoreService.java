package ru.crypto.finalexam.service;

import lombok.SneakyThrows;
import ru.crypto.finalexam.model.KeystoreType;
import ru.crypto.finalexam.model.RandomType;
import sun.security.tools.keytool.CertAndKeyGen;
import sun.security.x509.X500Name;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

import static ru.crypto.finalexam.model.KeystoreType.JCEKS;
import static ru.crypto.finalexam.model.KeystoreType.JKS;

public class KeystoreService {

    private static final String RSA = "RSA";
    private static final String SHA_WITH_RSA = "SHA1WithRSA";
    private static final long VALIDITY = (long) 365 * 24 * 3600;
    private static final String DNAME = "CN=ROOT";
    private static final String KEY_NAME = "keyName";
    private static final int KEY_SIZE = 2048;


    @SneakyThrows
    public KeystoreType randomizeKeystoreType(String randomType) {
        Map<Boolean, KeystoreType> keystoreTypeMap = new HashMap<Boolean, KeystoreType>() {{
            put(true, JKS);
            put(false, JCEKS);
        }};

        KeystoreType keystoreType;

        switch (RandomType.valueOf(randomType.toUpperCase(Locale.ROOT))) {

            case BASIC:
                keystoreType = keystoreTypeMap.get(new Random(System.currentTimeMillis()).nextBoolean());

                break;
            case SECURE:
                keystoreType = keystoreTypeMap.get(new SecureRandom(SecureRandom.class.getName().getBytes(StandardCharsets.UTF_8)).nextBoolean());
                break;
            default:
                throw new IllegalArgumentException("Неизвестный тип");
        }
         return keystoreType;
    }

    @SneakyThrows
    public KeyPair createKeyPair(KeystoreType keystoreType, String password) {
        final String keystoreName = "verySpecialKeystore." + keystoreType.name().toLowerCase(Locale.ROOT);


        KeyStore keyStore = KeyStore.getInstance(keystoreType.name());
        char[] passwordChars = password.toCharArray();

        keyStore.load(null, passwordChars);
        CertAndKeyGen generator = new CertAndKeyGen(RSA, SHA_WITH_RSA);
        generator.generate(KEY_SIZE);

        PrivateKey key = generator.getPrivateKey();

        X509Certificate cert = generator.getSelfCertificate(new X500Name(DNAME), VALIDITY);
        X509Certificate[] certChain = new X509Certificate[] {cert};

        keyStore.setKeyEntry(KEY_NAME, key, passwordChars, certChain);
        keyStore.store(new FileOutputStream(keystoreName), passwordChars);

        System.out.println("Создано хранилище " + keystoreName);
        return new KeyPair(cert.getPublicKey(), key);
    }

    @SneakyThrows
    public KeyStore getKeystore(String keystoreName, String keyName, String password) {

        KeyStore keyStore = KeyStore.getInstance(KeystoreType.defineTypeByFilename(keystoreName).name());
        char[] passwordChars = password.toCharArray();
        keyStore.load(new FileInputStream(keystoreName), passwordChars);
        System.out.println("Извлечение ключа из хранилища " + keystoreName);
        return keyStore;
    }
}
