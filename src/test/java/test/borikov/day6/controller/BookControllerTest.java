package test.borikov.day6.controller;

import com.borikov.day6.controller.BookController;
import com.borikov.day6.controller.command.impl.constant.DataKeyName;
import com.borikov.day6.controller.command.impl.constant.ResponseKeyName;
import com.borikov.day6.exception.StorageException;
import com.borikov.day6.model.entity.Book;
import test.borikov.day6.creator.BookStorageCreator;
import org.testng.annotations.*;

import java.util.*;

import static org.testng.Assert.*;

public class BookControllerTest {
    private BookController bookController;
    private BookStorageCreator bookStorageCreator;

    @BeforeClass
    public void setUpClass() throws StorageException {
        bookController = new BookController();
        bookStorageCreator = BookStorageCreator.getInstance();
        bookStorageCreator.setUpBookStorage();
    }

    @AfterClass
    public void tearDownClass() {
        bookController = null;
        bookStorageCreator = null;
    }

    @AfterMethod()
    public void tearDownMethod() throws StorageException {
        bookStorageCreator.setUpBookStorage();
    }

    @DataProvider(name = "processRequestAddBookPositiveData")
    public Object[][] createProcessRequestAddBookPositiveData() {
        String request = "add_book";
        Map<String, String> data1 = new HashMap<>();
        data1.put(DataKeyName.NAME, "Война и мир");
        data1.put(DataKeyName.PUBLISHING_YEAR, "2020");
        data1.put(DataKeyName.PUBLISHING_HOUSE, "Минск");
        data1.put(DataKeyName.AUTHOR + "1", "Лев");
        List<Book> addedBook1 = new ArrayList<>();
        addedBook1.add(new Book("Война и мир", 2020, "Минск", Arrays.asList("Лев")));
        Map<String, List<Book>> expected1 = new HashMap<>();
        expected1.put(ResponseKeyName.ADDED_BOOK, addedBook1);
        Map<String, String> data2 = new HashMap<>();
        data2.put(DataKeyName.NAME, "This is very very long line with 43 symbols");
        data2.put(DataKeyName.PUBLISHING_YEAR, "2020");
        data2.put(DataKeyName.PUBLISHING_HOUSE, "Минск");
        data2.put(DataKeyName.AUTHOR + "1", "Лев");
        List<Book> addedBook2 = new ArrayList<>();
        Map<String, List<Book>> expected2 = new HashMap<>();
        expected2.put(ResponseKeyName.ADDED_BOOK, addedBook2);
        Map<String, String> data3 = new HashMap<>();
        data3.put(DataKeyName.NAME, "Война и мир");
        data3.put(DataKeyName.PUBLISHING_YEAR, "2020");
        data3.put(DataKeyName.PUBLISHING_HOUSE, "Минск");
        data3.put(DataKeyName.AUTHOR, "Лев");
        List<Book> addedBook3 = new ArrayList<>();
        addedBook3.add(new Book("Война и мир", 2020, "Минск", Arrays.asList()));
        Map<String, List<Book>> expected3 = new HashMap<>();
        expected3.put(ResponseKeyName.ADDED_BOOK, addedBook3);
        Map<String, String> data4 = null;
        List<Book> filteredBooks4 = new ArrayList<>();
        Map<String, List<Book>> expected4 = new HashMap<>();
        expected4.put(ResponseKeyName.ADDED_BOOK, filteredBooks4);
        Map<String, String> data5 = new HashMap<>();
        List<Book> filteredBooks5 = new ArrayList<>();
        Map<String, List<Book>> expected5 = new HashMap<>();
        expected5.put(ResponseKeyName.ADDED_BOOK, filteredBooks5);
        Map<String, String> data6 = new HashMap<>();
        data6.put(DataKeyName.PUBLISHING_YEAR, "2020");
        data6.put(DataKeyName.PUBLISHING_HOUSE, "Минск");
        data6.put(DataKeyName.AUTHOR + "1", "Лев");
        List<Book> addedBook6 = new ArrayList<>();
        Map<String, List<Book>> expected6 = new HashMap<>();
        expected6.put(ResponseKeyName.ADDED_BOOK, addedBook6);
        Map<String, String> data7 = new HashMap<>();
        data7.put(DataKeyName.NAME, "Война и мир");
        data7.put(DataKeyName.PUBLISHING_YEAR, "1984");
        data7.put(DataKeyName.PUBLISHING_HOUSE, "Минск");
        data7.put(DataKeyName.AUTHOR + "1", "Лев Толстой");
        List<Book> addedBook7 = new ArrayList<>();
        Map<String, List<Book>> expected7 = new HashMap<>();
        expected7.put(ResponseKeyName.ERROR, addedBook7);
        return new Object[][]{
                {request, data1, expected1},
                {request, data2, expected2},
                {request, data3, expected3},
                {request, data4, expected4},
                {request, data5, expected5},
                {request, data6, expected6},
                {request, data7, expected7}
        };
    }

