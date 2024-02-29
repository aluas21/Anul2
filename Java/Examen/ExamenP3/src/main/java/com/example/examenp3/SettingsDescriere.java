package com.example.examenp3;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class SettingsDescriere {
    private static SettingsDescriere instance;

    private final String descriere;


    private SettingsDescriere(String repoType) {
        this.descriere = repoType;
    }

    public String getDescriere() {
        return descriere;
    }

    private static Properties loadSettings() {
        try (FileReader fr = new FileReader("/Volumes/Macintosh HD/Desktop/PROGRAMARE/MAP/ExamenP3/ExamenP3/src/main/java/com/example/examenp3/descrieri.txt")) {
            Properties settings = new Properties();
            settings.load(fr);
            return settings;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static synchronized SettingsDescriere getInstance() {
        Properties properties = loadSettings();
        // TODO De vazut ce se intampla daca setarea nu e in fisier
        // am vazut ce se intampla daca setarea nu e in fisier, se arunca exceptie
        instance = new SettingsDescriere(properties.getProperty("descriere"));
        return instance;
    }
}
