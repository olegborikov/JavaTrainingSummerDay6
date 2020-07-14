package com.borikov.day6.model.entity;

import com.borikov.day6.util.IdGenerator;

import java.util.Collections;
import java.util.List;

public class Book {
    private final long bookId;
    private String name;
    private double price;
    private String publishingHouse;
    private List<String> authors;

    public Book(String name, double price, String publishingHouse,
                List<String> authors) {
        this.bookId = IdGenerator.generateId();
        this.name = name;
        this.price = price;
        this.publishingHouse = publishingHouse;
        this.authors = authors;
    }

    public long getBookId() {
        return bookId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getPublishingHouse() {
        return publishingHouse;
    }

    public void setPublishingHouse(String publishingHouse) {
        this.publishingHouse = publishingHouse;
    }

    public List<String> getAuthors() {
        return Collections.unmodifiableList(authors);
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Book book = (Book) o;
        if (bookId != book.bookId) {
            return false;
        }
        if (price != book.price) {
            return false;
        }
        if (name == null) {
            if (book.name != null) {
                return false;
            }
        } else {
            if (!name.equals(book.name)) {
                return false;
            }
        }
        if (publishingHouse == null) {
            if (book.publishingHouse != null) {
                return false;
            }
        } else {
            if (!publishingHouse.equals(book.publishingHouse)) {
                return false;
            }
        }
        if (authors == null) {
            if (book.authors != null) {
                return false;
            }
        } else {
            if (!authors.equals(book.authors)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        return (int) (31 * bookId + price + ((name != null) ? name.hashCode() : 0)
                + ((publishingHouse != null) ? publishingHouse.hashCode() : 0)
                + ((authors != null) ? authors.hashCode() : 0));
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Book {");
        sb.append("bookId = ").append(bookId);
        sb.append(", name = '").append(name).append('\'');
        sb.append(", price = ").append(price);
        sb.append(", publishingHouse = '").append(publishingHouse).append('\'');
        sb.append(", authors = ").append(authors);
        sb.append('}');
        return sb.toString();
    }
}