    @Test(dataProvider = "processRequestAddBookPositiveData")
    public void processRequestAddBookPositiveTest(String request,
                                                  Map<String, String> data,
                                                  Map<String, List<Book>> expected) {
        Map<String, List<Book>> actual = bookController.processRequest(request, data);
        boolean result;
        if (actual.get(ResponseKeyName.ADDED_BOOK) != null &&
                expected.get(ResponseKeyName.ADDED_BOOK) != null &&
                actual.get(ResponseKeyName.ADDED_BOOK).size() == 1 &&
                expected.get(ResponseKeyName.ADDED_BOOK).size() == 1) {
            result = actual.get(ResponseKeyName.ADDED_BOOK).get(0)
                    .equalsToBook(expected.get(ResponseKeyName.ADDED_BOOK).get(0));
        } else {
            result = actual.equals(expected);
        }
        assertTrue(result);
    }

    @DataProvider(name = "processRequestAddBookNegativeData")
    public Object[][] createProcessRequestAddBookNegativeData() {
        String request = "add_book";
        Map<String, String> data1 = new HashMap<>();
        data1.put(DataKeyName.NAME, "Война и мир");
        data1.put(DataKeyName.PUBLISHING_YEAR, "2020");
        data1.put(DataKeyName.PUBLISHING_HOUSE, "Минск1");
        data1.put(DataKeyName.AUTHOR + "1", "Лев");
        List<Book> addedBook1 = new ArrayList<>();
        addedBook1.add(new Book("Война и мир", 2020, "Минск", Arrays.asList("Лев")));
        Map<String, List<Book>> expected1 = new HashMap<>();
        expected1.put(ResponseKeyName.ADDED_BOOK, addedBook1);
        Map<String, String> data2 = new HashMap<>();
        data2.put(DataKeyName.NAME, "This is very very long line with 43 symbols");
        data2.put(DataKeyName.PUBLISHING_YEAR, "2020");
        data2.put(DataKeyName.PUBLISHING_HOUSE, "Минск");
        data2.put(DataKeyName.AUTHOR + "1", "Лев");
        List<Book> addedBook2 = new ArrayList<>();
        addedBook2.add(new Book("Война и мир", 2020, "Минск", Arrays.asList("Лев")));
        Map<String, List<Book>> expected2 = new HashMap<>();
        expected2.put(ResponseKeyName.ADDED_BOOK, addedBook2);
        Map<String, String> data3 = new HashMap<>();
        data3.put(DataKeyName.NAME, "Война и мир");
        data3.put(DataKeyName.PUBLISHING_YEAR, "2020");
        data3.put(DataKeyName.PUBLISHING_HOUSE, "Минск");
        data3.put(DataKeyName.AUTHOR, "Лев");
        List<Book> addedBook3 = new ArrayList<>();
        Map<String, List<Book>> expected3 = new HashMap<>();
        expected3.put(ResponseKeyName.ADDED_BOOK, addedBook3);
        Map<String, String> data4 = null;
        Map<String, List<Book>> expected4 = new HashMap<>();
        Map<String, String> data5 = new HashMap<>();
        List<Book> filteredBooks5 = null;
        Map<String, List<Book>> expected5 = new HashMap<>();
        expected5.put(ResponseKeyName.ADDED_BOOK, filteredBooks5);
        Map<String, String> data6 = new HashMap<>();
        data6.put(DataKeyName.PUBLISHING_YEAR, "2020");
        data6.put(DataKeyName.PUBLISHING_HOUSE, "Минск");
        data6.put(DataKeyName.AUTHOR + "1", "Лев");
        List<Book> addedBook6 = new ArrayList<>();
        Map<String, List<Book>> expected6 = new HashMap<>();
        expected6.put(ResponseKeyName.ERROR, addedBook6);
        Map<String, String> data7 = new HashMap<>();
        data7.put(DataKeyName.NAME, "Война и мир");
        data7.put(DataKeyName.PUBLISHING_YEAR, "1984");
        data7.put(DataKeyName.PUBLISHING_HOUSE, "Минск");
        data7.put(DataKeyName.AUTHOR + "1", "Лев Толстой");
        List<Book> addedBook7 = new ArrayList<>();
        Map<String, List<Book>> expected7 = new HashMap<>();
        expected7.put(ResponseKeyName.ADDED_BOOK, addedBook7);
        return new Object[][]{
                {request, data1, expected1},
                {request, data2, expected2},
                {request, data3, expected3},
                {request, data4, expected4},
                {request, data5, expected5},
                {request, data6, expected6},
                {request, data7, expected7}
        };
    }

