package com.lechos22j;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

public class ConfigReader {
    private static final Map<String, String> config = new HashMap<>();

    static {
        Scanner scanner = new Scanner(Objects.requireNonNull(ConfigReader.class.getResourceAsStream("/default.cfg")));
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if(line.startsWith("#") || !line.contains("=")) continue;
            String[] lineSplit = line.split("=");
            config.put(lineSplit[0], lineSplit[1]);
        }
    }

    public static Map<String, String> getConfig() {
        return config;
    }
    public static void loadConfig(String path) throws IOException {
        FileReader fileReader = new FileReader(path);
        Scanner scanner = new Scanner(Objects.requireNonNull(fileReader));
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if(line.startsWith("#") || !line.contains("=")) continue;
            String[] lineSplit = line.split("=");
            config.put(lineSplit[0], lineSplit[1]);
        }
        scanner.close();
        fileReader.close();
    }
    public static void saveConfig(String path) throws IOException {
        FileWriter fileWriter = new FileWriter(path);
        for (Map.Entry<String, String> entry : config.entrySet()) {
            fileWriter.write(entry.getKey() + "=" + entry.getValue() + "\n");
        }
        fileWriter.close();
    }
}
