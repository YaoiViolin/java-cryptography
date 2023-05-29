package ru.crypto.keytool;

import lombok.SneakyThrows;
import ru.crypto.keytool.service.KeystoreService;

import javax.crypto.Cipher;
import java.nio.charset.StandardCharsets;
import java.security.KeyPair;
import java.util.Base64;

import static javax.crypto.Cipher.ENCRYPT_MODE;

public class EncryptApp {

    @SneakyThrows
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Error. Zero arguments passed to application");
            return;
        }

        String keystoreName = args[0];
        int keySize = Integer.parseInt(args[1]);

        KeystoreService keystoreService = new KeystoreService();
        KeyPair keyPair = keystoreService.createKeyPair(keystoreName, keySize);

        Cipher cipher = CryptUtils.initCipher(keyPair.getPublic(), ENCRYPT_MODE);

        byte[] encodedBytes = Base64.getEncoder()
                .encode(cipher.doFinal("Java".getBytes(StandardCharsets.UTF_8)));

        System.out.println("Зашифрованное сообщение 'Java': " + new String(encodedBytes));

    }
}
