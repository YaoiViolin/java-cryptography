package ru.crypto.keytool;

import lombok.SneakyThrows;
import ru.crypto.keytool.service.KeystoreService;

import javax.crypto.Cipher;
import java.util.Base64;

public class DecryptApp {

    @SneakyThrows
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Error. Zero arguments passed to application");
            return;
        }

        String keystoreName = args[0];
        String keyName = args[1];
        String base64encryptedString = args[2];

        byte[] encodedBytes = Base64.getDecoder()
                .decode(base64encryptedString);

        KeystoreService keystoreService = new KeystoreService();

        Cipher cipher = CryptUtils.initCipher(keystoreService.getKeyFromKeystore(keystoreName, keyName), Cipher.DECRYPT_MODE);
        byte[] decodedBytes = cipher.doFinal(encodedBytes);

        System.out.println("Расшифрованное сообщение: " + new String(decodedBytes));
    }

}
