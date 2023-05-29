package ru.crypto.keytool.service;

import lombok.SneakyThrows;
import sun.security.tools.keytool.CertAndKeyGen;
import sun.security.x509.X500Name;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;

public class KeystoreService {

    private static final String JKS = "JKS";
    private static final String RSA = "RSA";
    private static final String SHA_WITH_RSA = "SHA1WithRSA";
    private static final long VALIDITY = (long) 365 * 24 * 3600;
    private static final String DNAME = "CN=ROOT";
    private static final String KEY_NAME = "keyName";

    // так делать нехорошо, но поскольку пароль нужен сразу в двух методах, то пусть живет здесь
    private char[] password = "veryStrongPassword123".toCharArray();

    @SneakyThrows
    public KeyPair createKeyPair(String keystoreName, int keySize) {

        KeyStore keyStore = KeyStore.getInstance(JKS);
        keyStore.load(null, password);
        CertAndKeyGen generator = new CertAndKeyGen(RSA, SHA_WITH_RSA);

        generator.generate(keySize);

        PrivateKey key = generator.getPrivateKey();

        X509Certificate cert = generator.getSelfCertificate(new X500Name(DNAME), VALIDITY);
        X509Certificate[] certChain = new X509Certificate[] {cert};

        keyStore.setKeyEntry(KEY_NAME, key, password, certChain);

        String nameJks = keystoreName + ".jks";
        keyStore.store(new FileOutputStream(nameJks), password);

        System.out.println("Создано хранилище " + nameJks);
        return new KeyPair(cert.getPublicKey(), key);
    }

    @SneakyThrows
    public Key getKeyFromKeystore(String keystoreName, String keyName) {

        KeyStore keyStore = KeyStore.getInstance(JKS);
        String nameJks = keystoreName + ".jks";
        keyStore.load(new FileInputStream(nameJks), password);
        System.out.println("Извлечение ключа из хранилища " + nameJks);
        return keyStore.getKey(keyName, password);
    }

}
