package ru.crypto.hw.service;

import ru.crypto.hw.dto.ForecastDto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

public class ForecastService {

    private final Map<Boolean, String> forecasts = new HashMap<Boolean, String>() {{
        put(true, "У вас сегодня будет удача в делах!");
        put(false, "Сегодня хороший день для саморазвития!");
    }};

    public static final String BASIC = "basic";
    public static final String SECURE = "secure";

    public Optional<ForecastDto> readForecastInfo() {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {
            System.out.println("Введите имя и нажмите Enter");
            String name = reader.readLine();

            System.out.println("Введите тип прогноза и нажмите Enter");
            String randomType = reader.readLine().toLowerCase(Locale.ROOT);

            reader.close();
            return Optional.of(new ForecastDto(name, randomType));

        } catch (IOException e) {
            System.out.println("Error occurred while reading data from console");
            return Optional.empty();
        }
    }

    public void printForecast(ForecastDto dto) {
        String name = dto.getName();
        switch (dto.getRandomType()) {
            case BASIC:
                System.out.println(name + "! " + forecasts.get(new Random(name.length()).nextBoolean()));
                break;
            case SECURE:
                System.out.println(name + "! " + forecasts.get(new SecureRandom(name.getBytes(StandardCharsets.UTF_8)).nextBoolean()));
                break;
            default:
                System.out.println("Wrong type of randomizer");
        }
    }
}
