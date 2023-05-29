package ru.crypto.hw.service;

import ru.crypto.hw.utils.AsymmetricCryptUtils;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import java.nio.charset.StandardCharsets;
import java.security.KeyPair;
import java.security.Signature;
import java.security.SignatureException;

public class AsymmetricEncryptionService implements EncryptionService {

    @Override
    public byte[] encrypt(String data) {
        KeyPair keyPair = AsymmetricCryptUtils.generateKeyPair(2048);
        Cipher cipher = AsymmetricCryptUtils.initCipher(keyPair);

        try {
            if (cipher == null) {
                return new byte[0];
            }
            return cipher.doFinal(data.getBytes(StandardCharsets.UTF_8));

        } catch (IllegalBlockSizeException | BadPaddingException e) {
            System.out.println(e.getMessage());
            return new byte[0];
        }
    }

    public Signature makeSign(byte[] data) {
        try {
            Signature signature = AsymmetricCryptUtils.getSignature();
            if (signature == null) {
                return null;
            }
            signature.update(data);
            signature.sign();
            return signature;
        } catch (SignatureException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