    @Test(dataProvider = "processRequestAddBookNegativeData")
    public void processRequestAddBookNegativeTest(String request,
                                                  Map<String, String> data,
                                                  Map<String, List<Book>> expected) {
        Map<String, List<Book>> actual =
                bookController.processRequest(request, data);
        boolean result;
        if (actual.get(ResponseKeyName.ADDED_BOOK) != null &&
                expected.get(ResponseKeyName.ADDED_BOOK) != null &&
                actual.get(ResponseKeyName.ADDED_BOOK).size() == 1 &&
                expected.get(ResponseKeyName.ADDED_BOOK).size() == 1) {
            result = actual.get(ResponseKeyName.ADDED_BOOK).get(0)
                    .equalsToBook(expected.get(ResponseKeyName.ADDED_BOOK).get(0));
        } else {
            result = actual.equals(expected);
        }
        assertFalse(result);
    }

    @DataProvider(name = "processRequestFindAllBooksPositiveData")
    public Object[][] createProcessRequestFindAllBooksPositiveData() {
        String request = "find_all_books";
        Map<String, String> data1 = null;
        Map<String, String> data2 = new HashMap<>();
        Map<String, String> data3 = new HashMap<>();
        data3.put("first", "first");
        Map<String, List<Book>> expected = new HashMap<>();
        expected.put(ResponseKeyName.ALL_BOOKS, bookStorageCreator.getCreatedBooks());
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
        expected1.put(ResponseKeyName.ALL_BOOKS, bookStorageCreator.getCreatedBooks());
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
                {request, data1, expected1},
                {request, data2, expected2},
                {request, data3, expected3},
                {request, data4, expected4},
                {request, data5, expected5},
                {request, data6, expected6}
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
                {request, data1, expected1},
                {request, data2, expected2},
                {request, data3, expected3},
                {request, data4, expected4}
        };
    }

    @Test(dataProvider = "processRequestFindBooksByIdNegativeData")
    public void processRequestFindBooksByIdNegativeTest(String request,
                                                        Map<String, String> data,
                                                        Map<String, List<Book>> expected) {
        Map<String, List<Book>> actual = bookController.processRequest(request, data);
        assertNotEquals(actual, expected);
    }

    @DataProvider(name = "processRequestFindBooksByAuthorPositiveData")
    public Object[][] createProcessRequestFindBooksByAuthorPositiveData() {
        String request = "find_books_by_author";
        Map<String, String> data1 = new HashMap<>();
        data1.put(DataKeyName.AUTHOR, "Oleg");
        List<Book> filteredBooks1 = new ArrayList<>();
        filteredBooks1.add(bookStorageCreator.getCreatedBooks().get(3));
        filteredBooks1.add(bookStorageCreator.getCreatedBooks().get(7));
        filteredBooks1.add(bookStorageCreator.getCreatedBooks().get(9));
        Map<String, List<Book>> expected1 = new HashMap<>();
        expected1.put(ResponseKeyName.FILTERED_BOOKS, filteredBooks1);
        Map<String, String> data2 = new HashMap<>();
        data2.put(DataKeyName.AUTHOR, "Sapolsky");
        List<Book> filteredBooks2 = new ArrayList<>();
        filteredBooks2.add(bookStorageCreator.getCreatedBooks().get(5));
        Map<String, List<Book>> expected2 = new HashMap<>();
        expected2.put(ResponseKeyName.FILTERED_BOOKS, filteredBooks2);
        Map<String, String> data3 = new HashMap<>();
        data3.put(DataKeyName.AUTHOR, "qwe");
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
                {request, data1, expected1},
                {request, data2, expected2},
                {request, data3, expected3},
                {request, data4, expected4},
                {request, data5, expected5}
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
        Map<String, String> data1 = new HashMap<>();
        data1.put(DataKeyName.AUTHOR, "Oleg");
        List<Book> filteredBooks1 = new ArrayList<>();
        filteredBooks1.add(bookStorageCreator.getCreatedBooks().get(3));
        filteredBooks1.add(bookStorageCreator.getCreatedBooks().get(7));
        Map<String, List<Book>> expected1 = new HashMap<>();
        expected1.put(ResponseKeyName.FILTERED_BOOKS, filteredBooks1);
        Map<String, String> data2 = new HashMap<>();
        data2.put(DataKeyName.AUTHOR, "Sapolsky");
        List<Book> filteredBooks2 = new ArrayList<>();
        filteredBooks2.add(bookStorageCreator.getCreatedBooks().get(5));
        filteredBooks2.add(bookStorageCreator.getCreatedBooks().get(5));
        Map<String, List<Book>> expected2 = new HashMap<>();
        expected2.put(ResponseKeyName.FILTERED_BOOKS, filteredBooks2);
        Map<String, String> data3 = new HashMap<>();
        data3.put(DataKeyName.AUTHOR, "Oleg");
        List<Book> filteredBooks3 = new ArrayList<>();
        Map<String, List<Book>> expected3 = new HashMap<>();
        expected3.put(ResponseKeyName.FILTERED_BOOKS, filteredBooks3);
        Map<String, String> data4 = null;
        List<Book> filteredBooks4 = new ArrayList<>();
        Map<String, List<Book>> expected4 = new HashMap<>();
        expected4.put(ResponseKeyName.SORTED_BOOKS, filteredBooks4);
        return new Object[][]{
                {request, data1, expected1},
                {request, data2, expected2},
                {request, data3, expected3},
                {request, data4, expected4}
        };
    }

    @Test(dataProvider = "processRequestFindBooksByAuthorNegativeData")
    public void processRequestFindBooksByAuthorNegativeTest(String request,
                                                            Map<String, String> data,
                                                            Map<String, List<Book>> expected) {
        Map<String, List<Book>> actual = bookController.processRequest(request, data);
        assertNotEquals(actual, expected);
    }

    @DataProvider(name = "processRequestFindBooksByNamePositiveData")
    public Object[][] createProcessRequestFindBooksByNamePositiveData() {
        String request = "find_books_by_name";
        Map<String, String> data1 = new HashMap<>();
        data1.put(DataKeyName.NAME, "Война и мир");
        List<Book> filteredBooks1 = new ArrayList<>();
        filteredBooks1.add(bookStorageCreator.getCreatedBooks().get(0));
        filteredBooks1.add(bookStorageCreator.getCreatedBooks().get(4));
        Map<String, List<Book>> expected1 = new HashMap<>();
        expected1.put(ResponseKeyName.FILTERED_BOOKS, filteredBooks1);
        Map<String, String> data2 = new HashMap<>();
        data2.put(DataKeyName.NAME, "Метро 2033");
        List<Book> filteredBooks2 = new ArrayList<>();
        filteredBooks2.add(bookStorageCreator.getCreatedBooks().get(8));
        Map<String, List<Book>> expected2 = new HashMap<>();
        expected2.put(ResponseKeyName.FILTERED_BOOKS, filteredBooks2);
        Map<String, String> data3 = new HashMap<>();
        data3.put(DataKeyName.NAME, "qwe");
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
                {request, data1, expected1},
                {request, data2, expected2},
                {request, data3, expected3},
                {request, data4, expected4},
                {request, data5, expected5}
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
        Map<String, String> data1 = new HashMap<>();
        data1.put(DataKeyName.NAME, "Война и мир");
        List<Book> filteredBooks1 = new ArrayList<>();
        filteredBooks1.add(bookStorageCreator.getCreatedBooks().get(0));
        Map<String, List<Book>> expected1 = new HashMap<>();
        expected1.put(ResponseKeyName.FILTERED_BOOKS, filteredBooks1);
        Map<String, String> data2 = new HashMap<>();
        data2.put(DataKeyName.NAME, "Метро 2033");
        List<Book> filteredBooks2 = new ArrayList<>();
        filteredBooks2.add(bookStorageCreator.getCreatedBooks().get(8));
        filteredBooks2.add(bookStorageCreator.getCreatedBooks().get(8));
        Map<String, List<Book>> expected2 = new HashMap<>();
        expected2.put(ResponseKeyName.FILTERED_BOOKS, filteredBooks2);
        Map<String, String> data3 = new HashMap<>();
        data3.put(DataKeyName.NAME, "Война и мир");
        List<Book> filteredBooks3 = new ArrayList<>();
        Map<String, List<Book>> expected3 = new HashMap<>();
        expected3.put(ResponseKeyName.FILTERED_BOOKS, filteredBooks3);
        Map<String, String> data4 = null;
        List<Book> filteredBooks4 = new ArrayList<>();
        Map<String, List<Book>> expected4 = new HashMap<>();
        expected4.put(ResponseKeyName.SORTED_BOOKS, filteredBooks4);
        return new Object[][]{
                {request, data1, expected1},
                {request, data2, expected2},
                {request, data3, expected3},
                {request, data4, expected4}
        };
    }

    @Test(dataProvider = "processRequestFindBooksByNameNegativeData")
    public void processRequestFindBooksByNameNegativeTest(String request,
                                                          Map<String, String> data,
                                                          Map<String, List<Book>> expected) {
        Map<String, List<Book>> actual = bookController.processRequest(request, data);
        assertNotEquals(actual, expected);
    }

    @DataProvider(name = "processRequestFindBooksByPublishingHousePositiveData")
    public Object[][] createProcessRequestFindBooksByPublishingHousePositiveData() {
        String request = "find_books_by_publishing_house";
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
                {request, data1, expected1},
                {request, data2, expected2},
                {request, data3, expected3},
                {request, data4, expected4},
                {request, data5, expected5}
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
                {request, data1, expected1},
                {request, data2, expected2},
                {request, data3, expected3},
                {request, data4, expected4}
        };
    }

    @Test(dataProvider = "processRequestFindBooksByPublishingHouseNegativeData")
    public void processRequestFindBooksByPublishingHouseNegativeTest(String request,
                                                                     Map<String, String> data,
                                                                     Map<String, List<Book>> expected) {
        Map<String, List<Book>> actual = bookController.processRequest(request, data);
        assertNotEquals(actual, expected);
    }

    @DataProvider(name = "processRequestFindBooksByPublishingYearPositiveData")
    public Object[][] createProcessRequestFindBooksByPublishingYearPositiveData() {
        String request = "find_books_by_publishing_year";
        Map<String, String> data1 = new HashMap<>();
        data1.put(DataKeyName.PUBLISHING_YEAR, "1000");
        List<Book> filteredBooks1 = new ArrayList<>();
        filteredBooks1.add(bookStorageCreator.getCreatedBooks().get(2));
        filteredBooks1.add(bookStorageCreator.getCreatedBooks().get(6));
        Map<String, List<Book>> expected1 = new HashMap<>();
        expected1.put(ResponseKeyName.FILTERED_BOOKS, filteredBooks1);
        Map<String, String> data2 = new HashMap<>();
        data2.put(DataKeyName.PUBLISHING_YEAR, "1984");
        List<Book> filteredBooks2 = new ArrayList<>();
        filteredBooks2.add(bookStorageCreator.getCreatedBooks().get(0));
        Map<String, List<Book>> expected2 = new HashMap<>();
        expected2.put(ResponseKeyName.FILTERED_BOOKS, filteredBooks2);
        Map<String, String> data3 = new HashMap<>();
        data3.put(DataKeyName.PUBLISHING_YEAR, "1230");
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
        Map<String, String> data6 = new HashMap<>();
        data6.put(DataKeyName.PUBLISHING_YEAR, "abc");
        List<Book> filteredBooks6 = new ArrayList<>();
        Map<String, List<Book>> expected6 = new HashMap<>();
        expected6.put(ResponseKeyName.FILTERED_BOOKS, filteredBooks6);
        Map<String, String> data7 = new HashMap<>();
        data7.put(DataKeyName.PUBLISHING_YEAR, "-1000");
        List<Book> filteredBooks7 = new ArrayList<>();
        Map<String, List<Book>> expected7 = new HashMap<>();
        expected7.put(ResponseKeyName.FILTERED_BOOKS, filteredBooks7);
        return new Object[][]{
                {request, data1, expected1},
                {request, data2, expected2},
                {request, data3, expected3},
                {request, data4, expected4},
                {request, data5, expected5},
                {request, data6, expected6},
                {request, data7, expected7}
        };
    }

    @Test(dataProvider = "processRequestFindBooksByPublishingYearPositiveData")
    public void processRequestFindBooksByPublishingYearPositiveTest(String request,
                                                                    Map<String, String> data,
                                                                    Map<String, List<Book>> expected) {
        Map<String, List<Book>> actual = bookController.processRequest(request, data);
        assertEquals(actual, expected);
    }

    @DataProvider(name = "processRequestFindBooksByPublishingYearNegativeData")
    public Object[][] createProcessRequestFindBooksByPublishingYearNegativeData() {
        String request = "find_books_by_publishing_year";
        Map<String, String> data1 = new HashMap<>();
        data1.put(DataKeyName.PUBLISHING_YEAR, "1000");
        List<Book> filteredBooks1 = new ArrayList<>();
        filteredBooks1.add(bookStorageCreator.getCreatedBooks().get(2));
        Map<String, List<Book>> expected1 = new HashMap<>();
        expected1.put(ResponseKeyName.FILTERED_BOOKS, filteredBooks1);
        Map<String, String> data2 = new HashMap<>();
        data2.put(DataKeyName.PUBLISHING_YEAR, "1984");
        List<Book> filteredBooks2 = new ArrayList<>();
        filteredBooks2.add(bookStorageCreator.getCreatedBooks().get(0));
        filteredBooks2.add(bookStorageCreator.getCreatedBooks().get(0));
        Map<String, List<Book>> expected2 = new HashMap<>();
        expected2.put(ResponseKeyName.FILTERED_BOOKS, filteredBooks2);
        Map<String, String> data3 = new HashMap<>();
        data3.put(DataKeyName.PUBLISHING_YEAR, "1500");
        List<Book> filteredBooks3 = new ArrayList<>();
        Map<String, List<Book>> expected3 = new HashMap<>();
        expected3.put(ResponseKeyName.FILTERED_BOOKS, filteredBooks3);
        Map<String, String> data4 = null;
        List<Book> filteredBooks4 = new ArrayList<>();
        Map<String, List<Book>> expected4 = new HashMap<>();
        expected4.put(ResponseKeyName.SORTED_BOOKS, filteredBooks4);
        return new Object[][]{
                {request, data1, expected1},
                {request, data2, expected2},
                {request, data3, expected3},
                {request, data4, expected4}
        };
    }

    @Test(dataProvider = "processRequestFindBooksByPublishingYearNegativeData")
    public void processRequestFindBooksByPublishingYearNegativeTest(String request,
                                                                    Map<String, String> data,
                                                                    Map<String, List<Book>> expected) {
        Map<String, List<Book>> actual = bookController.processRequest(request, data);
        assertNotEquals(actual, expected);
    }

    @DataProvider(name = "processRequestRemoveBookPositiveData")
    public Object[][] createProcessRemoveBookPositiveData() {
        String request = "remove_book";
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
                {request, data1, expected1},
                {request, data2, expected2},
                {request, data3, expected3},
                {request, data4, expected4},
                {request, data5, expected5},
                {request, data6, expected6},
                {request, data7, expected7}
        };
    }

    @Test(dataProvider = "processRequestRemoveBookPositiveData")
    public void processRequestRemoveBookPositiveTest(String request,
                                                     Map<String, String> data,
                                                     Map<String, List<Book>> expected) {
        Map<String, List<Book>> actual = bookController.processRequest(request, data);
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

    @DataProvider(name = "processRequestRemoveBookNegativeData")
    public Object[][] createProcessRequestRemoveBookNegativeData() {
        String request = "remove_book";
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
                {request, data1, expected1},
                {request, data2, expected2},
                {request, data3, expected3},
                {request, data4, expected4},
                {request, data5, expected5},
                {request, data6, expected6},
                {request, data7, expected7}
        };
    }

    @Test(dataProvider = "processRequestRemoveBookNegativeData")
    public void processRequestRemoveBookNegativeTest(String request,
                                                     Map<String, String> data,
                                                     Map<String, List<Book>> expected) {
        Map<String, List<Book>> actual = bookController.processRequest(request, data);
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

    @Test()
    public void processRequestSortBooksByAuthorsPositiveTest() {
        String request = "sort_books_by_authors";
        Map<String, String> data = new HashMap<>();
        Map<String, List<Book>> actual = bookController.processRequest(request, data);
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

    @Test()
    public void processRequestSortBooksByAuthorsNegativeTest() {
        String request = "sort_books_by_authors";
        Map<String, String> data = new HashMap<>();
        Map<String, List<Book>> actual = bookController.processRequest(request, data);
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

    @Test()
    public void processRequestSortBooksByIdHousePositiveTest() {
        String request = "sort_books_by_id";
        Map<String, String> data = new HashMap<>();
        Map<String, List<Book>> actual = bookController.processRequest(request, data);
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

    @Test()
    public void processRequestSortBooksByIdNegativeTest() {
        String request = "sort_books_by_id";
        Map<String, String> data = new HashMap<>();
        Map<String, List<Book>> actual = bookController.processRequest(request, data);
        List<Book> sortedBooks = bookStorageCreator.getCreatedBooks();
        Map<String, List<Book>> expected = new HashMap<>();
        expected.put(ResponseKeyName.SORTED_BOOKS, sortedBooks);
        assertNotEquals(actual, expected);
    }

    @Test()
    public void processRequestSortBooksByNameHousePositiveTest() {
        String request = "sort_books_by_name";
        Map<String, String> data = new HashMap<>();
        Map<String, List<Book>> actual = bookController.processRequest(request, data);
        List<Book> sortedBooks = new ArrayList<>();
        sortedBooks.add(bookStorageCreator.getCreatedBooks().get(5));
        sortedBooks.add(bookStorageCreator.getCreatedBooks().get(9));
        sortedBooks.add(bookStorageCreator.getCreatedBooks().get(3));
        sortedBooks.add(bookStorageCreator.getCreatedBooks().get(0));
        sortedBooks.add(bookStorageCreator.getCreatedBooks().get(4));
        sortedBooks.add(bookStorageCreator.getCreatedBooks().get(6));
        sortedBooks.add(bookStorageCreator.getCreatedBooks().get(8));
        sortedBooks.add(bookStorageCreator.getCreatedBooks().get(1));
        sortedBooks.add(bookStorageCreator.getCreatedBooks().get(2));
        sortedBooks.add(bookStorageCreator.getCreatedBooks().get(7));
        Map<String, List<Book>> expected = new HashMap<>();
        expected.put(ResponseKeyName.SORTED_BOOKS, sortedBooks);
        assertEquals(actual, expected);
    }

    @Test()
    public void processRequestSortBooksByNameNegativeTest() {
        String request = "sort_books_by_name";
        Map<String, String> data = new HashMap<>();
        Map<String, List<Book>> actual = bookController.processRequest(request, data);
        List<Book> sortedBooks = new ArrayList<>();
        sortedBooks.add(bookStorageCreator.getCreatedBooks().get(5));
        sortedBooks.add(bookStorageCreator.getCreatedBooks().get(9));
        sortedBooks.add(bookStorageCreator.getCreatedBooks().get(3));
        sortedBooks.add(bookStorageCreator.getCreatedBooks().get(0));
        sortedBooks.add(bookStorageCreator.getCreatedBooks().get(4));
        sortedBooks.add(bookStorageCreator.getCreatedBooks().get(6));
        sortedBooks.add(bookStorageCreator.getCreatedBooks().get(8));
        sortedBooks.add(bookStorageCreator.getCreatedBooks().get(1));
        sortedBooks.add(bookStorageCreator.getCreatedBooks().get(7));
        sortedBooks.add(bookStorageCreator.getCreatedBooks().get(2));
        Map<String, List<Book>> expected = new HashMap<>();
        expected.put(ResponseKeyName.SORTED_BOOKS, sortedBooks);
        assertNotEquals(actual, expected);
    }

    @Test()
    public void processRequestSortBooksByPublishingHousePositiveTest() {
        String request = "sort_books_by_publishing_house";
        Map<String, String> data = new HashMap<>();
        Map<String, List<Book>> actual = bookController.processRequest(request, data);
        List<Book> sortedBooks = new ArrayList<>();
        sortedBooks.add(bookStorageCreator.getCreatedBooks().get(5));
        sortedBooks.add(bookStorageCreator.getCreatedBooks().get(9));
        sortedBooks.add(bookStorageCreator.getCreatedBooks().get(8));
        sortedBooks.add(bookStorageCreator.getCreatedBooks().get(0));
        sortedBooks.add(bookStorageCreator.getCreatedBooks().get(1));
        sortedBooks.add(bookStorageCreator.getCreatedBooks().get(6));
        sortedBooks.add(bookStorageCreator.getCreatedBooks().get(4));
        sortedBooks.add(bookStorageCreator.getCreatedBooks().get(7));
        sortedBooks.add(bookStorageCreator.getCreatedBooks().get(2));
        sortedBooks.add(bookStorageCreator.getCreatedBooks().get(3));
        Map<String, List<Book>> expected = new HashMap<>();
        expected.put(ResponseKeyName.SORTED_BOOKS, sortedBooks);
        assertEquals(actual, expected);
    }

    @Test()
    public void processRequestSortBooksByPublishingHouseNegativeTest() {
        String request = "sort_books_by_publishing_house";
        Map<String, String> data = new HashMap<>();
        Map<String, List<Book>> actual = bookController.processRequest(request, data);
        List<Book> sortedBooks = new ArrayList<>();
        sortedBooks.add(bookStorageCreator.getCreatedBooks().get(5));
        sortedBooks.add(bookStorageCreator.getCreatedBooks().get(9));
        sortedBooks.add(bookStorageCreator.getCreatedBooks().get(8));
        sortedBooks.add(bookStorageCreator.getCreatedBooks().get(0));
        sortedBooks.add(bookStorageCreator.getCreatedBooks().get(1));
        sortedBooks.add(bookStorageCreator.getCreatedBooks().get(6));
        sortedBooks.add(bookStorageCreator.getCreatedBooks().get(4));
        sortedBooks.add(bookStorageCreator.getCreatedBooks().get(7));
        sortedBooks.add(bookStorageCreator.getCreatedBooks().get(2));
        Map<String, List<Book>> expected = new HashMap<>();
        expected.put(ResponseKeyName.SORTED_BOOKS, sortedBooks);
        assertNotEquals(actual, expected);
    }

    @Test()
    public void processRequestSortBooksByPublishingYearPositiveTest() {
        String request = "sort_books_by_publishing_year";
        Map<String, String> data = new HashMap<>();
        Map<String, List<Book>> actual = bookController.processRequest(request, data);
        List<Book> sortedBooks = new ArrayList<>();
        sortedBooks.add(bookStorageCreator.getCreatedBooks().get(9));
        sortedBooks.add(bookStorageCreator.getCreatedBooks().get(2));
        sortedBooks.add(bookStorageCreator.getCreatedBooks().get(6));
        sortedBooks.add(bookStorageCreator.getCreatedBooks().get(3));
        sortedBooks.add(bookStorageCreator.getCreatedBooks().get(7));
        sortedBooks.add(bookStorageCreator.getCreatedBooks().get(5));
        sortedBooks.add(bookStorageCreator.getCreatedBooks().get(0));
        sortedBooks.add(bookStorageCreator.getCreatedBooks().get(4));
        sortedBooks.add(bookStorageCreator.getCreatedBooks().get(8));
        sortedBooks.add(bookStorageCreator.getCreatedBooks().get(1));
        Map<String, List<Book>> expected = new HashMap<>();
        expected.put(ResponseKeyName.SORTED_BOOKS, sortedBooks);
        assertEquals(actual, expected);
    }

    @Test()
    public void processRequestSortBooksByPublishingYearNegativeTest() {
        String request = "sort_books_by_publishing_year";
        Map<String, String> data = new HashMap<>();
        Map<String, List<Book>> actual = bookController.processRequest(request, data);
        List<Book> sortedBooks = bookStorageCreator.getCreatedBooks();
        Map<String, List<Book>> expected = new HashMap<>();
        expected.put(ResponseKeyName.SORTED_BOOKS, sortedBooks);
        assertNotEquals(actual, expected);
    }

    @DataProvider(name = "processRequestPositiveData")
    public Object[][] createProcessRequestPositiveData() {
        String request1 = "addbook";
        String request2 = "addBook";
        String request3 = "add book";
        Map<String, String> data = new HashMap<>();
        Map<String, List<Book>> expected = new HashMap<>();
        expected.put(ResponseKeyName.ALL_BOOKS, bookStorageCreator.getCreatedBooks());
        return new Object[][]{
                {request1, data, expected},
                {request2, data, expected},
                {request3, data, expected}
        };
    }

    @Test(dataProvider = "processRequestPositiveData")
    public void processRequestPositiveTest(String request,
                                           Map<String, String> data,
                                           Map<String, List<Book>> expected) {
        Map<String, List<Book>> actual = bookController.processRequest(request, data);
        assertEquals(actual, expected);
    }

    @DataProvider(name = "processRequestNegativeData")
    public Object[][] createProcessRequestNegativeData() {
        String request1 = "addbook";
        String request2 = "addBook";
        String request3 = "add book";
        Map<String, String> data = new HashMap<>();
        Map<String, List<Book>> expected = new HashMap<>();
        expected.put(ResponseKeyName.CURRENT_BOOK, bookStorageCreator.getCreatedBooks());
        return new Object[][]{
                {request1, data, expected},
                {request2, data, null},
                {request3, data, new HashMap<>()}
        };
    }

    @Test(dataProvider = "processRequestNegativeData")
    public void processRequestNegativeTest(String request,
                                           Map<String, String> data,
                                           Map<String, List<Book>> expected) {
        Map<String, List<Book>> actual = bookController.processRequest(request, data);
        assertNotEquals(actual, expected);
    }
}
