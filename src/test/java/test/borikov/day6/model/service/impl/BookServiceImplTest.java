package test.borikov.day6.model.service.impl;

import com.borikov.day6.exception.ServiceException;
import com.borikov.day6.exception.StorageException;
import com.borikov.day6.model.entity.Book;
import com.borikov.day6.model.service.BookService;
import com.borikov.day6.model.service.impl.BookServiceImpl;
import org.testng.annotations.*;
import test.borikov.day6.creator.BookStorageCreator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.*;

public class BookServiceImplTest {
    private BookService bookService;
    private BookStorageCreator bookStorageCreator;

    @BeforeClass
    public void setUpClass() throws StorageException {
        bookService = new BookServiceImpl();
        bookStorageCreator = BookStorageCreator.getInstance();
        bookStorageCreator.setUpBookStorage();
    }

    @AfterClass
    public void tearDownClass() {
        bookService = null;
        bookStorageCreator = null;
    }

    @AfterMethod
    public void tearDownMethod() throws StorageException {
        bookStorageCreator.setUpBookStorage();
    }

    @DataProvider(name = "addBookPositiveData")
    public Object[][] createAddBookPositiveData() {
        String name1 = "Война и мир";
        String publishingYear1 = "2020";
        String publishingHouse1 = "Минск";
        List<String> authors1 = Arrays.asList("Лев");
        List<Book> expected1 = new ArrayList<>();
        expected1.add(new Book("Война и мир", 2020, "Минск", Arrays.asList("Лев")));
        String name2 = "This is very very long line with 43 symbols";
        String publishingYear2 = "2020";
        String publishingHouse2 = "Минск";
        List<String> authors2 = Arrays.asList("Лев");
        List<Book> expected2 = new ArrayList<>();
        String name3 = "мир";
        String publishingYear3 = "2020";
        String publishingHouse3 = "This is very very long line with 43 symbols";
        List<String> authors3 = Arrays.asList("Лев");
        List<Book> expected3 = new ArrayList<>();
        String name4 = "мир";
        String publishingYear4 = "ac";
        String publishingHouse4 = "Минск";
        List<String> authors4 = Arrays.asList("Лев");
        List<Book> expected4 = new ArrayList<>();
        String name5 = "мир";
        String publishingYear5 = "2020";
        String publishingHouse5 = "Минск";
        List<String> authors5 =
                Arrays.asList("This is very very long line with 43 symbols");
        List<Book> expected5 = new ArrayList<>();
        String name6 = "Qw";
        String publishingYear6 = "1999";
        String publishingHouse6 = "Москва";
        List<String> authors6 = Arrays.asList("Qwe");
        List<Book> expected6 = new ArrayList<>();
        expected6.add(new Book("Qw", 1999, "Москва", Arrays.asList("Qwe")));
        return new Object[][]{
                {name1, publishingYear1, publishingHouse1, authors1, expected1},
                {name2, publishingYear2, publishingHouse2, authors2, expected2},
                {name3, publishingYear3, publishingHouse3, authors3, expected3},
                {name4, publishingYear4, publishingHouse4, authors4, expected4},
                {name5, publishingYear5, publishingHouse5, authors5, expected5},
                {name6, publishingYear6, publishingHouse6, authors6, expected6}
        };
    }

    @Test(dataProvider = "addBookPositiveData")
    public void addBookPositiveTest(String name, String publishingYear,
                                    String publishingHouse,
                                    List<String> authors, List<Book> expected) {
        try {
            List<Book> actual = bookService.addBook(name,
                    publishingYear, publishingHouse, authors);
            boolean result;
            if (actual.size() == 1 && expected.size() == 1) {
                result = actual.get(0).equalsToBook(expected.get(0));
            } else {
                result = actual.equals(expected);
            }
            assertTrue(result);
        } catch (ServiceException e) {
            fail("Incorrect input");
        }
    }

    @DataProvider(name = "addBookNegativeData")
    public Object[][] createAddBookNegativeData() {
        String name1 = "Война и мир";
        String publishingYear1 = "2021";
        String publishingHouse1 = "Минск";
        List<String> authors1 = Arrays.asList("Лев");
        List<Book> expected1 = new ArrayList<>();
        expected1.add(new Book("Война и мир", 2021, "Минск", Arrays.asList("Лев")));
        String name2 = "This is very very long line with 43 symbols";
        String publishingYear2 = "2020";
        String publishingHouse2 = "Минск";
        List<String> authors2 = Arrays.asList("Лев");
        List<Book> expected2 = null;
        String name3 = "Qw";
        String publishingYear3 = "1999";
        String publishingHouse3 = "Москва";
        List<String> authors3 = Arrays.asList();
        List<Book> expected3 = new ArrayList<>();
        expected3.add(new Book("Qw", 1999, "Москва", Arrays.asList("Qwe")));
        return new Object[][]{
                {name1, publishingYear1, publishingHouse1, authors1, expected1},
                {name2, publishingYear2, publishingHouse2, authors2, expected2},
                {name3, publishingYear3, publishingHouse3, authors3, expected3}
        };
    }

