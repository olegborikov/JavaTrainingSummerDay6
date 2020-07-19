package test.borikov.day6.controller.command.impl;

import com.borikov.day6.controller.command.impl.FindBooksByAuthorCommand;
import com.borikov.day6.controller.command.impl.constant.DataKeyType;
import com.borikov.day6.controller.command.impl.constant.ResponseKeyType;
import com.borikov.day6.exception.StorageException;
import com.borikov.day6.model.entity.Book;
import org.testng.annotations.*;
import test.borikov.day6.creator.BookStorageCreator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.testng.Assert.*;

public class FindBooksByAuthorCommandTest {
    private FindBooksByAuthorCommand findBooksByAuthorCommand;
    private BookStorageCreator bookStorageCreator;

    @BeforeClass
    public void setUpClass() throws StorageException {
        findBooksByAuthorCommand = new FindBooksByAuthorCommand();
        bookStorageCreator = BookStorageCreator.getInstance();
        bookStorageCreator.setUpBookStorage();
    }

    @AfterClass
    public void tearDownClass() {
        findBooksByAuthorCommand = null;
        bookStorageCreator = null;
    }

    @AfterMethod
    public void tearDownMethod() throws StorageException {
        bookStorageCreator.setUpBookStorage();
    }

    @DataProvider(name = "executePositiveData")
    public Object[][] createExecutePositiveData() {
        Map<String, String> data1 = new HashMap<>();
        data1.put(DataKeyType.AUTHOR, "Oleg");
        List<Book> filteredBooks1 = new ArrayList<>();
        filteredBooks1.add(bookStorageCreator.getCreatedBooks().get(3));
        filteredBooks1.add(bookStorageCreator.getCreatedBooks().get(7));
        filteredBooks1.add(bookStorageCreator.getCreatedBooks().get(9));
        Map<String, List<Book>> expected1 = new HashMap<>();
        expected1.put(ResponseKeyType.FILTERED_BOOKS, filteredBooks1);
        Map<String, String> data2 = new HashMap<>();
        data2.put(DataKeyType.AUTHOR, "Sapolsky");
        List<Book> filteredBooks2 = new ArrayList<>();
        filteredBooks2.add(bookStorageCreator.getCreatedBooks().get(5));
        Map<String, List<Book>> expected2 = new HashMap<>();
        expected2.put(ResponseKeyType.FILTERED_BOOKS, filteredBooks2);
        Map<String, String> data3 = new HashMap<>();
        data3.put(DataKeyType.AUTHOR, "qwe");
        List<Book> filteredBooks3 = new ArrayList<>();
        Map<String, List<Book>> expected3 = new HashMap<>();
        expected3.put(ResponseKeyType.FILTERED_BOOKS, filteredBooks3);
        Map<String, String> data4 = null;
        List<Book> filteredBooks4 = new ArrayList<>();
        Map<String, List<Book>> expected4 = new HashMap<>();
        expected4.put(ResponseKeyType.FILTERED_BOOKS, filteredBooks4);
        Map<String, String> data5 = new HashMap<>();
        List<Book> filteredBooks5 = new ArrayList<>();
        Map<String, List<Book>> expected5 = new HashMap<>();
        expected5.put(ResponseKeyType.FILTERED_BOOKS, filteredBooks5);
        return new Object[][]{
                {data1, expected1},
                {data2, expected2},
                {data3, expected3},
                {data4, expected4},
                {data5, expected5}
        };
    }

    @Test(dataProvider = "executePositiveData")
    public void executePositiveTest(Map<String, String> data,
                                    Map<String, List<Book>> expected) {
        Map<String, List<Book>> actual = findBooksByAuthorCommand.execute(data);
        assertEquals(actual, expected);
    }

    @DataProvider(name = "executeNegativeTest")
    public Object[][] createExecuteNegativeData() {
        Map<String, String> data1 = new HashMap<>();
        data1.put(DataKeyType.AUTHOR, "Oleg");
        List<Book> filteredBooks1 = new ArrayList<>();
        filteredBooks1.add(bookStorageCreator.getCreatedBooks().get(3));
        filteredBooks1.add(bookStorageCreator.getCreatedBooks().get(7));
        Map<String, List<Book>> expected1 = new HashMap<>();
        expected1.put(ResponseKeyType.FILTERED_BOOKS, filteredBooks1);
        Map<String, String> data2 = new HashMap<>();
        data2.put(DataKeyType.AUTHOR, "Sapolsky");
        List<Book> filteredBooks2 = new ArrayList<>();
        filteredBooks2.add(bookStorageCreator.getCreatedBooks().get(5));
        filteredBooks2.add(bookStorageCreator.getCreatedBooks().get(5));
        Map<String, List<Book>> expected2 = new HashMap<>();
        expected2.put(ResponseKeyType.FILTERED_BOOKS, filteredBooks2);
        Map<String, String> data3 = new HashMap<>();
        data3.put(DataKeyType.AUTHOR, "Oleg");
        List<Book> filteredBooks3 = new ArrayList<>();
        Map<String, List<Book>> expected3 = new HashMap<>();
        expected3.put(ResponseKeyType.FILTERED_BOOKS, filteredBooks3);
        Map<String, String> data4 = null;
        List<Book> filteredBooks4 = new ArrayList<>();
        Map<String, List<Book>> expected4 = new HashMap<>();
        expected4.put(ResponseKeyType.SORTED_BOOKS, filteredBooks4);
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
        Map<String, List<Book>> actual = findBooksByAuthorCommand.execute(data);
        assertNotEquals(actual, expected);
    }
}
