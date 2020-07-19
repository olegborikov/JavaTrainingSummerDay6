package test.borikov.day6.controller.command.impl;

import com.borikov.day6.controller.command.impl.FindBookByIdCommand;
import com.borikov.day6.controller.command.impl.constant.DataKeyName;
import com.borikov.day6.controller.command.impl.constant.ResponseKeyName;
import com.borikov.day6.exception.StorageException;
import com.borikov.day6.model.entity.Book;
import org.testng.annotations.*;
import test.borikov.day6.creator.BookStorageCreator;

import java.util.*;

import static org.testng.Assert.*;

public class FindBookByIdCommandTest {
    private FindBookByIdCommand findBookByIdCommand;
    private BookStorageCreator bookStorageCreator;

    @BeforeClass
    public void setUpClass() throws StorageException {
        findBookByIdCommand = new FindBookByIdCommand();
        bookStorageCreator = BookStorageCreator.getInstance();
        bookStorageCreator.setUpBookStorage();
    }

    @AfterClass
    public void tearDownClass() {
        findBookByIdCommand = null;
        bookStorageCreator = null;
    }

    @AfterMethod
    public void tearDownMethod() throws StorageException {
        bookStorageCreator.setUpBookStorage();
    }

    @DataProvider(name = "executePositiveData")
    public Object[][] createExecutePositiveData() {
        Map<String, String> data1 = new HashMap<>();
        data1.put(DataKeyName.ID, "1");
        List<Book> currentBook1 = new ArrayList<>();
        currentBook1.add(bookStorageCreator.getCreatedBooks().get(0));
        Map<String, List<Book>> expected1 = new HashMap<>();
        expected1.put(ResponseKeyName.CURRENT_BOOK, currentBook1);
        Map<String, String> data2 = new HashMap<>();
        data2.put(DataKeyName.ID, "9");
        List<Book> currentBook2 = new ArrayList<>();
        currentBook2.add(bookStorageCreator.getCreatedBooks().get(9));
        Map<String, List<Book>> expected2 = new HashMap<>();
        expected2.put(ResponseKeyName.CURRENT_BOOK, currentBook2);
        Map<String, String> data3 = new HashMap<>();
        data3.put(DataKeyName.ID, "-7");
        List<Book> currentBook3 = new ArrayList<>();
        Map<String, List<Book>> expected3 = new HashMap<>();
        expected3.put(ResponseKeyName.CURRENT_BOOK, currentBook3);
        Map<String, String> data4 = new HashMap<>();
        data4.put(DataKeyName.ID, "abc");
        List<Book> currentBook4 = new ArrayList<>();
        Map<String, List<Book>> expected4 = new HashMap<>();
        expected4.put(ResponseKeyName.CURRENT_BOOK, currentBook4);
        Map<String, String> data5 = null;
        List<Book> currentBook5 = new ArrayList<>();
        Map<String, List<Book>> expected5 = new HashMap<>();
        expected5.put(ResponseKeyName.CURRENT_BOOK, currentBook5);
        Map<String, String> data6 = new HashMap<>();
        List<Book> currentBook6 = new ArrayList<>();
        Map<String, List<Book>> expected6 = new HashMap<>();
        expected6.put(ResponseKeyName.CURRENT_BOOK, currentBook6);
        return new Object[][]{
                {data1, expected1},
                {data2, expected2},
                {data3, expected3},
                {data4, expected4},
                {data5, expected5},
                {data6, expected6}
        };
    }

    @Test(dataProvider = "executePositiveData")
    public void executePositiveTest(Map<String, String> data,
                                    Map<String, List<Book>> expected) {
        Map<String, List<Book>> actual = findBookByIdCommand.execute(data);
        assertEquals(actual, expected);
    }

    @DataProvider(name = "executeNegativeTest")
    public Object[][] createExecuteNegativeData() {
        Map<String, String> data1 = new HashMap<>();
        data1.put(DataKeyName.ID, "1");
        List<Book> currentBook1 = new ArrayList<>();
        Map<String, List<Book>> expected1 = new HashMap<>();
        expected1.put(ResponseKeyName.CURRENT_BOOK, currentBook1);
        Map<String, String> data2 = new HashMap<>();
        data2.put(DataKeyName.ID, "9");
        List<Book> currentBook2 = new ArrayList<>();
        currentBook2.add(bookStorageCreator.getCreatedBooks().get(2));
        Map<String, List<Book>> expected2 = new HashMap<>();
        expected2.put(ResponseKeyName.CURRENT_BOOK, currentBook2);
        Map<String, String> data3 = new HashMap<>();
        data3.put(DataKeyName.ID, "-7");
        List<Book> currentBook3 = new ArrayList<>();
        currentBook3.add(bookStorageCreator.getCreatedBooks().get(2));
        Map<String, List<Book>> expected3 = new HashMap<>();
        expected3.put(ResponseKeyName.CURRENT_BOOK, currentBook3);
        Map<String, String> data4 = new HashMap<>();
        data4.put(DataKeyName.ID, "abc");
        List<Book> currentBook4 = null;
        Map<String, List<Book>> expected4 = new HashMap<>();
        expected4.put(ResponseKeyName.CURRENT_BOOK, currentBook4);
        return new Object[][]{
                {data1, expected1},
                {data2, expected2},
                {data3, expected3},
                {data4, expected4}
        };
    }

    @Test(dataProvider = "executeNegativeTest")
    public void executeNegativeTest(Map<String, String> data,
                                    Map<String, List<Book>> expected) {
        Map<String, List<Book>> actual = findBookByIdCommand.execute(data);
        assertNotEquals(actual, expected);
    }
}
