package com.lechos22j;

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
}