    @Test(dataProvider = "addBookNegativeData")
    public void addBookNegativeTest(String name, String publishingYear,
                                    String publishingHouse,
                                    List<String> authors, List<Book> expected) {
        try {
            List<Book> actual = bookService.addBook(name,
                    publishingYear, publishingHouse, authors);
            boolean result;
            if (actual.size() == 1 && expected.size() == 1) {
                result = actual.get(0).equalsToBook(expected.get(0));
            } else {
                result = actual.equals(expected);
            }
            assertFalse(result);
        } catch (ServiceException e) {
            fail("Incorrect input");
        }
    }

    @DataProvider(name = "addBookExceptionData")
    public Object[][] createAddBookExceptionData() {
        String name1 = "Война и мир";
        String publishingYear1 = "1984";
        String publishingHouse1 = "Минск";
        List<String> authors1 = Arrays.asList("Лев Толстой");
        String name2 = "Философия Java";
        String publishingYear2 = "1000";
        String publishingHouse2 = "Питер";
        List<String> authors2 = Arrays.asList("Брюс Эккель");
        String name3 = "История Минска";
        String publishingYear3 = "1000";
        String publishingHouse3 = "Минск";
        List<String> authors3 = new ArrayList<>();
        return new Object[][]{
                {name1, publishingYear1, publishingHouse1, authors1},
                {name2, publishingYear2, publishingHouse2, authors2},
                {name3, publishingYear3, publishingHouse3, authors3}
        };
    }

    @Test(dataProvider = "addBookExceptionData",
            expectedExceptions = ServiceException.class)
    public void addBookExceptionTest(String name, String publishingYear,
                                     String publishingHouse,
                                     List<String> authors)
            throws ServiceException {
        bookService.addBook(name, publishingYear, publishingHouse, authors);
    }

    @DataProvider(name = "removeBookPositiveData")
    public Object[][] createRemoveBookPositiveData() {
        String name1 = "Война и мир";
        String publishingYear1 = "1990";
        String publishingHouse1 = "Москва";
        List<String> authors1 = Arrays.asList("Лев Толстой");
        List<Book> expected1 = new ArrayList<>();
        expected1.add(bookStorageCreator.getCreatedBooks().get(4));
        String name2 = "This is very very long line with 43 symbols";
        String publishingYear2 = "2020";
        String publishingHouse2 = "Минск";
        List<String> authors2 = Arrays.asList("Лев");
        List<Book> expected2 = new ArrayList<>();
        String name3 = "мир";
        String publishingYear3 = "2020";
        String publishingHouse3 = "This is very very long line with 43 symbols";
        List<String> authors3 = Arrays.asList("Лев");
        List<Book> expected3 = new ArrayList<>();
        String name4 = "мир";
        String publishingYear4 = "2021";
        String publishingHouse4 = "Минск";
        List<String> authors4 = Arrays.asList("Лев");
        List<Book> expected4 = new ArrayList<>();
        String name5 = "мир";
        String publishingYear5 = "2020";
        String publishingHouse5 = "Минск";
        List<String> authors5 =
                Arrays.asList("This is very very long line with 43 symbols");
        List<Book> expected5 = new ArrayList<>();
        String name6 = "Метро 2033";
        String publishingYear6 = "2015";
        String publishingHouse6 = "Киев";
        List<String> authors6 = Arrays.asList("Дмитрий Глуховский");
        List<Book> expected6 = new ArrayList<>();
        expected6.add(bookStorageCreator.getCreatedBooks().get(8));
        return new Object[][]{
                {name1, publishingYear1, publishingHouse1, authors1, expected1},
                {name2, publishingYear2, publishingHouse2, authors2, expected2},
                {name3, publishingYear3, publishingHouse3, authors3, expected3},
                {name4, publishingYear4, publishingHouse4, authors4, expected4},
                {name5, publishingYear5, publishingHouse5, authors5, expected5},
                {name6, publishingYear6, publishingHouse6, authors6, expected6}
        };
    }

