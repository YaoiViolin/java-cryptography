package ru.crypto.finalexam.model;

public enum KeystoreType {
    JKS,
    JCEKS;

    public static KeystoreType defineTypeByFilename(String fileName) {
        if (fileName.endsWith(".jks")) {
            return JKS;
        } else if (fileName.endsWith(".jceks")) {
            return JCEKS;
        }
        throw new IllegalArgumentException("Неправильный формат файла");
    }
}
