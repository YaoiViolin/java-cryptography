package ru.crypto.hw.dto;

public class ForecastDto {

    public ForecastDto(String name, String randomType) {
        this.name = name;
        this.randomType = randomType;
    }

    public String getName() {
        return name;
    }

    public String getRandomType() {
        return randomType;
    }

    private String name;
    private String randomType;

}