    @Test(dataProvider = "removeBookPositiveData")
    public void removeBookPositiveTest(String name, String publishingYear,
                                       String publishingHouse,
                                       List<String> authors,
                                       List<Book> expected) {
        try {
            List<Book> actual = bookService.removeBook(name,
                    publishingYear, publishingHouse, authors);
            boolean result;
            if (actual.size() == 1 && expected.size() == 1) {
                result = actual.get(0).equalsToBook(expected.get(0));
            } else {
                result = actual.equals(expected);
            }
            assertTrue(result);
        } catch (ServiceException e) {
            fail("Incorrect input");
        }
    }

    @DataProvider(name = "removeBookNegativeData")
    public Object[][] createRemoveBookNegativeData() {
        String name1 = "Война и мир";
        String publishingYear1 = "1990";
        String publishingHouse1 = "Москва";
        List<String> authors1 = Arrays.asList("Лев Толстой");
        List<Book> expected1 = new ArrayList<>();
        expected1.add(null);
        String name2 = "This is very very long line with 43 symbols";
        String publishingYear2 = "2020";
        String publishingHouse2 = "Минск";
        List<String> authors2 = Arrays.asList("Лев");
        List<Book> expected2 = null;
        String name3 = "Метро 2033";
        String publishingYear3 = "2015";
        String publishingHouse3 = "Киев";
        List<String> authors3 = Arrays.asList("Дмитрий Глуховский");
        List<Book> expected3 = new ArrayList<>();
        expected3.add(bookStorageCreator.getCreatedBooks().get(1));
        return new Object[][]{
                {name1, publishingYear1, publishingHouse1, authors1, expected1},
                {name2, publishingYear2, publishingHouse2, authors2, expected2},
                {name3, publishingYear3, publishingHouse3, authors3, expected3}
        };
    }

    @Test(dataProvider = "removeBookNegativeData")
    public void removeBookNegativeTest(String name, String publishingYear,
                                       String publishingHouse,
                                       List<String> authors,
                                       List<Book> expected) {
        try {
            List<Book> actual = bookService.removeBook(name, publishingYear,
                    publishingHouse, authors);
            boolean result;
            if (actual.size() == 1 && expected.size() == 1) {
                result = actual.get(0).equalsToBook(expected.get(0));
            } else {
                result = actual.equals(expected);
            }
            assertFalse(result);
        } catch (ServiceException e) {
            fail("Incorrect input");
        }
    }

    @DataProvider(name = "removeBookExceptionData")
    public Object[][] createRemoveBookExceptionData() {
        String name1 = "Война и мир";
        String publishingYear1 = "1990";
        String publishingHouse1 = "Москва";
        List<String> authors1 = Arrays.asList("Толстой");
        String name2 = "Qwe";
        String publishingYear2 = "2020";
        String publishingHouse2 = "Минск";
        List<String> authors2 = Arrays.asList("Лев");
        String name3 = "мир";
        String publishingYear3 = "2020";
        String publishingHouse3 = "Minsk";
        List<String> authors3 = Arrays.asList("Лев");
        String name4 = "мир";
        String publishingYear4 = "1999";
        String publishingHouse4 = "Минск";
        List<String> authors4 = Arrays.asList("Лев");
        String name5 = "мир";
        String publishingYear5 = "2020";
        String publishingHouse5 = "Минск";
        List<String> authors5 = Arrays.asList("Oleg");
        String name6 = "Метро 2033";
        String publishingYear6 = "2015";
        String publishingHouse6 = "Киев";
        List<String> authors6 = Arrays.asList("Дмитрий");
        return new Object[][]{
                {name1, publishingYear1, publishingHouse1, authors1},
                {name2, publishingYear2, publishingHouse2, authors2},
                {name3, publishingYear3, publishingHouse3, authors3},
                {name4, publishingYear4, publishingHouse4, authors4},
                {name5, publishingYear5, publishingHouse5, authors5},
                {name6, publishingYear6, publishingHouse6, authors6}
        };
    }

    @Test(dataProvider = "removeBookExceptionData",
            expectedExceptions = ServiceException.class)
    public void removeBookExceptionTest(String name, String publishingYear,
                                        String publishingHouse,
                                        List<String> authors)
            throws ServiceException {
        bookService.removeBook(name, publishingYear, publishingHouse, authors);
    }

    @Test
    public void findAllBooksPositiveTest() {
        List<Book> actual = bookService.findAllBooks();
        List<Book> expected = bookStorageCreator.getCreatedBooks();
        assertEquals(actual, expected);
    }

