/*
package test.borikov.day6.controller;

import com.borikov.day6.controller.BookController;
import com.borikov.day6.controller.command.impl.constant.DataKeyType;
import com.borikov.day6.model.entity.Book;
import test.borikov.day6.creator.BookStorageCreator;
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.testng.Assert.*;

public class BookControllerTest {
    private BookController bookController;

    @BeforeClass
    public void setUpClass() {
        bookController = new BookController();
        BookStorageCreator.setUpBookStorage();
    }

    @AfterClass
    public void tearDownClass() {
        bookController = null;
    }

    @AfterMethod()
    public void tearDownMethod() {
        BookStorageCreator.setUpBookStorage();
    }

    @DataProvider(name = "processRequestNegativeData")
    public Object[][] createProcessRequestNegativeData() {
        String request1 = "addbook";
        String request2 = "addBook";
        String request3 = "add book";
        Map<String, String> data = new HashMap<>();
        Map<String, List<Book>> expected = new HashMap<>();
        expected.put("allBooks", BookStorageCreator.getCreatedBooks());
        return new Object[][]{
                {request1, data, expected},
                {request2, data, expected},
                {request3, data, expected}
        };
    }

    @Test(dataProvider = "processRequestNegativeData", enabled = false)
    public void processRequestNegativeTest(String request,
                                                  Map<String, String> data,
                                                  Map<String, List<Book>> expected) {
        Map<String, List<Book>> actual = bookController.processRequest(request, data);
        assertEquals(actual, expected);
    }

    @DataProvider(name = "processRequestAddBookPositiveData")
    public Object[][] createProcessRequestAddBookPositiveData() {
        String request = "add_book";
        Map<String, String> data1 = new HashMap<>();
        data1.put("name", "Qwerty");
        data1.put("price", "100");
        data1.put("publishingHouse", "Minsk");
        data1.put("author1", "Oleg");
        data1.put("author2", "Pavel");
        Map<String, String> data2 = new HashMap<>();
        data2.put("name", "???");
        data2.put("price", "100.1");
        data2.put("publishingHouse", "Москва");
        Map<String, String> data3 = new HashMap<>();
        data3.put("name", "Ты");
        data3.put("price", "10");
        data3.put("publishingHouse", "Minsk");
        data3.put("author1", "Oleg");
        Map<String, String> data4 = new HashMap<>();
        data4.put("name", "Ты");
        data4.put("price", "10");
        data4.put("publishingHouse", "Minsk");
        data4.put("author2", "Oleg");
        Map<String, List<Book>> expected = new HashMap<>();
        return new Object[][]{
                {request, data1, expected},
                {request, data2, expected},
                {request, data3, expected},
                {request, data4, expected},
        };
    }

    @Test(dataProvider = "processRequestAddBookPositiveData", enabled = false)
    public void processRequestAddBookPositiveTest(String request,
                                                  Map<String, String> data,
                                                  Map<String, List<Book>> expected) {
        Map<String, List<Book>> actual = bookController.processRequest(request, data);
        List<Book> addedBook = new ArrayList<>();
        //addedBook.add(bookStorage.get().get(bookStorage.get().size() - 1));
        expected.put("addedBook", addedBook);
        assertEquals(actual, expected);
    }

    @DataProvider(name = "processRequestAddBookNegativeData")
    public Object[][] createProcessRequestAddBookNegativeData() {
        String request = "add_book";
        Map<String, String> data1 = new HashMap<>();
        Map<String, String> data2 = null;
        Map<String, String> data3 = new HashMap<>();
        data3.put("name", "Ты");
        data3.put("price", "10k");
        data3.put("publishingHouse", "Minsk");
        data3.put("author1", "Oleg");
        Map<String, String> data4 = new HashMap<>();
        data4.put("name", "Ты");
        data4.put("price", "10");
        data4.put("publishingHouse", "Minsk");
        data4.put("author1", "Olegasdasdasdassgdfgdfgdf dfg dfg dfg dfg");
        Map<String, List<Book>> expected = new HashMap<>();
        expected.put("addedBook", new ArrayList<>());
        return new Object[][]{
                {request, data1, expected},
                {request, data2, expected},
                {request, data3, expected},
                {request, data4, expected},
        };
    }

    @Test(dataProvider = "processRequestAddBookNegativeData", enabled = false)
    public void processRequestAddBookNegativeTest(String request,
                                                  Map<String, String> data,
                                                  Map<String, List<Book>> expected) {
        Map<String, List<Book>> actual = bookController.processRequest(request, data);
        assertEquals(actual, expected);
    }

    @DataProvider(name = "processRequestFindAllBooksPositiveData")
    public Object[][] createProcessRequestFindAllBooksPositiveData() {
        String request = "find_all_books";
        Map<String, String> data1 = null;
        Map<String, String> data2 = new HashMap<>();
        Map<String, String> data3 = new HashMap<>();
        data3.put("first", "first");
        Map<String, List<Book>> expected = new HashMap<>();
        expected.put(DataKeyType.ALL_BOOKS, BookStorageCreator.getCreatedBooks());
        return new Object[][]{
                {request, data1, expected},
                {request, data2, expected},
                {request, data3, expected},
        };
    }

    @Test(dataProvider = "processRequestFindAllBooksPositiveData")
    public void processRequestFindAllBooksPositiveTest(String request,
                                                       Map<String, String> data,
                                                       Map<String, List<Book>> expected) {
        Map<String, List<Book>> actual = bookController.processRequest(request, data);
        assertEquals(actual, expected);
    }

    @DataProvider(name = "processRequestFindAllBooksNegativeData")
    public Object[][] createProcessRequestFindAllBooksNegativeData() {
        String request = "find_book_by_id";
        Map<String, String> data1 = null;
        Map<String, String> data2 = new HashMap<>();
        Map<String, List<Book>> expected1 = new HashMap<>();
        expected1.put("allbooks", BookStorageCreator.getCreatedBooks());
        Map<String, List<Book>> expected2 = new HashMap<>();
        return new Object[][]{
                {request, data1, expected1},
                {request, data2, expected2},
        };
    }

    @Test(dataProvider = "processRequestFindAllBooksNegativeData")
    public void processRequestFindAllBooksNegativeTest(String request,
                                                       Map<String, String> data,
                                                       Map<String, List<Book>> expected) {
        Map<String, List<Book>> actual = bookController.processRequest(request, data);
        assertNotEquals(actual, expected);
    }

    @DataProvider(name = "processRequestFindBooksByIdPositiveData")
    public Object[][] createProcessRequestFindBooksByIdPositiveData() {
        String request = "find_book_by_id";
        Map<String, String> data1 = new HashMap<>();
        data1.put("id", "5");
        Map<String, List<Book>> expected1 = new HashMap<>();
        List<Book> filteredBook1 = new ArrayList<>();
        filteredBook1.add(BookStorageCreator.getCreatedBooks().get(4));
        expected1.put("filteredBook", filteredBook1);
        Map<String, String> data2 = new HashMap<>();
        data2.put("id", "1");
        Map<String, List<Book>> expected2 = new HashMap<>();
        List<Book> filteredBook2 = new ArrayList<>();
        filteredBook2.add(BookStorageCreator.getCreatedBooks().get(0));
        expected2.put("filteredBook", filteredBook2);
        Map<String, String> data3 = new HashMap<>();
        data3.put("id", "4");
        Map<String, List<Book>> expected3 = new HashMap<>();
        List<Book> filteredBook3 = new ArrayList<>();
        filteredBook3.add(BookStorageCreator.getCreatedBooks().get(3));
        expected3.put(DataKeyType.FILTERED_BOOK, filteredBook3);
        return new Object[][]{
                {request, data1, expected1},
                {request, data2, expected2},
                {request, data3, expected3},
        };
    }

    @Test(dataProvider = "processRequestFindBooksByIdPositiveData")
    public void processRequestFindBooksByIdPositiveTest(String request,
                                                        Map<String, String> data,
                                                        Map<String, List<Book>> expected) {
        Map<String, List<Book>> actual = bookController.processRequest(request, data);
        assertEquals(actual, expected);
    }

    @DataProvider(name = "processRequestFindBooksByIdNegativeData")
    public Object[][] createProcessRequestFindBooksByIdNegativeData() {
        String request = "find_book_by_id";
        Map<String, String> data1 = null;
        Map<String, String> data2 = new HashMap<>();
        data2.put("id", "1.");
        Map<String, String> data3 = new HashMap<>();
        data3.put("id", "100001");
        Map<String, List<Book>> expected = new HashMap<>();
        expected.put(DataKeyType.FILTERED_BOOK, new ArrayList<>());
        return new Object[][]{
                {request, data1, expected},
                {request, data2, expected},
                {request, data3, expected},
        };
    }

    @Test(dataProvider = "processRequestFindBooksByIdNegativeData")
    public void processRequestFindBooksByIdNegativeTest(String request,
                                                        Map<String, String> data,
                                                        Map<String, List<Book>> expected) {
        Map<String, List<Book>> actual = bookController.processRequest(request, data);
        assertEquals(actual, expected);
    }

    @DataProvider(name = "processRequestFindBooksByAuthorPositiveData")
    public Object[][] createProcessRequestFindBooksByAuthorPositiveData() {
        String request = "find_books_by_author";
        Map<String, String> data1 = new HashMap<>();
        data1.put("author", "Oleg");
        Map<String, List<Book>> expected1 = new HashMap<>();
        List<Book> filteredBook1 = new ArrayList<>();
        filteredBook1.add(BookStorageCreator.getCreatedBooks().get(3));
        filteredBook1.add(BookStorageCreator.getCreatedBooks().get(7));
        filteredBook1.add(BookStorageCreator.getCreatedBooks().get(9));
        expected1.put("filteredBooks", filteredBook1);
        Map<String, String> data2 = new HashMap<>();
        data2.put("author", "Sapolsky");
        Map<String, List<Book>> expected2 = new HashMap<>();
        List<Book> filteredBook2 = new ArrayList<>();
        filteredBook2.add(BookStorageCreator.getCreatedBooks().get(5));
        expected2.put("filteredBooks", filteredBook2);
        Map<String, String> data3 = new HashMap<>();
        data3.put("author", "Robert");
        Map<String, List<Book>> expected3 = new HashMap<>();
        List<Book> filteredBook3 = new ArrayList<>();
        filteredBook3.add(BookStorageCreator.getCreatedBooks().get(3));
        filteredBook3.add(BookStorageCreator.getCreatedBooks().get(5));
        expected3.put("filteredBooks", filteredBook3);
        List<Book> filteredBook4 = new ArrayList<>();
        Map<String, List<Book>> expected4 = new HashMap<>();
        expected4.put(DataKeyType.FILTERED_BOOKS, filteredBook4);
        return new Object[][]{
                {request, data1, expected1},
                {request, data2, expected2},
                {request, data3, expected3},
                {request, null, expected4}
        };
    }

    @Test(dataProvider = "processRequestFindBooksByAuthorPositiveData")
    public void processRequestFindBooksByAuthorPositiveTest(String request,
                                                            Map<String, String> data,
                                                            Map<String, List<Book>> expected) {
        Map<String, List<Book>> actual = bookController.processRequest(request, data);
        assertEquals(actual, expected);
    }

    @DataProvider(name = "processRequestFindBooksByAuthorNegativeData")
    public Object[][] createProcessRequestFindBooksByAuthorNegativeData() {
        String request = "find_books_by_author";
        Map<String, String> data1 = null;
        Map<String, String> data2 = new HashMap<>();
        data2.put("author", "dsa");
        Map<String, String> data3 = new HashMap<>();
        data3.put("author", "Olegewqe qwe qwe qwe qwe qwe qwe qw eqwe qw eewq ");
        Map<String, List<Book>> expected = new HashMap<>();
        expected.put(DataKeyType.FILTERED_BOOKS, new ArrayList<>());
        return new Object[][]{
                {request, data1, expected},
                {request, data2, expected},
                {request, data3, expected},
        };
    }

    @Test(dataProvider = "processRequestFindBooksByAuthorNegativeData")
    public void processRequestFindBooksByAuthorNegativeTest(String request,
                                                            Map<String, String> data,
                                                            Map<String, List<Book>> expected) {
        Map<String, List<Book>> actual = bookController.processRequest(request, data);
        assertEquals(actual, expected);
    }

    @DataProvider(name = "processRequestFindBooksByNamePositiveData")
    public Object[][] createProcessRequestFindBooksByNamePositiveData() {
        String request = "find_books_by_name";
        Map<String, String> data1 = new HashMap<>();
        data1.put("name", "Война и мир");
        Map<String, List<Book>> expected1 = new HashMap<>();
        List<Book> filteredBook1 = new ArrayList<>();
        filteredBook1.add(BookStorageCreator.getCreatedBooks().get(0));
        filteredBook1.add(BookStorageCreator.getCreatedBooks().get(4));
        expected1.put("filteredBooks", filteredBook1);
        Map<String, String> data2 = new HashMap<>();
        data2.put("name", "Метро 2033");
        Map<String, List<Book>> expected2 = new HashMap<>();
        List<Book> filteredBook2 = new ArrayList<>();
        filteredBook2.add(BookStorageCreator.getCreatedBooks().get(8));
        expected2.put("filteredBooks", filteredBook2);
        Map<String, String> data3 = new HashMap<>();
        data3.put("name", "Я");
        Map<String, List<Book>> expected3 = new HashMap<>();
        List<Book> filteredBook3 = new ArrayList<>();
        filteredBook3.add(BookStorageCreator.getCreatedBooks().get(7));
        expected3.put("filteredBooks", filteredBook3);
        return new Object[][]{
                {request, data1, expected1},
                {request, data2, expected2},
                {request, data3, expected3},
        };
    }

    @Test(dataProvider = "processRequestFindBooksByNamePositiveData")
    public void processRequestFindBooksByNamePositiveTest(String request,
                                                          Map<String, String> data,
                                                          Map<String, List<Book>> expected) {
        Map<String, List<Book>> actual = bookController.processRequest(request, data);
        assertEquals(actual, expected);
    }

    @DataProvider(name = "processRequestFindBooksByNameNegativeData")
    public Object[][] createProcessRequestFindBooksByNameNegativeData() {
        String request = "find_books_by_name";
        Map<String, String> data1 = null;
        Map<String, String> data2 = new HashMap<>();
        data2.put("name", "dsa");
        Map<String, String> data3 = new HashMap<>();
        data3.put("name", "Olegewqe qwe qwe qwe qwe qwe qwe qw eqwe qw eewq ");
        Map<String, List<Book>> expected = new HashMap<>();
        expected.put("filteredBooks", new ArrayList<>());
        return new Object[][]{
                {request, data1, expected},
                {request, data2, expected},
                {request, data3, expected},
        };
    }

    @Test(dataProvider = "processRequestFindBooksByNameNegativeData")
    public void processRequestFindBooksByNameNegativeTest(String request,
                                                          Map<String, String> data,
                                                          Map<String, List<Book>> expected) {
        Map<String, List<Book>> actual = bookController.processRequest(request, data);
        assertEquals(actual, expected);
    }

    @DataProvider(name = "processRequestFindBooksByPricePositiveData")
    public Object[][] createProcessRequestFindBooksByPricePositiveData() {
        String request = "find_books_by_price";
        Map<String, String> data1 = new HashMap<>();
        data1.put("price", "100");
        Map<String, List<Book>> expected1 = new HashMap<>();
        List<Book> filteredBook1 = new ArrayList<>();
        filteredBook1.add(BookStorageCreator.getCreatedBooks().get(0));
        filteredBook1.add(BookStorageCreator.getCreatedBooks().get(4));
        filteredBook1.add(BookStorageCreator.getCreatedBooks().get(8));
        expected1.put("filteredBooks", filteredBook1);
        Map<String, String> data2 = new HashMap<>();
        data2.put("price", "1000");
        Map<String, List<Book>> expected2 = new HashMap<>();
        List<Book> filteredBook2 = new ArrayList<>();
        filteredBook2.add(BookStorageCreator.getCreatedBooks().get(2));
        expected2.put("filteredBooks", filteredBook2);
        Map<String, String> data3 = new HashMap<>();
        data3.put("price", "10");
        Map<String, List<Book>> expected3 = new HashMap<>();
        List<Book> filteredBook3 = new ArrayList<>();
        filteredBook3.add(BookStorageCreator.getCreatedBooks().get(6));
        expected3.put("filteredBooks", filteredBook3);
        return new Object[][]{
                {request, data1, expected1},
                {request, data2, expected2},
                {request, data3, expected3},
        };
    }

    @Test(dataProvider = "processRequestFindBooksByPricePositiveData")
    public void processRequestFindBooksByPricePositiveTest(String request,
                                                           Map<String, String> data,
                                                           Map<String, List<Book>> expected) {
        Map<String, List<Book>> actual = bookController.processRequest(request, data);
        assertEquals(actual, expected);
    }

    @DataProvider(name = "processRequestFindBooksByPriceNegativeData")
    public Object[][] createProcessRequestFindBooksByPriceNegativeData() {
        String request = "find_books_by_price";
        Map<String, String> data1 = null;
        Map<String, String> data2 = new HashMap<>();
        data2.put("price", "1001");
        Map<String, String> data3 = new HashMap<>();
        data3.put("price", "10j");
        Map<String, List<Book>> expected = new HashMap<>();
        expected.put("filteredBooks", new ArrayList<>());
        return new Object[][]{
                {request, data1, expected},
                {request, data2, expected},
                {request, data3, expected},
        };
    }

    @Test(dataProvider = "processRequestFindBooksByPriceNegativeData")
    public void processRequestFindBooksByPriceNegativeTest(String request,
                                                           Map<String, String> data,
                                                           Map<String, List<Book>> expected) {
        Map<String, List<Book>> actual = bookController.processRequest(request, data);
        assertEquals(actual, expected);
    }

    @DataProvider(name = "processRequestFindBooksByPublishingHousePositiveData")
    public Object[][] createProcessRequestFindBooksByPublishingHousePositiveData() {
        String request = "find_books_by_publishing_house";
        Map<String, String> data1 = new HashMap<>();
        data1.put("publishingHouse", "Минск");
        Map<String, List<Book>> expected1 = new HashMap<>();
        List<Book> filteredBook1 = new ArrayList<>();
        filteredBook1.add(BookStorageCreator.getCreatedBooks().get(0));
        filteredBook1.add(BookStorageCreator.getCreatedBooks().get(1));
        filteredBook1.add(BookStorageCreator.getCreatedBooks().get(6));
        expected1.put("filteredBooks", filteredBook1);
        Map<String, String> data2 = new HashMap<>();
        data2.put("publishingHouse", "Москва");
        Map<String, List<Book>> expected2 = new HashMap<>();
        List<Book> filteredBook2 = new ArrayList<>();
        filteredBook2.add(BookStorageCreator.getCreatedBooks().get(4));
        filteredBook2.add(BookStorageCreator.getCreatedBooks().get(7));
        expected2.put("filteredBooks", filteredBook2);
        Map<String, String> data3 = new HashMap<>();
        data3.put("publishingHouse", "London");
        Map<String, List<Book>> expected3 = new HashMap<>();
        List<Book> filteredBook3 = new ArrayList<>();
        filteredBook3.add(BookStorageCreator.getCreatedBooks().get(5));
        filteredBook3.add(BookStorageCreator.getCreatedBooks().get(9));
        expected3.put("filteredBooks", filteredBook3);
        return new Object[][]{
                {request, data1, expected1},
                {request, data2, expected2},
                {request, data3, expected3},
        };
    }

    @Test(dataProvider = "processRequestFindBooksByPublishingHousePositiveData")
    public void processRequestFindBooksByPublishingHousePositiveTest(String request,
                                                                     Map<String, String> data,
                                                                     Map<String, List<Book>> expected) {
        Map<String, List<Book>> actual = bookController.processRequest(request, data);
        assertEquals(actual, expected);
    }

    @DataProvider(name = "processRequestFindBooksByPublishingHouseNegativeData")
    public Object[][] createProcessRequestFindBooksByPublishingHouseNegativeData() {
        String request = "find_books_by_publishing_house";
        Map<String, String> data1 = new HashMap<>();
        data1.put("publishingHouse", "Витебск");
        Map<String, String> data2 = null;
        Map<String, String> data3 = new HashMap<>();
        data3.put("publishingHouse", "Londoqweqweqw qe q ewq ewqe qewq qwe qwe n");
        Map<String, List<Book>> expected = new HashMap<>();
        expected.put("filteredBooks", new ArrayList<>());
        return new Object[][]{
                {request, data1, expected},
                {request, data2, expected},
                {request, data3, expected},
        };
    }

    @Test(dataProvider = "processRequestFindBooksByPublishingHouseNegativeData")
    public void processRequestFindBooksByPublishingHouseNegativeTest(String request,
                                                                     Map<String, String> data,
                                                                     Map<String, List<Book>> expected) {
        Map<String, List<Book>> actual = bookController.processRequest(request, data);
        assertEquals(actual, expected);
    }

    @DataProvider(name = "processRequestRemoveBookPositiveData")
    public Object[][] createProcessRemoveBookPositiveData() {
        String request = "add_book";
        Map<String, String> data1 = new HashMap<>();
        data1.put("name", "Qwerty");
        data1.put("price", "100");
        data1.put("publishingHouse", "Minsk");
        data1.put("author1", "Oleg");
        data1.put("author2", "Pavel");
        Map<String, String> data2 = new HashMap<>();
        data2.put("name", "???");
        data2.put("price", "100.1");
        data2.put("publishingHouse", "Москва");
        Map<String, String> data3 = new HashMap<>();
        data3.put("name", "Ты");
        data3.put("price", "10");
        data3.put("publishingHouse", "Minsk");
        data3.put("author1", "Oleg");
        Map<String, List<Book>> expected = new HashMap<>();
        return new Object[][]{
                {request, data1, expected},
                {request, data2, expected},
                {request, data3, expected}
        };
    }

    @Test(dataProvider = "processRequestRemoveBookPositiveData",
            enabled = false)
    public void processRequestRemoveBookPositiveTest(String request,
                                                     Map<String, String> data,
                                                     Map<String, List<Book>> expected) {
        Map<String, List<Book>> actual = bookController.processRequest(request, data);
        assertEquals(actual, expected);
    }

    @DataProvider(name = "processRequestRemoveBookNegativeData")
    public Object[][] createProcessRequestRemoveBookNegativeData() {
        String request = "remove_book";
        Map<String, String> data1 = new HashMap<>();
        data1.put("publishingHouse", "Витебск");
        Map<String, String> data2 = null;
        Map<String, String> data3 = new HashMap<>();
        data3.put("publishingHouse", "Londoqweqweqw qe q ewq ewqe qewq qwe qwe n");
        Map<String, List<Book>> expected = new HashMap<>();
        expected.put("filteredBooks", new ArrayList<>());
        return new Object[][]{
                {request, data1, expected},
                {request, data2, expected},
                {request, data3, expected},
        };
    }

    @Test(dataProvider = "processRequestRemoveBookNegativeData",
            enabled = false)
    public void processRequestRemoveBookNegativeTest(String request,
                                                     Map<String, String> data,
                                                     Map<String, List<Book>> expected) {
        Map<String, List<Book>> actual = bookController.processRequest(request, data);
        assertEquals(actual, expected);
    }
}
*/
