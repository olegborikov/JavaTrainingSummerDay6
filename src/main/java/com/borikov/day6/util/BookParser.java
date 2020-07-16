package com.borikov.day6.util;

public class BookParser {
    public long parseId(String id) {
        long idParsed;
        try {
            idParsed = Long.parseLong(id);
        } catch (NumberFormatException e) {
            idParsed = -1;
        }
        return idParsed;
    }

    public int parsePublishingYear(String publishingYear) {
        int publishingYearParsed;
        try {
            publishingYearParsed = Integer.parseInt(publishingYear);
        } catch (NumberFormatException e) {
            publishingYearParsed = -1;
        }
        return publishingYearParsed;
    }
}
