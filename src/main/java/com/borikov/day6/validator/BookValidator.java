package com.borikov.day6.validator;

public class BookValidator {
    private static final double MIN_PRICE = 0.01;
    private static final double MAX_PRICE = 1000;

    public boolean isPriceCorrect(double price) {
        boolean result = true;
        if (price < MIN_PRICE && price > MAX_PRICE) {
            result = false;
        }
        return result;
    }
}

