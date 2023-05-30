package ru.crypto.finalexam;

import lombok.SneakyThrows;
import ru.crypto.finalexam.service.CipherService;
import ru.crypto.finalexam.service.KeystoreService;
import ru.crypto.finalexam.service.SignService;

import java.security.Key;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.util.Base64;

public class DecryptApp {

    /**
     * @param args [0] - keystorePath
     *             [1] - keystorePassword
     *             [2] - base64EncryptedString
     *             [3] - base64EncryptedSign
     *             [4] - keyName
     */
    @SneakyThrows
    public static void main(String[] args) {
        if (args.length < 5) {
            System.out.println("Ошибка. Неверное кол-во аргументов");
            return;
        }

        // input data
        String keystorePath = args[0];
        String keystorePassword = args[1];
        byte[] encryptedString = Base64.getDecoder().decode(args[2]);
        byte[] encryptedSign = Base64.getDecoder().decode(args[3]);
        String keyName = args[4];

        // init services
        KeystoreService keystoreService = new KeystoreService();
        CipherService cipherService = new CipherService();
        SignService signService = new SignService();

        // keystore
        KeyStore keyStore = keystoreService.getKeystore(keystorePath, keyName, keystorePassword);

        // decrypt
        byte[] decryptedString = cipherService.decrypt(encryptedString, (PrivateKey) keyStore.getKey(keyName, keystorePassword.toCharArray()));
        System.out.println("Расшифрованная строка: " + new String(decryptedString));

        // sign
        boolean verified = signService.verify(encryptedString, encryptedSign, keyStore.getCertificate(keyName).getPublicKey());
        System.out.println(verified ? "Подпись верна" : "Подпись не верна");
    }
}
