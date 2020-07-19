package test.borikov.day6.controller.command.impl;

import com.borikov.day6.controller.command.impl.RemoveBookCommand;
import com.borikov.day6.controller.command.impl.constant.DataKeyName;
import com.borikov.day6.controller.command.impl.constant.ResponseKeyName;
import com.borikov.day6.exception.StorageException;
import com.borikov.day6.model.entity.Book;
import org.testng.annotations.*;
import test.borikov.day6.creator.BookStorageCreator;

import java.util.*;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class RemoveBookCommandTest {
    private RemoveBookCommand removeBookCommand;
    private BookStorageCreator bookStorageCreator;

    @BeforeClass
    public void setUpClass() throws StorageException {
        removeBookCommand = new RemoveBookCommand();
        bookStorageCreator = BookStorageCreator.getInstance();
        bookStorageCreator.setUpBookStorage();
    }

    @AfterClass
    public void tearDownClass() {
        removeBookCommand = null;
        bookStorageCreator = null;
    }

    @AfterMethod
    public void tearDownMethod() throws StorageException {
        bookStorageCreator.setUpBookStorage();
    }

    @DataProvider(name = "executePositiveData")
    public Object[][] createExecutePositiveData() {
        Map<String, String> data1 = new HashMap<>();
        data1.put(DataKeyName.NAME, "Война и мир");
        data1.put(DataKeyName.PUBLISHING_YEAR, "1990");
        data1.put(DataKeyName.PUBLISHING_HOUSE, "Москва");
        data1.put(DataKeyName.AUTHOR + "1", "Лев Толстой");
        List<Book> removedBook1 = new ArrayList<>();
        removedBook1.add(new Book("Война и мир", 1990,
                "Москва", Arrays.asList("Лев Толстой")));
        Map<String, List<Book>> expected1 = new HashMap<>();
        expected1.put(ResponseKeyName.REMOVED_BOOK, removedBook1);
        Map<String, String> data2 = new HashMap<>();
        data2.put(DataKeyName.NAME, "This is very very long line with 43 symbols");
        data2.put(DataKeyName.PUBLISHING_YEAR, "2020");
        data2.put(DataKeyName.PUBLISHING_HOUSE, "Минск");
        data2.put(DataKeyName.AUTHOR + "1", "Лев");
        List<Book> removedBook2 = new ArrayList<>();
        Map<String, List<Book>> expected2 = new HashMap<>();
        expected2.put(ResponseKeyName.REMOVED_BOOK, removedBook2);
        Map<String, String> data3 = new HashMap<>();
        data3.put(DataKeyName.NAME, "История Минска");
        data3.put(DataKeyName.PUBLISHING_YEAR, "1000");
        data3.put(DataKeyName.PUBLISHING_HOUSE, "Минск");
        data3.put(DataKeyName.AUTHOR, "Лев");
        List<Book> addedBook3 = new ArrayList<>();
        addedBook3.add(bookStorageCreator.getCreatedBooks().get(6));
        Map<String, List<Book>> expected3 = new HashMap<>();
        expected3.put(ResponseKeyName.REMOVED_BOOK, addedBook3);
        Map<String, String> data4 = null;
        List<Book> filteredBooks4 = new ArrayList<>();
        Map<String, List<Book>> expected4 = new HashMap<>();
        expected4.put(ResponseKeyName.REMOVED_BOOK, filteredBooks4);
        Map<String, String> data5 = new HashMap<>();
        List<Book> filteredBooks5 = new ArrayList<>();
        Map<String, List<Book>> expected5 = new HashMap<>();
        expected5.put(ResponseKeyName.REMOVED_BOOK, filteredBooks5);
        Map<String, String> data6 = new HashMap<>();
        data6.put(DataKeyName.PUBLISHING_YEAR, "2020");
        data6.put(DataKeyName.PUBLISHING_HOUSE, "Минск");
        data6.put(DataKeyName.AUTHOR + "1", "Лев");
        List<Book> addedBook6 = new ArrayList<>();
        Map<String, List<Book>> expected6 = new HashMap<>();
        expected6.put(ResponseKeyName.REMOVED_BOOK, addedBook6);
        Map<String, String> data7 = new HashMap<>();
        data7.put(DataKeyName.NAME, "Война и мир1");
        data7.put(DataKeyName.PUBLISHING_YEAR, "1984");
        data7.put(DataKeyName.PUBLISHING_HOUSE, "Минск");
        data7.put(DataKeyName.AUTHOR + "1", "Лев Толстой");
        List<Book> addedBook7 = new ArrayList<>();
        Map<String, List<Book>> expected7 = new HashMap<>();
        expected7.put(ResponseKeyName.ERROR, addedBook7);
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
        Map<String, List<Book>> actual = removeBookCommand.execute(data);
        boolean result;
        if (actual.get(ResponseKeyName.REMOVED_BOOK) != null &&
                expected.get(ResponseKeyName.REMOVED_BOOK) != null &&
                actual.get(ResponseKeyName.REMOVED_BOOK).size() == 1 &&
                expected.get(ResponseKeyName.REMOVED_BOOK).size() == 1) {
            result = actual.get(ResponseKeyName.REMOVED_BOOK).get(0)
                    .equalsToBook(expected.get(ResponseKeyName.REMOVED_BOOK).get(0));
        } else {
            result = actual.equals(expected);
        }
        assertTrue(result);
    }

    @DataProvider(name = "executeNegativeTest")
    public Object[][] createExecuteNegativeData() {
        Map<String, String> data1 = new HashMap<>();
        data1.put(DataKeyName.NAME, "Война и мир");
        data1.put(DataKeyName.PUBLISHING_YEAR, "1990");
        data1.put(DataKeyName.PUBLISHING_HOUSE, "Москва");
        data1.put(DataKeyName.AUTHOR + "1", "Лев Толстой");
        List<Book> removedBook1 = new ArrayList<>();
        removedBook1.add(new Book("Война и мир", 1991,
                "Москва", Arrays.asList("Лев Толстой")));
        Map<String, List<Book>> expected1 = new HashMap<>();
        expected1.put(ResponseKeyName.REMOVED_BOOK, removedBook1);
        Map<String, String> data2 = new HashMap<>();
        data2.put(DataKeyName.NAME, "This is very very long line with 43 symbols");
        data2.put(DataKeyName.PUBLISHING_YEAR, "2020");
        data2.put(DataKeyName.PUBLISHING_HOUSE, "Минск");
        data2.put(DataKeyName.AUTHOR + "1", "Лев");
        List<Book> removedBook2 = new ArrayList<>();
        removedBook2.add(null);
        Map<String, List<Book>> expected2 = new HashMap<>();
        expected2.put(ResponseKeyName.REMOVED_BOOK, removedBook2);
        Map<String, String> data3 = new HashMap<>();
        data3.put(DataKeyName.NAME, "История Минска");
        data3.put(DataKeyName.PUBLISHING_YEAR, "1000");
        data3.put(DataKeyName.PUBLISHING_HOUSE, "Минск");
        data3.put(DataKeyName.AUTHOR, "Лев");
        List<Book> addedBook3 = new ArrayList<>();
        addedBook3.add(bookStorageCreator.getCreatedBooks().get(3));
        Map<String, List<Book>> expected3 = new HashMap<>();
        expected3.put(ResponseKeyName.REMOVED_BOOK, addedBook3);
        Map<String, String> data4 = null;
        List<Book> filteredBooks4 = null;
        Map<String, List<Book>> expected4 = new HashMap<>();
        expected4.put(ResponseKeyName.REMOVED_BOOK, filteredBooks4);
        Map<String, String> data5 = new HashMap<>();
        List<Book> filteredBooks5 = null;
        Map<String, List<Book>> expected5 = new HashMap<>();
        expected5.put(ResponseKeyName.REMOVED_BOOK, filteredBooks5);
        Map<String, String> data6 = new HashMap<>();
        data6.put(DataKeyName.PUBLISHING_YEAR, "2020");
        data6.put(DataKeyName.PUBLISHING_HOUSE, "Минск");
        data6.put(DataKeyName.AUTHOR + "1", "Лев");
        List<Book> addedBook6 = new ArrayList<>();
        Map<String, List<Book>> expected6 = new HashMap<>();
        expected6.put(ResponseKeyName.ERROR, addedBook6);
        Map<String, String> data7 = new HashMap<>();
        data7.put(DataKeyName.NAME, "Война и мир1");
        data7.put(DataKeyName.PUBLISHING_YEAR, "1984");
        data7.put(DataKeyName.PUBLISHING_HOUSE, "Минск");
        data7.put(DataKeyName.AUTHOR + "1", "Лев Толстой");
        List<Book> addedBook7 = new ArrayList<>();
        Map<String, List<Book>> expected7 = new HashMap<>();
        expected7.put(ResponseKeyName.REMOVED_BOOK, addedBook7);
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
        Map<String, List<Book>> actual = removeBookCommand.execute(data);
        boolean result;
        if (actual.get(ResponseKeyName.REMOVED_BOOK) != null &&
                expected.get(ResponseKeyName.REMOVED_BOOK) != null &&
                actual.get(ResponseKeyName.REMOVED_BOOK).size() == 1 &&
                expected.get(ResponseKeyName.REMOVED_BOOK).size() == 1) {
            result = actual.get(ResponseKeyName.REMOVED_BOOK).get(0)
                    .equalsToBook(expected.get(ResponseKeyName.REMOVED_BOOK).get(0));
        } else {
            result = actual.equals(expected);
        }
        assertFalse(result);
    }
}
