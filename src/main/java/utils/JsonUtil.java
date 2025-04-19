package utils;

import config.Configreader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonUtil {


    public static String readJsonFileAsString(String filePath) {
        try {
            return new String(Files.readAllBytes(Paths.get(Configreader.get("jsonFilePath") + filePath)));
        } catch (IOException e) {
            throw new RuntimeException("Failed to read JSON file: " + filePath, e);
        }
    }
}