    @Test
    public void findAllBooksNegativeTest() {
        List<Book> actual = bookService.findAllBooks();
        List<Book> expected =
                new ArrayList<>(bookStorageCreator.getCreatedBooks());
        expected.add(null);
        assertNotEquals(actual, expected);
    }

    @DataProvider(name = "findBookByIdPositiveData")
    public Object[][] createFindBookByIdPositiveData() {
        String id1 = "1";
        List<Book> expected1 = new ArrayList<>();
        expected1.add(bookStorageCreator.getCreatedBooks().get(0));
        String id2 = "9";
        List<Book> expected2 = new ArrayList<>();
        expected2.add(bookStorageCreator.getCreatedBooks().get(9));
        String id3 = "7";
        List<Book> expected3 = new ArrayList<>();
        expected3.add(bookStorageCreator.getCreatedBooks().get(6));
        String id4 = "100_001";
        List<Book> expected4 = new ArrayList<>();
        String id5 = "-7";
        List<Book> expected5 = new ArrayList<>();
        String id6 = "zbc";
        List<Book> expected6 = new ArrayList<>();
        String id7 = "15";
        List<Book> expected7 = new ArrayList<>();
        return new Object[][]{
                {id1, expected1},
                {id2, expected2},
                {id3, expected3},
                {id4, expected4},
                {id5, expected5},
                {id6, expected6},
                {id7, expected7},
        };
    }

    @Test(dataProvider = "findBookByIdPositiveData")
    public void findBookByIdPositiveTest(String id, List<Book> expected) {
        List<Book> actual = bookService.findBookById(id);
        assertEquals(actual, expected);
    }

    @DataProvider(name = "findBookByIdNegativeData")
    public Object[][] createFindBookByIdNegativeData() {
        String id1 = "1";
        List<Book> expected1 = new ArrayList<>();
        expected1.add(bookStorageCreator.getCreatedBooks().get(0));
        expected1.add(bookStorageCreator.getCreatedBooks().get(0));
        String id2 = "9";
        List<Book> expected2 = new ArrayList<>();
        expected2.add(bookStorageCreator.getCreatedBooks().get(1));
        String id3 = "7";
        List<Book> expected3 = new ArrayList<>();
        String id4 = "100_001";
        List<Book> expected4 = new ArrayList<>();
        expected4.add(bookStorageCreator.getCreatedBooks().get(6));
        String id5 = "-7";
        List<Book> expected5 = null;
        String id6 = "zbc";
        List<Book> expected6 = new ArrayList<>();
        expected6.add(bookStorageCreator.getCreatedBooks().get(0));
        return new Object[][]{
                {id1, expected1},
                {id2, expected2},
                {id3, expected3},
                {id4, expected4},
                {id5, expected5},
                {id6, expected6}
        };
    }

    @Test(dataProvider = "findBookByIdNegativeData")
    public void findBookByIdNegativeTest(String id, List<Book> expected) {
        List<Book> actual = bookService.findBookById(id);
        assertNotEquals(actual, expected);
    }

    @DataProvider(name = "findBooksByNamePositiveData")
    public Object[][] createFindBooksByNamePositiveData() {
        String name1 = "Война и мир";
        List<Book> expected1 = new ArrayList<>();
        expected1.add(bookStorageCreator.getCreatedBooks().get(0));
        expected1.add(bookStorageCreator.getCreatedBooks().get(4));
        String name2 = "Метро 2033";
        List<Book> expected2 = new ArrayList<>();
        expected2.add(bookStorageCreator.getCreatedBooks().get(8));
        String name3 = "я";
        List<Book> expected3 = new ArrayList<>();
        String name4 = "this is very very long line with 43 symbols";
        List<Book> expected4 = new ArrayList<>();
        return new Object[][]{
                {name1, expected1},
                {name2, expected2},
                {name3, expected3},
                {name4, expected4},
        };
    }

    @Test(dataProvider = "findBooksByNamePositiveData")
    public void findBooksByNamePositiveTest(String name, List<Book> expected) {
        List<Book> actual = bookService.findBooksByName(name);
        assertEquals(actual, expected);
    }

