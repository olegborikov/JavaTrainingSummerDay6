package test.borikov.day6.controller.command.impl;

import com.borikov.day6.controller.command.impl.FindAllBooksCommand;
import com.borikov.day6.controller.command.impl.constant.ResponseKeyType;
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

public class FindAllBooksCommandTest {
    private FindAllBooksCommand findAllBooksCommand;
    private BookStorageCreator bookStorageCreator;

    @BeforeClass
    public void setUpClass() throws StorageException {
        findAllBooksCommand = new FindAllBooksCommand();
        bookStorageCreator = BookStorageCreator.getInstance();
        bookStorageCreator.setUpBookStorage();
    }

    @AfterClass
    public void tearDownClass() {
        findAllBooksCommand = null;
        bookStorageCreator = null;
    }

    @AfterMethod
    public void tearDownMethod() throws StorageException {
        bookStorageCreator.setUpBookStorage();
    }

    @Test
    public void executePositiveTest() {
        Map<String, String> data = new HashMap<>();
        Map<String, List<Book>> actual = findAllBooksCommand.execute(data);
        List<Book> allBooks = bookStorageCreator.getCreatedBooks();
        Map<String, List<Book>> expected = new HashMap<>();
        expected.put(ResponseKeyType.ALL_BOOKS, allBooks);
        assertEquals(actual, expected);
    }

    @Test
    public void executeNegativeTest() {
        Map<String, String> data = new HashMap<>();
        Map<String, List<Book>> actual = findAllBooksCommand.execute(data);
        List<Book> allBooks =
                new ArrayList<>(bookStorageCreator.getCreatedBooks());
        allBooks.add(null);
        Map<String, List<Book>> expected = new HashMap<>();
        expected.put(ResponseKeyType.ALL_BOOKS, allBooks);
        assertNotEquals(actual, expected);
    }
}
