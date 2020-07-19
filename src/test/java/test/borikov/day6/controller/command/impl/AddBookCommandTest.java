package test.borikov.day6.controller.command.impl;

import com.borikov.day6.controller.command.impl.AddBookCommand;
import com.borikov.day6.controller.command.impl.constant.DataKeyType;
import com.borikov.day6.controller.command.impl.constant.ResponseKeyType;
import com.borikov.day6.exception.StorageException;
import com.borikov.day6.model.entity.Book;
import org.testng.annotations.*;
import test.borikov.day6.creator.BookStorageCreator;

import java.util.*;

import static org.testng.Assert.*;

public class AddBookCommandTest {
    private AddBookCommand addBookCommand;
    private BookStorageCreator bookStorageCreator;

    @BeforeClass
    public void setUpClass() throws StorageException {
        addBookCommand = new AddBookCommand();
        bookStorageCreator = BookStorageCreator.getInstance();
        bookStorageCreator.setUpBookStorage();
    }

    @AfterClass
    public void tearDownClass() {
        addBookCommand = null;
        bookStorageCreator = null;
    }

    @AfterMethod
    public void tearDownMethod() throws StorageException {
        bookStorageCreator.setUpBookStorage();
    }

    @DataProvider(name = "executePositiveData")
    public Object[][] createExecutePositiveData() {
        Map<String, String> data1 = new HashMap<>();
        data1.put(DataKeyType.NAME, "Война и мир");
        data1.put(DataKeyType.PUBLISHING_YEAR, "2020");
        data1.put(DataKeyType.PUBLISHING_HOUSE, "Минск");
        data1.put(DataKeyType.AUTHOR + "1", "Лев");
        List<Book> addedBook1 = new ArrayList<>();
        addedBook1.add(new Book("Война и мир", 2020, "Минск", Arrays.asList("Лев")));
        Map<String, List<Book>> expected1 = new HashMap<>();
        expected1.put(ResponseKeyType.ADDED_BOOK, addedBook1);
        Map<String, String> data2 = new HashMap<>();
        data2.put(DataKeyType.NAME, "This is very very long line with 43 symbols");
        data2.put(DataKeyType.PUBLISHING_YEAR, "2020");
        data2.put(DataKeyType.PUBLISHING_HOUSE, "Минск");
        data2.put(DataKeyType.AUTHOR + "1", "Лев");
        List<Book> addedBook2 = new ArrayList<>();
        Map<String, List<Book>> expected2 = new HashMap<>();
        expected2.put(ResponseKeyType.ADDED_BOOK, addedBook2);
        Map<String, String> data3 = new HashMap<>();
        data3.put(DataKeyType.NAME, "Война и мир");
        data3.put(DataKeyType.PUBLISHING_YEAR, "2020");
        data3.put(DataKeyType.PUBLISHING_HOUSE, "Минск");
        data3.put(DataKeyType.AUTHOR, "Лев");
        List<Book> addedBook3 = new ArrayList<>();
        addedBook3.add(new Book("Война и мир", 2020, "Минск", Arrays.asList()));
        Map<String, List<Book>> expected3 = new HashMap<>();
        expected3.put(ResponseKeyType.ADDED_BOOK, addedBook3);
        Map<String, String> data4 = null;
        List<Book> filteredBooks4 = new ArrayList<>();
        Map<String, List<Book>> expected4 = new HashMap<>();
        expected4.put(ResponseKeyType.ADDED_BOOK, filteredBooks4);
        Map<String, String> data5 = new HashMap<>();
        List<Book> filteredBooks5 = new ArrayList<>();
        Map<String, List<Book>> expected5 = new HashMap<>();
        expected5.put(ResponseKeyType.ADDED_BOOK, filteredBooks5);
        Map<String, String> data6 = new HashMap<>();
        data6.put(DataKeyType.PUBLISHING_YEAR, "2020");
        data6.put(DataKeyType.PUBLISHING_HOUSE, "Минск");
        data6.put(DataKeyType.AUTHOR + "1", "Лев");
        List<Book> addedBook6 = new ArrayList<>();
        Map<String, List<Book>> expected6 = new HashMap<>();
        expected6.put(ResponseKeyType.ADDED_BOOK, addedBook6);
        Map<String, String> data7 = new HashMap<>();
        data7.put(DataKeyType.NAME, "Война и мир");
        data7.put(DataKeyType.PUBLISHING_YEAR, "1984");
        data7.put(DataKeyType.PUBLISHING_HOUSE, "Минск");
        data7.put(DataKeyType.AUTHOR + "1", "Лев Толстой");
        List<Book> addedBook7 = new ArrayList<>();
        Map<String, List<Book>> expected7 = new HashMap<>();
        expected7.put(ResponseKeyType.ERROR, addedBook7);
        return new Object[][]{
                {data1, expected1},
                {data2, expected2},
                {data3, expected3},
                {data4, expected4},
                {data5, expected5},
                {data6, expected6},
                {data7, expected7}
        };
    }

