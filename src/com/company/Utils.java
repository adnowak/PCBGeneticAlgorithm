package com.company;

public class Utils {
    public static double CROSS_PROB = 0.9;

    public static int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    public static long getRandomNumber(long min, long max) {
        return (long) ((Math.random() * (max - min)) + min);
    }
}
