package ru.crypto.hw.dto;

import java.util.HashMap;
import java.util.Map;

public enum Scenario {

    FORECAST_SCENARIO("1"),
    SYMMETRIC_ENCRYPTION_SCENARIO("2"),
    ASYMMETRIC_ENCRYPTION_SCENARIO("3"),
    PASSWORD_BASED_ENCRYPTION_SCENARIO("4")
    ;

    private static Map<String, Scenario> values = new HashMap<>();
    private final String scenarioNumber;

    static {
        for (Scenario s: Scenario.values()) {
            values.put(s.scenarioNumber, s);
        }
    }

    Scenario (String scenarioNumber) {
        this.scenarioNumber = scenarioNumber;
    }

    public static Scenario get(String scenarioNumber) {
        return values.get(scenarioNumber);
    }
}