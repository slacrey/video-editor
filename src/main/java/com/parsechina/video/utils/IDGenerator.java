package com.parsechina.video.utils;

public class IDGenerator {

    public static String uuid() {
        String uuid = java.util.UUID.randomUUID().toString();
        return uuid.replace("-", "");
    }

    public static String randomNumber() {
        return String.valueOf((int) ((Math.random() * 9 + 1) * 10000));
    }

}