    @Test(dataProvider = "executePositiveData")
    public void executePositiveTest(Map<String, String> data,
                                    Map<String, List<Book>> expected) {
        Map<String, List<Book>> actual = addBookCommand.execute(data);
        boolean result;
        if (actual.get(ResponseKeyType.ADDED_BOOK) != null &&
                expected.get(ResponseKeyType.ADDED_BOOK) != null &&
                actual.get(ResponseKeyType.ADDED_BOOK).size() == 1 &&
                expected.get(ResponseKeyType.ADDED_BOOK).size() == 1) {
            result = actual.get(ResponseKeyType.ADDED_BOOK).get(0)
                    .equalsToBook(expected.get(ResponseKeyType.ADDED_BOOK).get(0));
        } else {
            result = actual.equals(expected);
        }
        assertTrue(result);
    }

    @DataProvider(name = "executeNegativeTest")
    public Object[][] createExecuteNegativeData() {
        Map<String, String> data1 = new HashMap<>();
        data1.put(DataKeyType.NAME, "Война и мир");
        data1.put(DataKeyType.PUBLISHING_YEAR, "2020");
        data1.put(DataKeyType.PUBLISHING_HOUSE, "Минск1");
        data1.put(DataKeyType.AUTHOR + "1", "Лев");
        List<Book> addedBook1 = new ArrayList<>();
        addedBook1.add(new Book("Война и мир", 2020, "Минск", Arrays.asList("Лев")));
        Map<String, List<Book>> expected1 = new HashMap<>();
        expected1.put(ResponseKeyType.ADDED_BOOK, addedBook1);
        Map<String, String> data2 = new HashMap<>();
        data2.put(DataKeyType.NAME, "This is very very long line with 43 symbols");
        data2.put(DataKeyType.PUBLISHING_YEAR, "2020");
        data2.put(DataKeyType.PUBLISHING_HOUSE, "Минск");
        data2.put(DataKeyType.AUTHOR + "1", "Лев");
        List<Book> addedBook2 = new ArrayList<>();
        addedBook2.add(new Book("Война и мир", 2020, "Минск", Arrays.asList("Лев")));
        Map<String, List<Book>> expected2 = new HashMap<>();
        expected2.put(ResponseKeyType.ADDED_BOOK, addedBook2);
        Map<String, String> data3 = new HashMap<>();
        data3.put(DataKeyType.NAME, "Война и мир");
        data3.put(DataKeyType.PUBLISHING_YEAR, "2020");
        data3.put(DataKeyType.PUBLISHING_HOUSE, "Минск");
        data3.put(DataKeyType.AUTHOR, "Лев");
        List<Book> addedBook3 = new ArrayList<>();
        Map<String, List<Book>> expected3 = new HashMap<>();
        expected3.put(ResponseKeyType.ADDED_BOOK, addedBook3);
        Map<String, String> data4 = null;
        Map<String, List<Book>> expected4 = new HashMap<>();
        Map<String, String> data5 = new HashMap<>();
        List<Book> filteredBooks5 = null;
        Map<String, List<Book>> expected5 = new HashMap<>();
        expected5.put(ResponseKeyType.ADDED_BOOK, filteredBooks5);
        Map<String, String> data6 = new HashMap<>();
        data6.put(DataKeyType.PUBLISHING_YEAR, "2020");
        data6.put(DataKeyType.PUBLISHING_HOUSE, "Минск");
        data6.put(DataKeyType.AUTHOR + "1", "Лев");
        List<Book> addedBook6 = new ArrayList<>();
        Map<String, List<Book>> expected6 = new HashMap<>();
        expected6.put(ResponseKeyType.ERROR, addedBook6);
        Map<String, String> data7 = new HashMap<>();
        data7.put(DataKeyType.NAME, "Война и мир");
        data7.put(DataKeyType.PUBLISHING_YEAR, "1984");
        data7.put(DataKeyType.PUBLISHING_HOUSE, "Минск");
        data7.put(DataKeyType.AUTHOR + "1", "Лев Толстой");
        List<Book> addedBook7 = new ArrayList<>();
        Map<String, List<Book>> expected7 = new HashMap<>();
        expected7.put(ResponseKeyType.ADDED_BOOK, addedBook7);
        return new Object[][]{
                {data1, expected1},
                {data2, expected2},
                {data3, expected3},
                {data4, expected4},
                {data5, expected5},
                {data6, expected6},
                {data7, expected7}
        };
    }

    @Test(dataProvider = "executeNegativeTest")
    public void executeNegativeTest(Map<String, String> data,
                                    Map<String, List<Book>> expected) {
        Map<String, List<Book>> actual = addBookCommand.execute(data);
        boolean result;
        if (actual.get(ResponseKeyType.ADDED_BOOK) != null &&
                expected.get(ResponseKeyType.ADDED_BOOK) != null &&
                actual.get(ResponseKeyType.ADDED_BOOK).size() == 1 &&
                expected.get(ResponseKeyType.ADDED_BOOK).size() == 1) {
            result = actual.get(ResponseKeyType.ADDED_BOOK).get(0)
                    .equalsToBook(expected.get(ResponseKeyType.ADDED_BOOK).get(0));
        } else {
            result = actual.equals(expected);
        }
        assertFalse(result);
    }
}
