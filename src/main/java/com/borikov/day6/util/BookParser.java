package com.borikov.day6.util;

public class BookParser {
    private static final int DEFAULT_VALUE = -1;

    public long parseId(String id) {
        long idParsed;
        try {
            idParsed = Long.parseLong(id);
        } catch (NumberFormatException e) {
            idParsed = DEFAULT_VALUE;
        }
        return idParsed;
    }

    public int parsePublishingYear(String publishingYear) {
        int publishingYearParsed;
        try {
            publishingYearParsed = Integer.parseInt(publishingYear);
        } catch (NumberFormatException e) {
            publishingYearParsed = DEFAULT_VALUE;
        }
        return publishingYearParsed;
    }
}
