package com.games.snakeandladder.model;

public class Dice {
    private static final int MIN = 1;
    private static final int MAX = 6;

    public static int getDiceValue() {
        return 1 + (int) (Math.random() * (MAX - MIN + 1));
    }
}