    @DataProvider(name = "findBooksByNameNegativeData")
    public Object[][] createFindBooksByNameNegativeData() {
        String name1 = "Война и мир";
        List<Book> expected1 = new ArrayList<>();
        expected1.add(bookStorageCreator.getCreatedBooks().get(0));
        expected1.add(bookStorageCreator.getCreatedBooks().get(3));
        String name2 = "Метро 2033";
        List<Book> expected2 = new ArrayList<>();
        expected2.add(bookStorageCreator.getCreatedBooks().get(8));
        expected2.add(bookStorageCreator.getCreatedBooks().get(8));
        String name3 = "я";
        List<Book> expected3 = new ArrayList<>();
        expected3.add(bookStorageCreator.getCreatedBooks().get(1));
        String name4 = "this is very very long line with 43 symbols";
        List<Book> expected4 = new ArrayList<>();
        expected4.add(bookStorageCreator.getCreatedBooks().get(0));
        return new Object[][]{
                {name1, expected1},
                {name2, expected2},
                {name3, expected3},
                {name4, expected4}
        };
    }

    @Test(dataProvider = "findBooksByNameNegativeData")
    public void findBooksByNameNegativeTest(String name, List<Book> expected) {
        List<Book> actual = bookService.findBooksByName(name);
        assertNotEquals(actual, expected);
    }

    @DataProvider(name = "findBooksByPublishingYearPositiveData")
    public Object[][] createFindBooksByPublishingYearPositiveData() {
        String publishingYear1 = "1000";
        List<Book> expected1 = new ArrayList<>();
        expected1.add(bookStorageCreator.getCreatedBooks().get(2));
        expected1.add(bookStorageCreator.getCreatedBooks().get(6));
        String publishingYear2 = "1984";
        List<Book> expected2 = new ArrayList<>();
        expected2.add(bookStorageCreator.getCreatedBooks().get(0));
        String publishingYear3 = "2015";
        List<Book> expected3 = new ArrayList<>();
        expected3.add(bookStorageCreator.getCreatedBooks().get(8));
        String publishingYear4 = "2016";
        List<Book> expected4 = new ArrayList<>();
        String publishingYear5 = "abc";
        List<Book> expected5 = new ArrayList<>();
        String publishingYear6 = "2500";
        List<Book> expected6 = new ArrayList<>();
        return new Object[][]{
                {publishingYear1, expected1},
                {publishingYear2, expected2},
                {publishingYear3, expected3},
                {publishingYear4, expected4},
                {publishingYear5, expected5},
                {publishingYear6, expected6}
        };
    }

    @Test(dataProvider = "findBooksByPublishingYearPositiveData")
    public void findBooksByPublishingYearPositiveTest(String publishingYear,
                                                      List<Book> expected) {
        List<Book> actual =
                bookService.findBooksByPublishingYear(publishingYear);
        assertEquals(actual, expected);
    }

    @DataProvider(name = "findBooksByPublishingYearNegativeData")
    public Object[][] createFindBooksByPublishingYearNegativeData() {
        String publishingYear1 = "1000";
        List<Book> expected1 = new ArrayList<>();
        expected1.add(bookStorageCreator.getCreatedBooks().get(6));
        expected1.add(bookStorageCreator.getCreatedBooks().get(2));
        String publishingYear2 = "1984";
        List<Book> expected2 = new ArrayList<>();
        expected2.add(bookStorageCreator.getCreatedBooks().get(0));
        expected2.add(bookStorageCreator.getCreatedBooks().get(0));
        String publishingYear3 = "2015";
        List<Book> expected3 = new ArrayList<>();
        String publishingYear4 = "2016";
        List<Book> expected4 = new ArrayList<>();
        expected4.add(bookStorageCreator.getCreatedBooks().get(8));
        String publishingYear5 = "abc";
        List<Book> expected5 = null;
        String publishingYear6 = "2500";
        List<Book> expected6 = new ArrayList<>();
        expected6.add(bookStorageCreator.getCreatedBooks().get(0));
        return new Object[][]{
                {publishingYear1, expected1},
                {publishingYear2, expected2},
                {publishingYear3, expected3},
                {publishingYear4, expected4},
                {publishingYear5, expected5},
                {publishingYear6, expected6}
        };
    }

    @Test(dataProvider = "findBooksByPublishingYearNegativeData")
    public void findBooksByPublishingYearNegativeTest(String publishingYear,
                                                      List<Book> expected) {
        List<Book> actual =
                bookService.findBooksByPublishingYear(publishingYear);
        assertNotEquals(actual, expected);
    }

