package ru.crypto.finalexam;

import lombok.SneakyThrows;
import ru.crypto.finalexam.model.KeystoreType;
import ru.crypto.finalexam.service.CipherService;
import ru.crypto.finalexam.service.KeystoreService;
import ru.crypto.finalexam.service.SignService;

import java.security.KeyPair;
import java.util.Base64;

public class EncryptApp {

    /**
     * @param args [0] - randomType
     *             [1] - keystorePassword
     *             [2] - data to be encrypted
     */
    @SneakyThrows
    public static void main(String[] args) {
        if (args.length < 3) {
            System.out.println("Ошибка. Неверное кол-во аргументов");
            return;
        }

        // input data
        String randomType = args[0];
        String keystorePassword = args[1];
        String data = args[2];

        System.out.println("Исходное сообщение: " + data);

        // init services
        KeystoreService keystoreService = new KeystoreService();
        CipherService cipherService = new CipherService();
        SignService signService = new SignService();

        // keystore
        KeystoreType keystoreType = keystoreService.randomizeKeystoreType(randomType);
        KeyPair keyPair = keystoreService.createKeyPair(keystoreType, keystorePassword);

        // cipher
        byte[] encryptedString = cipherService.encrypt(data, keyPair);
        System.out.println("Сообщение после шифрования: " + new String(Base64.getEncoder().encode(encryptedString)));

        //signature
        byte[] signedString = signService.createSign(encryptedString, keyPair);
        System.out.println("Сообщение после подписи: "+ new String(Base64.getEncoder().encode(signedString)));

    }
}
