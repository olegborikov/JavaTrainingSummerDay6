package test.borikov.day6.controller.command.impl;

import com.borikov.day6.controller.command.impl.SortBooksByAuthorsCommand;
import com.borikov.day6.controller.command.impl.constant.ResponseKeyName;
import com.borikov.day6.exception.StorageException;
import com.borikov.day6.model.entity.Book;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import test.borikov.day6.creator.BookStorageCreator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.testng.Assert.*;

public class SortBooksByAuthorsCommandTest {
    private SortBooksByAuthorsCommand sortBooksByAuthorsCommand;
    private BookStorageCreator bookStorageCreator;

    @BeforeClass
    public void setUpClass() throws StorageException {
        sortBooksByAuthorsCommand = new SortBooksByAuthorsCommand();
        bookStorageCreator = BookStorageCreator.getInstance();
        bookStorageCreator.setUpBookStorage();
    }

    @AfterClass
    public void tearDownClass() {
        sortBooksByAuthorsCommand = null;
        bookStorageCreator = null;
    }

    @AfterMethod
    public void tearDownMethod() throws StorageException {
        bookStorageCreator.setUpBookStorage();
    }

    @Test
    public void executePositiveTest() {
        Map<String, String> data = new HashMap<>();
        Map<String, List<Book>> actual = sortBooksByAuthorsCommand.execute(data);
        List<Book> sortedBooks = new ArrayList<>();
        sortedBooks.add(bookStorageCreator.getCreatedBooks().get(6));
        sortedBooks.add(bookStorageCreator.getCreatedBooks().get(0));
        sortedBooks.add(bookStorageCreator.getCreatedBooks().get(2));
        sortedBooks.add(bookStorageCreator.getCreatedBooks().get(4));
        sortedBooks.add(bookStorageCreator.getCreatedBooks().get(7));
        sortedBooks.add(bookStorageCreator.getCreatedBooks().get(8));
        sortedBooks.add(bookStorageCreator.getCreatedBooks().get(3));
        sortedBooks.add(bookStorageCreator.getCreatedBooks().get(9));
        sortedBooks.add(bookStorageCreator.getCreatedBooks().get(1));
        sortedBooks.add(bookStorageCreator.getCreatedBooks().get(5));
        Map<String, List<Book>> expected = new HashMap<>();
        expected.put(ResponseKeyName.SORTED_BOOKS, sortedBooks);
        assertEquals(actual, expected);
    }

    @Test
    public void executeNegativeTest() {
        Map<String, String> data = new HashMap<>();
        Map<String, List<Book>> actual = sortBooksByAuthorsCommand.execute(data);
        List<Book> sortedBooks = new ArrayList<>();
        sortedBooks.add(bookStorageCreator.getCreatedBooks().get(6));
        sortedBooks.add(bookStorageCreator.getCreatedBooks().get(0));
        sortedBooks.add(bookStorageCreator.getCreatedBooks().get(2));
        sortedBooks.add(bookStorageCreator.getCreatedBooks().get(4));
        sortedBooks.add(bookStorageCreator.getCreatedBooks().get(7));
        sortedBooks.add(bookStorageCreator.getCreatedBooks().get(8));
        sortedBooks.add(bookStorageCreator.getCreatedBooks().get(3));
        sortedBooks.add(bookStorageCreator.getCreatedBooks().get(9));
        sortedBooks.add(bookStorageCreator.getCreatedBooks().get(1));
        sortedBooks.add(bookStorageCreator.getCreatedBooks().get(5));
        sortedBooks.add(bookStorageCreator.getCreatedBooks().get(5));
        Map<String, List<Book>> expected = new HashMap<>();
        expected.put(ResponseKeyName.SORTED_BOOKS, sortedBooks);
        assertNotEquals(actual, expected);
    }
}
