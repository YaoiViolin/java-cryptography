package ru.crypto.finalexam.service;

import lombok.SneakyThrows;
import ru.crypto.finalexam.utils.CryptUtils;

import java.security.KeyPair;
import java.security.PublicKey;
import java.security.Signature;

public class SignService {

    private static final String SHA_256_WITH_RSA = "SHA256withRSA";

    @SneakyThrows
    public byte[] createSign(byte[] encryptedString, KeyPair keyPair) {
        Signature signature = CryptUtils.initSign(keyPair.getPrivate());
        signature.update(encryptedString);
        return signature.sign();
    }

    @SneakyThrows
    public boolean verify(byte[] data, byte[] signature, PublicKey key) {
        Signature sign = Signature.getInstance(SHA_256_WITH_RSA);
        sign.initVerify(key);
        sign.update(data);
        return sign.verify(signature);
    }
}
