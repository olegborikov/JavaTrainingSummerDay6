package test.borikov.day6.controller.command.impl;

import com.borikov.day6.controller.command.impl.SortBooksByIdCommand;
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

public class SortBooksByIdCommandTest {
    private SortBooksByIdCommand sortBooksByIdCommand;
    private BookStorageCreator bookStorageCreator;

    @BeforeClass
    public void setUpClass() throws StorageException {
        sortBooksByIdCommand = new SortBooksByIdCommand();
        bookStorageCreator = BookStorageCreator.getInstance();
        bookStorageCreator.setUpBookStorage();
    }

    @AfterClass
    public void tearDownClass() {
        sortBooksByIdCommand = null;
        bookStorageCreator = null;
    }

    @AfterMethod
    public void tearDownMethod() throws StorageException {
        bookStorageCreator.setUpBookStorage();
    }

    @Test
    public void executePositiveTest() {
        Map<String, String> data = new HashMap<>();
        Map<String, List<Book>> actual = sortBooksByIdCommand.execute(data);
        List<Book> sortedBooks = new ArrayList<>();
        sortedBooks.add(bookStorageCreator.getCreatedBooks().get(0));
        sortedBooks.add(bookStorageCreator.getCreatedBooks().get(1));
        sortedBooks.add(bookStorageCreator.getCreatedBooks().get(2));
        sortedBooks.add(bookStorageCreator.getCreatedBooks().get(3));
        sortedBooks.add(bookStorageCreator.getCreatedBooks().get(4));
        sortedBooks.add(bookStorageCreator.getCreatedBooks().get(5));
        sortedBooks.add(bookStorageCreator.getCreatedBooks().get(6));
        sortedBooks.add(bookStorageCreator.getCreatedBooks().get(7));
        sortedBooks.add(bookStorageCreator.getCreatedBooks().get(9));
        sortedBooks.add(bookStorageCreator.getCreatedBooks().get(8));
        Map<String, List<Book>> expected = new HashMap<>();
        expected.put(ResponseKeyName.SORTED_BOOKS, sortedBooks);
        assertEquals(actual, expected);
    }

    @Test
    public void executeNegativeTest() {
        Map<String, String> data = new HashMap<>();
        Map<String, List<Book>> actual = sortBooksByIdCommand.execute(data);
        List<Book> sortedBooks = bookStorageCreator.getCreatedBooks();
        Map<String, List<Book>> expected = new HashMap<>();
        expected.put(ResponseKeyName.SORTED_BOOKS, sortedBooks);
        assertNotEquals(actual, expected);
    }
}