    @DataProvider(name = "findBooksByPublishingHousePositiveData")
    public Object[][] createFindBooksByPublishingHousePositiveData() {
        String publishingHouse1 = "Минск";
        List<Book> expected1 = new ArrayList<>();
        expected1.add(bookStorageCreator.getCreatedBooks().get(0));
        expected1.add(bookStorageCreator.getCreatedBooks().get(1));
        expected1.add(bookStorageCreator.getCreatedBooks().get(6));
        String publishingHouse2 = "Москва";
        List<Book> expected2 = new ArrayList<>();
        expected2.add(bookStorageCreator.getCreatedBooks().get(4));
        expected2.add(bookStorageCreator.getCreatedBooks().get(7));
        String publishingHouse3 = "Витебск";
        List<Book> expected3 = new ArrayList<>();
        String publishingHouse4 = "this is very very long line with 43 symbols";
        List<Book> expected4 = new ArrayList<>();
        return new Object[][]{
                {publishingHouse1, expected1},
                {publishingHouse2, expected2},
                {publishingHouse3, expected3},
                {publishingHouse4, expected4}
        };
    }

    @Test(dataProvider = "findBooksByPublishingHousePositiveData")
    public void findBooksByPublishingHousePositiveTest(String publishingHouse,
                                                       List<Book> expected) {
        List<Book> actual =
                bookService.findBooksByPublishingHouse(publishingHouse);
        assertEquals(actual, expected);
    }

    @DataProvider(name = "findBooksByPublishingHouseNegativeData")
    public Object[][] createFindBooksByPublishingHouseNegativeData() {
        String publishingHouse1 = "Минск";
        List<Book> expected1 = new ArrayList<>();
        expected1.add(bookStorageCreator.getCreatedBooks().get(0));
        expected1.add(bookStorageCreator.getCreatedBooks().get(1));
        String publishingHouse2 = "Москва";
        List<Book> expected2 = new ArrayList<>();
        expected2.add(bookStorageCreator.getCreatedBooks().get(4));
        expected2.add(bookStorageCreator.getCreatedBooks().get(4));
        expected2.add(bookStorageCreator.getCreatedBooks().get(7));
        String publishingHouse3 = "Витебск";
        List<Book> expected3 = new ArrayList<>();
        expected3.add(bookStorageCreator.getCreatedBooks().get(1));
        String publishingHouse4 = "this is very very long line with 43 symbols";
        List<Book> expected4 = new ArrayList<>();
        expected4.add(bookStorageCreator.getCreatedBooks().get(0));
        return new Object[][]{
                {publishingHouse1, expected1},
                {publishingHouse2, expected2},
                {publishingHouse3, expected3},
                {publishingHouse4, expected4}
        };
    }

    @Test(dataProvider = "findBooksByPublishingHouseNegativeData")
    public void findBooksByPublishingHouseNegativeTest(String publishingHouse,
                                                       List<Book> expected) {
        List<Book> actual =
                bookService.findBooksByPublishingHouse(publishingHouse);
        assertNotEquals(actual, expected);
    }

    @DataProvider(name = "findBooksByAuthorPositiveData")
    public Object[][] createFindBooksByAuthorPositiveData() {
        String author1 = "Oleg";
        List<Book> expected1 = new ArrayList<>();
        expected1.add(bookStorageCreator.getCreatedBooks().get(3));
        expected1.add(bookStorageCreator.getCreatedBooks().get(7));
        expected1.add(bookStorageCreator.getCreatedBooks().get(9));
        String author2 = "Sapolsky";
        List<Book> expected2 = new ArrayList<>();
        expected2.add(bookStorageCreator.getCreatedBooks().get(5));
        String author3 = "Robert";
        List<Book> expected3 = new ArrayList<>();
        expected3.add(bookStorageCreator.getCreatedBooks().get(3));
        expected3.add(bookStorageCreator.getCreatedBooks().get(5));
        String author4 = "oleg";
        List<Book> expected4 = new ArrayList<>();
        String author5 = "this is very very long line with 43 symbols";
        List<Book> expected5 = new ArrayList<>();
        return new Object[][]{
                {author1, expected1},
                {author2, expected2},
                {author3, expected3},
                {author4, expected4},
                {author5, expected5}
        };
    }

    @Test(dataProvider = "findBooksByAuthorPositiveData")
    public void findBooksByAuthorPositiveTest(String author,
                                              List<Book> expected) {
        List<Book> actual = bookService.findBooksByAuthor(author);
        assertEquals(actual, expected);
    }

