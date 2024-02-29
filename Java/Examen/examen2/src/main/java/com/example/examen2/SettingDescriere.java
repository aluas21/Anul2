package com.example.examen2;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class SettingDescriere {
    private static SettingDescriere instance;

    private final String descriere;

    private SettingDescriere(String repoType) {
        this.descriere = repoType;

    }

    public String getDescriere() {
        return descriere;
    }

    private static Properties loadSettings() {
        try (FileReader fr = new FileReader("/Volumes/Macintosh HD/Desktop/PROGRAMARE/MAP/examenTxt/examen2/src/main/java/com/example/examen2/descriere.txt")) {
            Properties settings = new Properties();
            settings.load(fr);
            return settings;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static synchronized SettingDescriere getInstance() {
        Properties properties = loadSettings();
        // TODO De vazut ce se intampla daca setarea nu e in fisier
        // am vazut ce se intampla daca setarea nu e in fisier, se arunca exceptie
        instance = new SettingDescriere(properties.getProperty("descriere"));
        return instance;
    }
}
