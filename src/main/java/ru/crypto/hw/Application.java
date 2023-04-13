package ru.crypto.hw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

public class Application {

    public static final String BASIC = "basic";
    public static final String SECURE = "secure";
    public static final String EXIT = "exit";

    public static void main(String[] args) throws IOException {
        Map<Boolean, String> forecasts = Map.of(
                true, "У вас сегодня будет удача в делах!",
                false, "Сегодня хороший день для саморазвития!"
        );

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String randomType = "";
        String name = "";

        while (!randomType.equals(EXIT)) {

            System.out.println("Введите имя и нажмите Enter");
            name = reader.readLine();

            System.out.println("Введите тип прогноза и нажмите Enter или exit, чтобы выйти");
            randomType = reader.readLine().toLowerCase(Locale.ROOT);

            switch (randomType) {
                case BASIC:
                    System.out.println(name + "! " + forecasts.get(new Random(name.length()).nextBoolean()));
                    break;
                case SECURE:
                    System.out.println(name + "! " + forecasts.get(new SecureRandom(name.getBytes(StandardCharsets.UTF_8)).nextBoolean()));
                    break;
                case EXIT:
                    break;
                default:
                    System.out.println("Wrong type");
            }
        }

        reader.close();
    }
}
