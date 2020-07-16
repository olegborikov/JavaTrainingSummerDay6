package com.borikov.day6.validator;

import java.util.List;

public class BookValidator {
    private static final double MIN_PRICE = 0.01;
    private static final double MAX_PRICE = 1000.0;
    private static final long MIN_ID = 1;
    private static final long MAX_ID = 100_000;
    private static final int MAX_STRING_LENGTH = 40;
    private static final int MIN_STRING_LENGTH = 1;
    private static final int MAX_AUTHORS_AMOUNT = 10;

    public boolean isIdCorrect(long id) {
        boolean result = true;
        if (id < MIN_ID || id > MAX_ID) {
            result = false;
        }
        return result;
    }

    public boolean isNameCorrect(String name) {
        boolean result = true;
        if (name == null || name.isBlank() ||
                name.length() < MIN_STRING_LENGTH ||
                name.length() > MAX_STRING_LENGTH) {
            result = false;
        }
        return result;
    }

    public boolean isPriceCorrect(double price) {
        boolean result = true;
        if (price < MIN_PRICE || price > MAX_PRICE) {
            result = false;
        }
        return result;
    }

    public boolean isPublishingHouseCorrect(String publishingHouse) {
        boolean result = true;
        if (publishingHouse == null || publishingHouse.isBlank() ||
                publishingHouse.length() < MIN_STRING_LENGTH ||
                publishingHouse.length() > MAX_STRING_LENGTH) {
            result = false;
        }
        return result;
    }

    public boolean isAuthorsCorrect(List<String> authors) {
        boolean result = true;
        if (authors == null ||
                authors.size() > MAX_AUTHORS_AMOUNT ||
                authors.stream().anyMatch(author -> !isAuthorCorrect(author))) {
            result = false;
        }
        return result;
    }

    public boolean isAuthorCorrect(String author) {
        boolean result = true;
        if (author == null || author.isBlank() ||
                author.length() < MIN_STRING_LENGTH ||
                author.length() > MAX_STRING_LENGTH) {
            result = false;
        }
        return result;
    }
}

