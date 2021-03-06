package test.borikov.day6.creator;

import com.borikov.day6.exception.StorageException;
import com.borikov.day6.model.entity.Book;
import com.borikov.day6.model.entity.BookStorage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BookStorageCreator {
    private static BookStorageCreator instance;
    private static List<Book> books;

    private BookStorageCreator() {
        books = new ArrayList<>();
        List<String> authors1 = new ArrayList<>();
        authors1.add("Лев Толстой");
        List<String> authors2 = new ArrayList<>();
        authors2.add("Эрнест Хемингуэй");
        authors2.add("Vladimir Nabokov");
        authors2.add("Фрэнсис Скотт Фицджеральд");
        List<String> authors3 = new ArrayList<>();
        authors3.add("Брюс Эккель");
        List<String> authors4 = new ArrayList<>();
        authors4.add("Robert");
        authors4.add("Oleg");
        List<String> authors5 = new ArrayList<>();
        authors5.add("Лев Толстой");
        List<String> authors6 = new ArrayList<>();
        authors6.add("Robert Sapolsky");
        authors6.add("Robert");
        authors6.add("Sapolsky");
        List<String> authors7 = new ArrayList<>();
        List<String> authors8 = new ArrayList<>();
        authors8.add("Oleg");
        List<String> authors9 = new ArrayList<>();
        authors9.add("Дмитрий Глуховский");
        List<String> authors10 = new ArrayList<>();
        authors10.add("Oleg");
        authors10.add("Alex Black");
        Book book1 = new Book("Война и мир", 1984, "Минск", authors1);
        Book book2 = new Book("Преступление", 2020, "Минск", authors2);
        Book book3 = new Book("Философия Java", 1000, "Питер", authors3);
        Book book4 = new Book("It is ...", 1500, "Питер", authors4);
        Book book5 = new Book("Война и мир", 1990, "Москва", authors5);
        Book book6 = new Book("Behavior", 1750, "London", authors6);
        Book book7 = new Book("История Минска", 1000, "Минск", authors7);
        Book book8 = new Book("Я", 1600, "Москва", authors8);
        Book book10 = new Book("Introduction to Java", 100, "London", authors10);
        Book book9 = new Book("Метро 2033", 2015, "Киев", authors9);
        books.add(book1);
        books.add(book2);
        books.add(book3);
        books.add(book4);
        books.add(book5);
        books.add(book6);
        books.add(book7);
        books.add(book8);
        books.add(book9);
        books.add(book10);
    }

    public static BookStorageCreator getInstance() {
        if (instance == null) {
            instance = new BookStorageCreator();
        }
        return instance;
    }

    public void setUpBookStorage() throws StorageException {
        BookStorage bookStorage = BookStorage.getInstance();
        bookStorage.reset();
        for (Book book : books) {
            bookStorage.add(book);
        }
    }

    public List<Book> getCreatedBooks() {
        return Collections.unmodifiableList(books);
    }
}
