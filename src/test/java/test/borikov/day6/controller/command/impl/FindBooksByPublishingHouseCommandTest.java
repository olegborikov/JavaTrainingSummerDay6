package test.borikov.day6.controller.command.impl;

import com.borikov.day6.controller.command.impl.FindBooksByPublishingHouseCommand;
import com.borikov.day6.controller.command.impl.constant.DataKeyName;
import com.borikov.day6.controller.command.impl.constant.ResponseKeyName;
import com.borikov.day6.exception.StorageException;
import com.borikov.day6.model.entity.Book;
import org.testng.annotations.*;
import test.borikov.day6.creator.BookStorageCreator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.testng.Assert.*;

public class FindBooksByPublishingHouseCommandTest {
    private FindBooksByPublishingHouseCommand findBooksByPublishingHouseCommand;
    private BookStorageCreator bookStorageCreator;

    @BeforeClass
    public void setUpClass() throws StorageException {
        findBooksByPublishingHouseCommand = new FindBooksByPublishingHouseCommand();
        bookStorageCreator = BookStorageCreator.getInstance();
        bookStorageCreator.setUpBookStorage();
    }

    @AfterClass
    public void tearDownClass() {
        findBooksByPublishingHouseCommand = null;
        bookStorageCreator = null;
    }

    @AfterMethod
    public void tearDownMethod() throws StorageException {
        bookStorageCreator.setUpBookStorage();
    }

    @DataProvider(name = "executePositiveData")
    public Object[][] createExecutePositiveData() {
        Map<String, String> data1 = new HashMap<>();
        data1.put(DataKeyName.PUBLISHING_HOUSE, "Минск");
        List<Book> filteredBooks1 = new ArrayList<>();
        filteredBooks1.add(bookStorageCreator.getCreatedBooks().get(0));
        filteredBooks1.add(bookStorageCreator.getCreatedBooks().get(1));
        filteredBooks1.add(bookStorageCreator.getCreatedBooks().get(6));
        Map<String, List<Book>> expected1 = new HashMap<>();
        expected1.put(ResponseKeyName.FILTERED_BOOKS, filteredBooks1);
        Map<String, String> data2 = new HashMap<>();
        data2.put(DataKeyName.PUBLISHING_HOUSE, "Москва");
        List<Book> filteredBooks2 = new ArrayList<>();
        filteredBooks2.add(bookStorageCreator.getCreatedBooks().get(4));
        filteredBooks2.add(bookStorageCreator.getCreatedBooks().get(7));
        Map<String, List<Book>> expected2 = new HashMap<>();
        expected2.put(ResponseKeyName.FILTERED_BOOKS, filteredBooks2);
        Map<String, String> data3 = new HashMap<>();
        data3.put(DataKeyName.PUBLISHING_HOUSE, "qwe");
        List<Book> filteredBooks3 = new ArrayList<>();
        Map<String, List<Book>> expected3 = new HashMap<>();
        expected3.put(ResponseKeyName.FILTERED_BOOKS, filteredBooks3);
        Map<String, String> data4 = null;
        List<Book> filteredBooks4 = new ArrayList<>();
        Map<String, List<Book>> expected4 = new HashMap<>();
        expected4.put(ResponseKeyName.FILTERED_BOOKS, filteredBooks4);
        Map<String, String> data5 = new HashMap<>();
        List<Book> filteredBooks5 = new ArrayList<>();
        Map<String, List<Book>> expected5 = new HashMap<>();
        expected5.put(ResponseKeyName.FILTERED_BOOKS, filteredBooks5);
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
        Map<String, List<Book>> actual =
                findBooksByPublishingHouseCommand.execute(data);
        assertEquals(actual, expected);
    }

    @DataProvider(name = "executeNegativeTest")
    public Object[][] createExecuteNegativeData() {
        Map<String, String> data1 = new HashMap<>();
        data1.put(DataKeyName.PUBLISHING_HOUSE, "Минск");
        List<Book> filteredBooks1 = new ArrayList<>();
        filteredBooks1.add(bookStorageCreator.getCreatedBooks().get(0));
        filteredBooks1.add(bookStorageCreator.getCreatedBooks().get(1));
        Map<String, List<Book>> expected1 = new HashMap<>();
        expected1.put(ResponseKeyName.FILTERED_BOOKS, filteredBooks1);
        Map<String, String> data2 = new HashMap<>();
        data2.put(DataKeyName.PUBLISHING_HOUSE, "Москва");
        List<Book> filteredBooks2 = new ArrayList<>();
        filteredBooks2.add(bookStorageCreator.getCreatedBooks().get(4));
        filteredBooks2.add(bookStorageCreator.getCreatedBooks().get(7));
        filteredBooks2.add(bookStorageCreator.getCreatedBooks().get(7));
        Map<String, List<Book>> expected2 = new HashMap<>();
        expected2.put(ResponseKeyName.FILTERED_BOOKS, filteredBooks2);
        Map<String, String> data3 = new HashMap<>();
        data3.put(DataKeyName.PUBLISHING_HOUSE, "Киев");
        List<Book> filteredBooks3 = new ArrayList<>();
        Map<String, List<Book>> expected3 = new HashMap<>();
        expected3.put(ResponseKeyName.FILTERED_BOOKS, filteredBooks3);
        Map<String, String> data4 = null;
        List<Book> filteredBooks4 = new ArrayList<>();
        Map<String, List<Book>> expected4 = new HashMap<>();
        expected4.put(ResponseKeyName.SORTED_BOOKS, filteredBooks4);
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
        Map<String, List<Book>> actual =
                findBooksByPublishingHouseCommand.execute(data);
        assertNotEquals(actual, expected);
    }
}