    @DataProvider(name = "findBooksByAuthorNegativeData")
    public Object[][] createFindBooksByAuthorNegativeData() {
        String author1 = "Oleg";
        List<Book> expected1 = new ArrayList<>();
        expected1.add(bookStorageCreator.getCreatedBooks().get(3));
        expected1.add(bookStorageCreator.getCreatedBooks().get(7));
        String author2 = "Sapolsky";
        List<Book> expected2 = new ArrayList<>();
        expected2.add(bookStorageCreator.getCreatedBooks().get(5));
        expected2.add(bookStorageCreator.getCreatedBooks().get(5));
        String author3 = "Robert";
        List<Book> expected3 = new ArrayList<>();
        expected3.add(bookStorageCreator.getCreatedBooks().get(3));
        String author4 = "oleg";
        List<Book> expected4 = new ArrayList<>();
        expected4.add(bookStorageCreator.getCreatedBooks().get(5));
        String author5 = "this is very very long line with 43 symbols";
        List<Book> expected5 = new ArrayList<>();
        expected5.add(bookStorageCreator.getCreatedBooks().get(0));
        return new Object[][]{
                {author1, expected1},
                {author2, expected2},
                {author3, expected3},
                {author4, expected4},
                {author5, expected5}
        };
    }

    @Test(dataProvider = "findBooksByAuthorNegativeData")
    public void findBooksByAuthorNegativeTest(String author,
                                              List<Book> expected) {
        List<Book> actual = bookService.findBooksByAuthor(author);
        assertNotEquals(actual, expected);
    }

    @Test
    public void sortBooksByIdPositiveTest() {
        List<Book> actual = bookService.sortBooksById();
        List<Book> expected = new ArrayList<>();
        expected.add(bookStorageCreator.getCreatedBooks().get(0));
        expected.add(bookStorageCreator.getCreatedBooks().get(1));
        expected.add(bookStorageCreator.getCreatedBooks().get(2));
        expected.add(bookStorageCreator.getCreatedBooks().get(3));
        expected.add(bookStorageCreator.getCreatedBooks().get(4));
        expected.add(bookStorageCreator.getCreatedBooks().get(5));
        expected.add(bookStorageCreator.getCreatedBooks().get(6));
        expected.add(bookStorageCreator.getCreatedBooks().get(7));
        expected.add(bookStorageCreator.getCreatedBooks().get(9));
        expected.add(bookStorageCreator.getCreatedBooks().get(8));
        assertEquals(actual, expected);
    }

    @Test
    public void sortBooksByIdNegativeTest() {
        List<Book> actual = bookService.sortBooksById();
        List<Book> expected = bookStorageCreator.getCreatedBooks();
        assertNotEquals(actual, expected);
    }

    @Test
    public void sortBooksByNamePositiveTest() {
        List<Book> actual = bookService.sortBooksByName();
        List<Book> expected = new ArrayList<>();
        expected.add(bookStorageCreator.getCreatedBooks().get(5));
        expected.add(bookStorageCreator.getCreatedBooks().get(9));
        expected.add(bookStorageCreator.getCreatedBooks().get(3));
        expected.add(bookStorageCreator.getCreatedBooks().get(0));
        expected.add(bookStorageCreator.getCreatedBooks().get(4));
        expected.add(bookStorageCreator.getCreatedBooks().get(6));
        expected.add(bookStorageCreator.getCreatedBooks().get(8));
        expected.add(bookStorageCreator.getCreatedBooks().get(1));
        expected.add(bookStorageCreator.getCreatedBooks().get(2));
        expected.add(bookStorageCreator.getCreatedBooks().get(7));
        assertEquals(actual, expected);
    }

    @Test
    public void sortBooksByNameNegativeTest() {
        List<Book> actual = bookService.sortBooksByName();
        List<Book> expected = new ArrayList<>();
        expected.add(bookStorageCreator.getCreatedBooks().get(5));
        expected.add(bookStorageCreator.getCreatedBooks().get(9));
        expected.add(bookStorageCreator.getCreatedBooks().get(3));
        expected.add(bookStorageCreator.getCreatedBooks().get(0));
        expected.add(bookStorageCreator.getCreatedBooks().get(4));
        expected.add(bookStorageCreator.getCreatedBooks().get(6));
        expected.add(bookStorageCreator.getCreatedBooks().get(8));
        expected.add(bookStorageCreator.getCreatedBooks().get(1));
        expected.add(bookStorageCreator.getCreatedBooks().get(7));
        expected.add(bookStorageCreator.getCreatedBooks().get(2));
        assertNotEquals(actual, expected);
    }

    @Test
    public void sortBooksByPublishingYearPositiveTest() {
        List<Book> actual = bookService.sortBooksByPublishingYear();
        List<Book> expected = new ArrayList<>();
        expected.add(bookStorageCreator.getCreatedBooks().get(9));
        expected.add(bookStorageCreator.getCreatedBooks().get(2));
        expected.add(bookStorageCreator.getCreatedBooks().get(6));
        expected.add(bookStorageCreator.getCreatedBooks().get(3));
        expected.add(bookStorageCreator.getCreatedBooks().get(7));
        expected.add(bookStorageCreator.getCreatedBooks().get(5));
        expected.add(bookStorageCreator.getCreatedBooks().get(0));
        expected.add(bookStorageCreator.getCreatedBooks().get(4));
        expected.add(bookStorageCreator.getCreatedBooks().get(8));
        expected.add(bookStorageCreator.getCreatedBooks().get(1));
        assertEquals(actual, expected);
    }

