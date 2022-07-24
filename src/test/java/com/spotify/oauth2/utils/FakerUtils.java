package com.spotify.oauth2.utils;

import com.github.javafaker.Faker;

public class FakerUtils {

    public static String generateName(){
        Faker faker = new Faker();
        return "Play List " + faker.regexify("[A-Za-z0-9_-&]{10}");
    }

    public static String generateDescription(){
        Faker faker = new Faker();
        return "Play List " + faker.regexify("[A-Za-z0-9_-&]{20}");
    }
}
