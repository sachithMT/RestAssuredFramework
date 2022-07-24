package com.spotify.oauth2.utils;

import java.io.*;
import java.util.Properties;

public class PropertyUtils {
    public static Properties proprtyLoader(String filePath) {
        Properties properties = new Properties();
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(filePath));
            try {
                properties.load(reader);
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException("Failed to load file at " + filePath);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Properties file not found at" + filePath);
        }
        return properties;
    }
}