    @Test
    public void sortBooksByPublishingYearNegativeTest() {
        List<Book> actual = bookService.sortBooksByPublishingYear();
        List<Book> expected = bookStorageCreator.getCreatedBooks();
        assertNotEquals(actual, expected);
    }

    @Test
    public void sortBooksByPublishingHousePositiveTest() {
        List<Book> actual = bookService.sortBooksByPublishingHouse();
        List<Book> expected = new ArrayList<>();
        expected.add(bookStorageCreator.getCreatedBooks().get(5));
        expected.add(bookStorageCreator.getCreatedBooks().get(9));
        expected.add(bookStorageCreator.getCreatedBooks().get(8));
        expected.add(bookStorageCreator.getCreatedBooks().get(0));
        expected.add(bookStorageCreator.getCreatedBooks().get(1));
        expected.add(bookStorageCreator.getCreatedBooks().get(6));
        expected.add(bookStorageCreator.getCreatedBooks().get(4));
        expected.add(bookStorageCreator.getCreatedBooks().get(7));
        expected.add(bookStorageCreator.getCreatedBooks().get(2));
        expected.add(bookStorageCreator.getCreatedBooks().get(3));
        assertEquals(actual, expected);
    }

    @Test
    public void sortBooksByPublishingHouseNegativeTest() {
        List<Book> actual = bookService.sortBooksByPublishingHouse();
        List<Book> expected = new ArrayList<>();
        expected.add(bookStorageCreator.getCreatedBooks().get(5));
        expected.add(bookStorageCreator.getCreatedBooks().get(9));
        expected.add(bookStorageCreator.getCreatedBooks().get(8));
        expected.add(bookStorageCreator.getCreatedBooks().get(0));
        expected.add(bookStorageCreator.getCreatedBooks().get(1));
        expected.add(bookStorageCreator.getCreatedBooks().get(6));
        expected.add(bookStorageCreator.getCreatedBooks().get(4));
        expected.add(bookStorageCreator.getCreatedBooks().get(7));
        expected.add(bookStorageCreator.getCreatedBooks().get(2));
        assertNotEquals(actual, expected);
    }

    @Test
    public void sortBooksByAuthorsPositiveTest() {
        List<Book> actual = bookService.sortBooksByAuthors();
        List<Book> expected = new ArrayList<>();
        expected.add(bookStorageCreator.getCreatedBooks().get(6));
        expected.add(bookStorageCreator.getCreatedBooks().get(0));
        expected.add(bookStorageCreator.getCreatedBooks().get(2));
        expected.add(bookStorageCreator.getCreatedBooks().get(4));
        expected.add(bookStorageCreator.getCreatedBooks().get(7));
        expected.add(bookStorageCreator.getCreatedBooks().get(8));
        expected.add(bookStorageCreator.getCreatedBooks().get(3));
        expected.add(bookStorageCreator.getCreatedBooks().get(9));
        expected.add(bookStorageCreator.getCreatedBooks().get(1));
        expected.add(bookStorageCreator.getCreatedBooks().get(5));
        assertEquals(actual, expected);
    }

    @Test
    public void sortBooksByAuthorsNegativeTest() {
        List<Book> actual = bookService.sortBooksByAuthors();
        List<Book> expected = new ArrayList<>();
        expected.add(bookStorageCreator.getCreatedBooks().get(6));
        expected.add(bookStorageCreator.getCreatedBooks().get(0));
        expected.add(bookStorageCreator.getCreatedBooks().get(2));
        expected.add(bookStorageCreator.getCreatedBooks().get(4));
        expected.add(bookStorageCreator.getCreatedBooks().get(7));
        expected.add(bookStorageCreator.getCreatedBooks().get(8));
        expected.add(bookStorageCreator.getCreatedBooks().get(3));
        expected.add(bookStorageCreator.getCreatedBooks().get(9));
        expected.add(bookStorageCreator.getCreatedBooks().get(1));
        expected.add(bookStorageCreator.getCreatedBooks().get(5));
        expected.add(bookStorageCreator.getCreatedBooks().get(5));
        assertNotEquals(actual, expected);
    }
}

