package ru.crypto.hw;

import ru.crypto.hw.service.EncryptionService;
import ru.crypto.hw.service.ForecastService;

import java.io.IOException;

public class Application {

    private static final String FORECAST_SCENARIO = "1";
    private static final String ENCRYPTION_SCENARIO = "2";

    /**
     *
     * @param args приминает на вход один аргумент - номер сценария
     *             1 - прогнозы (1 дз)
     *             2 - Шифр (2 дз)
     *
     */
    public static void main(String[] args) throws IOException {
        
        if (args.length == 0) {
            System.out.println("Error. Zero arguments passed to application");
            return;
        }
        
        switch (args[0]) {
            case FORECAST_SCENARIO:
                ForecastService forecastService = new ForecastService();
                forecastService.readForecastInfo()
                        .ifPresent(forecastService::printForecast);

                break;
            case ENCRYPTION_SCENARIO:
                EncryptionService encryptionService = new EncryptionService();
                String secretWord = encryptionService.readData();
                byte[] encryptedData = encryptionService.encrypt(secretWord);
                String decryptedHash = encryptionService.decrypt(encryptedData);
                encryptionService.checkHash(decryptedHash, secretWord);

                break;
            default:
                System.out.println("Error. Wrong scenario");
        }

    }
}
