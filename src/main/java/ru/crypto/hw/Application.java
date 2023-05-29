package ru.crypto.hw;

import ru.crypto.hw.dto.Scenario;
import ru.crypto.hw.service.AsymmetricEncryptionService;
import ru.crypto.hw.service.PBEncryptionService;
import ru.crypto.hw.service.SymmetricEncryptionService;
import ru.crypto.hw.service.ForecastService;

import java.security.Signature;

public class Application {

    /**
     *
     * @param args приминает на вход один аргумент - номер сценария
     *             1 - прогнозы (1 дз)
     *             2 - Симметричное Шифрование (2 дз)
     *             3 - Ассиметричное шифрование + ЦП (3 дз)
     *             4 - Шифрование на основе пароля (4 дз)
     *
     */
    public static void main(String[] args) throws Exception {
        
        if (args.length == 0) {
            System.out.println("Error. Zero arguments passed to application");
            return;
        }

        Scenario scenario = Scenario.get(args[0]);
        switch (scenario) {
            case FORECAST_SCENARIO:
                ForecastService forecastService = new ForecastService();
                forecastService.readForecastInfo()
                        .ifPresent(forecastService::printForecast);

                break;
            case SYMMETRIC_ENCRYPTION_SCENARIO:
                SymmetricEncryptionService symmetricEncryptionService = new SymmetricEncryptionService();
                String secretWord = symmetricEncryptionService.readData();
                byte[] encryptedData = symmetricEncryptionService.encrypt(secretWord);
                String decryptedHash = symmetricEncryptionService.decrypt(encryptedData);
                symmetricEncryptionService.checkHash(decryptedHash, secretWord);

                break;
            case ASYMMETRIC_ENCRYPTION_SCENARIO:
                AsymmetricEncryptionService asymmetricEncryptionService = new AsymmetricEncryptionService();
                byte[] encrypt = asymmetricEncryptionService.encrypt("Java");
                Signature signature = asymmetricEncryptionService.makeSign(encrypt);

                System.out.println("Generated signature object:" + signature.toString());
                break;
            case PASSWORD_BASED_ENCRYPTION_SCENARIO:

                PBEncryptionService pbEncryptionService = new PBEncryptionService();
                byte[] pbEncrypted = pbEncryptionService.encrypt("Java");
                break;


            default:
                System.out.println("Error. Wrong scenario");
        }

    }
}
