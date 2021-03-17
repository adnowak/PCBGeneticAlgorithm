package com.company;

public class Utils {
    public static final double CROSS_PROB = 0.8;
    public static final double MUTATION_PROB = 0.3;
    public static final int TOURNAMENT_SIZE = 10;

    public static int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    public static long getRandomNumber(long min, long max) {
        return (long) ((Math.random() * (max - min)) + min);
    }
}
