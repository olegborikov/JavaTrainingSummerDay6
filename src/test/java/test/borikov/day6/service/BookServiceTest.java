package test.borikov.day6.service;

import com.borikov.day6.model.entity.Book;
import com.borikov.day6.model.entity.BookStorage;
import com.borikov.day6.exception.ServiceException;
import com.borikov.day6.model.service.BookService;
import com.borikov.day6.util.BookStorageCreator;
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.*;

public class BookServiceTest {
    private BookStorage bookStorage;
    private BookService bookService;

    @BeforeClass
    public void setUpClass() {
        bookStorage = BookStorage.getInstance();
        bookService = new BookService();
        BookStorageCreator.setUpBookStorage();
    }

    @AfterClass
    public void tearDownClass() {
        bookService = null;
        bookStorage.reset();
        bookStorage = null;
    }

    @AfterMethod
    public void tearDownMethod() {
        bookStorage.reset();
        BookStorageCreator.setUpBookStorage();
    }

    @DataProvider(name = "addBookInLibraryPositiveData")
    public Object[][] createAddBookInLibraryPositiveData() {
        List<String> authors1 = new ArrayList<>();
        authors1.add("Лев");
        Book newBook1 = new Book("Война", 120, "Минск", authors1);
        List<Book> expected1 = new ArrayList<>(BookStorageCreator.getCreatedBooks());
        expected1.add(newBook1);
        List<String> authors2 = new ArrayList<>();
        authors2.add("Олег");
        Book newBook2 = new Book("Я", 13, "Москва", authors2);
        List<Book> expected2 = new ArrayList<>(BookStorageCreator.getCreatedBooks());
        expected2.add(newBook2);
        List<String> authors3 = new ArrayList<>();
        authors3.add("Саша");
        authors3.add("Олег");
        Book newBook3 = new Book("Мир", 20, "Минск", authors3);
        List<Book> expected3 = new ArrayList<>(BookStorageCreator.getCreatedBooks());
        expected3.add(newBook3);
        return new Object[][]{
                {newBook1, expected1},
                {newBook2, expected2},
                {newBook3, expected3}
        };
    }

    @Test(dataProvider = "addBookInLibraryPositiveData")
    public void addBookInLibraryPositiveTest(Book newBook, List<Book> expected) {
        try {
            bookService.addBookInLibrary(newBook);
            List<Book> actual = bookStorage.get();
            assertEquals(actual, expected);
        } catch (ServiceException e) {
            fail("Incorrect input");
        }
    }

    @DataProvider(name = "addBookInLibraryNegativeData")
    public Object[][] createAddBookInLibraryNegativeData() {
        List<String> authors1 = new ArrayList<>();
        authors1.add("Лев");
        Book newBook1 = new Book("Война", 120, "Минск", authors1);
        List<Book> expected1 = new ArrayList<>(BookStorageCreator.getCreatedBooks());
        List<String> authors2 = new ArrayList<>();
        authors2.add("Олег");
        Book newBook2 = new Book("Я", 13, "Москва", authors2);
        List<Book> expected2 = new ArrayList<>(BookStorageCreator.getCreatedBooks());
        expected2.add(newBook1);
        expected2.add(newBook2);
        List<String> authors3 = new ArrayList<>();
        authors3.add("Саша");
        authors3.add("Олег");
        Book newBook3 = new Book("Мир", 20, "Минск", authors3);
        List<Book> expected3 = new ArrayList<>(BookStorageCreator.getCreatedBooks());
        expected3.add(newBook2);
        expected3.add(newBook3);
        return new Object[][]{
                {newBook1, expected1},
                {newBook2, expected2},
                {newBook3, expected3}
        };
    }

    @Test(dataProvider = "addBookInLibraryNegativeData")
    public void addBookInLibraryNegativeTest(Book newBook, List<Book> expected) {
        try {
            bookService.addBookInLibrary(newBook);
            List<Book> actual = bookStorage.get();
            assertNotEquals(actual, expected);
        } catch (ServiceException e) {
            fail("Incorrect input");
        }
    }

    @DataProvider(name = "addBookInLibraryExceptionData")
    public Object[][] createAddBookInLibraryExceptionData() {
        Book newBook1 = BookStorageCreator.getCreatedBooks().get(1);
        Book newBook2 = BookStorageCreator.getCreatedBooks().get(2);
        Book newBook3 = BookStorageCreator.getCreatedBooks().get(9);
        Book newBook4 = new Book("Qwerty", 1001, "qwerty", new ArrayList<>());
        Book newBook5 = new Book("Qwertyddsadasdasd ada dasdsa asd as das asd a",
                100, "qwerty", new ArrayList<>());
        Book newBook6 = new Book("Qwerty", 100,
                "Qwertyddsadasdasd ada dasdsa asd as das asd a", new ArrayList<>());
        List<String> authors7 = new ArrayList<>();
        authors7.add(null);
        Book newBook7 = new Book("Qwerty", 100,
                "Qwertyddsadasdasd ada dasdsa asd as das asd a", authors7);
        return new Object[][]{
                {newBook1},
                {newBook2},
                {newBook3},
                {newBook4},
                {newBook5},
                {newBook6},
                {newBook7},
                {null},
        };
    }

    @Test(dataProvider = "addBookInLibraryExceptionData",
            expectedExceptions = ServiceException.class)
    public void addBookInLibraryExceptionTest(Book newBook) throws ServiceException {
        bookService.addBookInLibrary(newBook);
    }

    @DataProvider(name = "removeBookFromLibraryPositiveData")
    public Object[][] createRemoveBookFromLibraryPositiveData() {
        Book removeBook1 = BookStorageCreator.getCreatedBooks().get(1);
        List<Book> expected1 = new ArrayList<>(BookStorageCreator.getCreatedBooks());
        expected1.remove(removeBook1);
        Book removeBook2 = BookStorageCreator.getCreatedBooks().get(5);
        List<Book> expected2 = new ArrayList<>(BookStorageCreator.getCreatedBooks());
        expected2.remove(removeBook2);
        Book removeBook3 = BookStorageCreator.getCreatedBooks().get(7);
        List<Book> expected3 = new ArrayList<>(BookStorageCreator.getCreatedBooks());
        expected3.remove(removeBook3);
        return new Object[][]{
                {removeBook1, expected1},
                {removeBook2, expected2},
                {removeBook3, expected3}
        };
    }

    @Test(dataProvider = "removeBookFromLibraryPositiveData")
    public void removeBookFromLibraryPositiveTest(Book removeBook, List<Book> expected) {
        try {
            bookService.removeBookFromLibrary(removeBook);
            List<Book> actual = bookStorage.get();
            assertEquals(actual, expected);
        } catch (ServiceException e) {
            fail("Incorrect input");
        }
    }

    @DataProvider(name = "removeBookFromLibraryNegativeData")
    public Object[][] createRemoveBookNegativeData() {
        Book removeBook1 = BookStorageCreator.getCreatedBooks().get(1);
        List<Book> expected1 = new ArrayList<>(BookStorageCreator.getCreatedBooks());
        Book removeBook2 = BookStorageCreator.getCreatedBooks().get(5);
        List<Book> expected2 = new ArrayList<>(BookStorageCreator.getCreatedBooks());
        expected2.remove(removeBook1);
        Book removeBook3 = BookStorageCreator.getCreatedBooks().get(7);
        List<Book> expected3 = new ArrayList<>(BookStorageCreator.getCreatedBooks());
        expected3.remove(removeBook1);
        expected3.remove(removeBook2);
        return new Object[][]{
                {removeBook1, expected1},
                {removeBook2, expected2},
                {removeBook3, expected3}
        };
    }

    @Test(dataProvider = "removeBookFromLibraryNegativeData")
    public void removeBookFromLibraryNegativeTest(Book removeBook, List<Book> expected) {
        try {
            bookService.removeBookFromLibrary(removeBook);
            List<Book> actual = bookStorage.get();
            assertNotEquals(actual, expected);
        } catch (ServiceException e) {
            fail("Incorrect input");
        }
    }

    @DataProvider(name = "removeBookFromLibraryExceptionData")
    public Object[][] createRemoveBookExceptionData() {
        List<String> authors1 = new ArrayList<>();
        authors1.add("Лев");
        Book removeBook1 = new Book("Война", 120, "Минск", authors1);
        List<String> authors2 = new ArrayList<>();
        authors2.add("Олег");
        Book removeBook2 = new Book("Я", 13, "Москва", authors2);
        List<String> authors3 = new ArrayList<>();
        authors3.add("Саша");
        authors3.add("Олег");
        Book removeBook3 = new Book("Мир", 20, "Минск", authors3);
        return new Object[][]{
                {removeBook1},
                {removeBook2},
                {removeBook3},
                {null}
        };
    }

    @Test(dataProvider = "removeBookFromLibraryExceptionData",
            expectedExceptions = ServiceException.class)
    public void removeBookFromLibraryExceptionTest(Book removeBook)
            throws ServiceException {
        bookService.removeBookFromLibrary(removeBook);
    }

    @Test
    public void findAllBooksInLibraryPositiveTest() {
        List<Book> actual = bookService.findAllBooksInLibrary();
        List<Book> expected = new ArrayList<>(BookStorageCreator.getCreatedBooks());
        assertEquals(actual, expected);
    }

    @Test
    public void findAllBooksInLibraryNegativeTest() {
        List<Book> actual = bookService.findAllBooksInLibrary();
        List<Book> expected = new ArrayList<>(BookStorageCreator.getCreatedBooks());
        expected.add(null);
        assertNotEquals(actual, expected);
    }

    @DataProvider(name = "findBookByIdInLibraryPositiveData")
    public Object[][] createFindBookByIdInLibraryPositiveData() {
        long id1 = 1;
        Book expected1 = BookStorageCreator.getCreatedBooks().get(0);
        long id2 = 9;
        Book expected2 = BookStorageCreator.getCreatedBooks().get(9);
        long id3 = 7;
        Book expected3 = BookStorageCreator.getCreatedBooks().get(6);
        return new Object[][]{
                {id1, expected1},
                {id2, expected2},
                {id3, expected3}
        };
    }

    @Test(dataProvider = "findBookByIdInLibraryPositiveData")
    public void findBookByIdInLibraryPositiveTest(long id, Book expected) {
        try {
            Book actual = bookService.findBookByIdInLibrary(id);
            assertEquals(actual, expected);
        } catch (ServiceException e) {
            fail("Incorrect input");
        }
    }

    @DataProvider(name = "findBookByIdInLibraryNegativeData")
    public Object[][] createFindBookByIdInLibraryNegativeData() {
        long id1 = 1;
        Book expected1 = BookStorageCreator.getCreatedBooks().get(1);
        long id2 = 9;
        Book expected2 = BookStorageCreator.getCreatedBooks().get(3);
        long id3 = 7;
        Book expected3 = BookStorageCreator.getCreatedBooks().get(2);
        return new Object[][]{
                {id1, expected1},
                {id2, expected2},
                {id3, expected3}
        };
    }

    @Test(dataProvider = "findBookByIdInLibraryNegativeData")
    public void findBookByIdInLibraryNegativeTest(long id, Book expected) {
        try {
            Book actual = bookService.findBookByIdInLibrary(id);
            assertNotEquals(actual, expected);
        } catch (ServiceException e) {
            fail("Incorrect input");
        }
    }

    @DataProvider(name = "findBookByIdInLibraryExceptionData")
    public Object[][] createFindBookByIdInLibraryExceptionData() {
        long id1 = -1;
        long id2 = 100_001;
        long id3 = 12;
        return new Object[][]{
                {id1},
                {id2},
                {id3}
        };
    }

    @Test(dataProvider = "findBookByIdInLibraryExceptionData",
            expectedExceptions = ServiceException.class)
    public void findBookByIdInLibraryExceptionTest(long id) throws ServiceException {
        bookService.findBookByIdInLibrary(id);
    }

    @DataProvider(name = "findBooksByNameInLibraryPositiveData")
    public Object[][] createFindBooksByNameInLibraryPositiveData() {
        String name1 = "Война и мир";
        List<Book> expected1 = new ArrayList<>();
        expected1.add(BookStorageCreator.getCreatedBooks().get(0));
        expected1.add(BookStorageCreator.getCreatedBooks().get(4));
        String name2 = "Метро 2033";
        List<Book> expected2 = new ArrayList<>();
        expected2.add(BookStorageCreator.getCreatedBooks().get(8));
        String name3 = "я";
        List<Book> expected3 = new ArrayList<>();
        return new Object[][]{
                {name1, expected1},
                {name2, expected2},
                {name3, expected3}
        };
    }

    @Test(dataProvider = "findBooksByNameInLibraryPositiveData")
    public void findBooksByNameInLibraryPositiveTest(String name, List<Book> expected) {
        try {
            List<Book> actual = bookService.findBooksByNameInLibrary(name);
            assertEquals(actual, expected);
        } catch (ServiceException e) {
            fail("Incorrect input");
        }
    }

    @DataProvider(name = "findBooksByNameInLibraryNegativeData")
    public Object[][] createFindBooksByNameInLibraryNegativeData() {
        String name1 = "Война и мир";
        List<Book> expected1 = new ArrayList<>();
        expected1.add(BookStorageCreator.getCreatedBooks().get(0));
        expected1.add(BookStorageCreator.getCreatedBooks().get(3));
        String name2 = "Метро 2033";
        List<Book> expected2 = new ArrayList<>();
        expected2.add(BookStorageCreator.getCreatedBooks().get(8));
        expected2.add(BookStorageCreator.getCreatedBooks().get(8));
        String name3 = "я";
        List<Book> expected3 = new ArrayList<>();
        expected3.add(BookStorageCreator.getCreatedBooks().get(1));
        return new Object[][]{
                {name1, expected1},
                {name2, expected2},
                {name3, expected3}
        };
    }

    @Test(dataProvider = "findBooksByNameInLibraryNegativeData")
    public void findBooksByNameInLibraryNegativeTest(String name, List<Book> expected) {
        try {
            List<Book> actual = bookService.findBooksByNameInLibrary(name);
            assertNotEquals(actual, expected);
        } catch (ServiceException e) {
            fail("Incorrect input");
        }
    }

    @Test(expectedExceptions = ServiceException.class)
    public void findBooksByNameInLibraryExceptionTest()
            throws ServiceException {
        String name = "Война и мирqweqdrehfhfghfghg dfsfds sdf sdffh";
        bookService.findBooksByNameInLibrary(name);
    }

    @DataProvider(name = "findBooksByPriceInLibraryPositiveData")
    public Object[][] createFindBooksByPriceInLibraryPositiveData() {
        double price1 = 100;
        List<Book> expected1 = new ArrayList<>();
        expected1.add(BookStorageCreator.getCreatedBooks().get(0));
        expected1.add(BookStorageCreator.getCreatedBooks().get(4));
        expected1.add(BookStorageCreator.getCreatedBooks().get(8));
        double price2 = 1000;
        List<Book> expected2 = new ArrayList<>();
        expected2.add(BookStorageCreator.getCreatedBooks().get(2));
        double price3 = 1;
        List<Book> expected3 = new ArrayList<>();
        return new Object[][]{
                {price1, expected1},
                {price2, expected2},
                {price3, expected3}
        };
    }

    @Test(dataProvider = "findBooksByPriceInLibraryPositiveData")
    public void findBooksByPriceInLibraryPositiveTest(double price,
                                                      List<Book> expected) {
        try {
            List<Book> actual = bookService.findBooksByPriceInLibrary(price);
            assertEquals(actual, expected);
        } catch (ServiceException e) {
            fail("Incorrect input");
        }
    }

    @DataProvider(name = "findBooksByPriceInLibraryNegativeData")
    public Object[][] createFindBooksByPriceInLibraryNegativeData() {
        double price1 = 100;
        List<Book> expected1 = new ArrayList<>();
        expected1.add(BookStorageCreator.getCreatedBooks().get(0));
        expected1.add(BookStorageCreator.getCreatedBooks().get(4));
        double price2 = 1000;
        List<Book> expected2 = new ArrayList<>();
        expected2.add(BookStorageCreator.getCreatedBooks().get(2));
        expected2.add(BookStorageCreator.getCreatedBooks().get(2));
        double price3 = 1;
        List<Book> expected3 = new ArrayList<>();
        expected3.add(BookStorageCreator.getCreatedBooks().get(2));
        return new Object[][]{
                {price1, expected1},
                {price2, expected2},
                {price3, expected3}
        };
    }

    @Test(dataProvider = "findBooksByPriceInLibraryNegativeData")
    public void findBooksByPriceInLibraryNegativeTest(double price,
                                                      List<Book> expected) {
        try {
            List<Book> actual = bookService.findBooksByPriceInLibrary(price);
            assertNotEquals(actual, expected);
        } catch (ServiceException e) {
            fail("Incorrect input");
        }
    }

    @DataProvider(name = "findBooksByPriceInLibraryExceptionTest")
    public Object[][] createFindBooksByPriceInLibraryExceptionTest() {
        double price1 = 0;
        double price2 = 1001;
        double price3 = -1;
        return new Object[][]{
                {price1},
                {price2},
                {price3}
        };
    }

    @Test(dataProvider = "findBooksByPriceInLibraryExceptionTest",
            expectedExceptions = ServiceException.class)
    public void findBooksByPriceInLibraryExceptionTest(double price)
            throws ServiceException {
        bookService.findBooksByPriceInLibrary(price);
    }

    @DataProvider(name = "findBooksByPublishingHouseInLibraryPositiveData")
    public Object[][] createFindBooksByPublishingHouseInLibraryPositiveData() {
        String publishingHouse1 = "Минск";
        List<Book> expected1 = new ArrayList<>();
        expected1.add(BookStorageCreator.getCreatedBooks().get(0));
        expected1.add(BookStorageCreator.getCreatedBooks().get(1));
        expected1.add(BookStorageCreator.getCreatedBooks().get(6));
        String publishingHouse2 = "Москва";
        List<Book> expected2 = new ArrayList<>();
        expected2.add(BookStorageCreator.getCreatedBooks().get(4));
        expected2.add(BookStorageCreator.getCreatedBooks().get(7));
        String publishingHouse3 = "Витебск";
        List<Book> expected3 = new ArrayList<>();
        return new Object[][]{
                {publishingHouse1, expected1},
                {publishingHouse2, expected2},
                {publishingHouse3, expected3}
        };
    }

    @Test(dataProvider = "findBooksByPublishingHouseInLibraryPositiveData")
    public void findBooksByPublishingHouseInLibraryPositiveTest(String publishingHouse,
                                                                List<Book> expected) {
        try {
            List<Book> actual =
                    bookService.findBooksByPublishingHouseInLibrary(publishingHouse);
            assertEquals(actual, expected);
        } catch (ServiceException e) {
            fail("Incorrect input");
        }
    }

    @DataProvider(name = "findBooksByPublishingHouseInLibraryNegativeData")
    public Object[][] createFindBooksByPublishingHouseInLibraryNegativeData() {
        String publishingHouse1 = "Минск";
        List<Book> expected1 = new ArrayList<>();
        expected1.add(BookStorageCreator.getCreatedBooks().get(0));
        expected1.add(BookStorageCreator.getCreatedBooks().get(1));
        String publishingHouse2 = "Москва";
        List<Book> expected2 = new ArrayList<>();
        expected2.add(BookStorageCreator.getCreatedBooks().get(4));
        expected2.add(BookStorageCreator.getCreatedBooks().get(4));
        expected2.add(BookStorageCreator.getCreatedBooks().get(7));
        String publishingHouse3 = "Витебск";
        List<Book> expected3 = new ArrayList<>();
        expected3.add(BookStorageCreator.getCreatedBooks().get(1));
        return new Object[][]{
                {publishingHouse1, expected1},
                {publishingHouse2, expected2},
                {publishingHouse3, expected3}
        };
    }

    @Test(dataProvider = "findBooksByPublishingHouseInLibraryNegativeData")
    public void findBooksByPublishingHouseInLibraryNegativeTest(String publishingHouse,
                                                                List<Book> expected) {
        try {
            List<Book> actual =
                    bookService.findBooksByPublishingHouseInLibrary(publishingHouse);
            assertNotEquals(actual, expected);
        } catch (ServiceException e) {
            fail("Incorrect input");
        }
    }

    @Test(expectedExceptions = ServiceException.class)
    public void findBooksByPublishingHouseInLibraryExceptionTest()
            throws ServiceException {
        String publishingHouse = "dqwesdasdas asd ad ads sa dasd dsadasdsa asd a";
        bookService.findBooksByPublishingHouseInLibrary(publishingHouse);
    }

    @DataProvider(name = "findBooksByAuthorInLibraryPositiveData")
    public Object[][] createFindBooksByAuthorInLibraryPositiveData() {
        String author1 = "Oleg";
        List<Book> expected1 = new ArrayList<>();
        expected1.add(BookStorageCreator.getCreatedBooks().get(3));
        expected1.add(BookStorageCreator.getCreatedBooks().get(7));
        expected1.add(BookStorageCreator.getCreatedBooks().get(9));
        String author2 = "Sapolsky";
        List<Book> expected2 = new ArrayList<>();
        expected2.add(BookStorageCreator.getCreatedBooks().get(5));
        String author3 = "Robert";
        List<Book> expected3 = new ArrayList<>();
        expected3.add(BookStorageCreator.getCreatedBooks().get(3));
        expected3.add(BookStorageCreator.getCreatedBooks().get(5));
        String author4 = "oleg";
        List<Book> expected4 = new ArrayList<>();
        return new Object[][]{
                {author1, expected1},
                {author2, expected2},
                {author3, expected3},
                {author4, expected4}
        };
    }

    @Test(dataProvider = "findBooksByAuthorInLibraryPositiveData")
    public void findBooksByAuthorInLibraryPositiveTest(String author,
                                                       List<Book> expected) {
        try {
            List<Book> actual = bookService.findBooksByAuthorInLibrary(author);
            assertEquals(actual, expected);
        } catch (ServiceException e) {
            fail("Incorrect input");
        }
    }

    @DataProvider(name = "findBooksByAuthorInLibraryNegativeData")
    public Object[][] createFindBooksByAuthorInLibraryNegativeData() {
        String author1 = "Oleg";
        List<Book> expected1 = new ArrayList<>();
        expected1.add(BookStorageCreator.getCreatedBooks().get(3));
        expected1.add(BookStorageCreator.getCreatedBooks().get(7));
        String author2 = "Sapolsky";
        List<Book> expected2 = new ArrayList<>();
        expected2.add(BookStorageCreator.getCreatedBooks().get(5));
        expected2.add(BookStorageCreator.getCreatedBooks().get(5));
        String author3 = "Robert";
        List<Book> expected3 = new ArrayList<>();
        expected3.add(BookStorageCreator.getCreatedBooks().get(3));
        String author4 = "oleg";
        List<Book> expected4 = new ArrayList<>();
        expected4.add(BookStorageCreator.getCreatedBooks().get(5));
        return new Object[][]{
                {author1, expected1},
                {author2, expected2},
                {author3, expected3},
                {author4, expected4}
        };
    }

    @Test(dataProvider = "findBooksByAuthorInLibraryNegativeData")
    public void findBooksByAuthorInLibraryNegativeTest(String author,
                                                       List<Book> expected) {
        try {
            List<Book> actual = bookService.findBooksByAuthorInLibrary(author);
            assertNotEquals(actual, expected);
        } catch (ServiceException e) {
            fail("Incorrect input");
        }
    }

    @Test(expectedExceptions = ServiceException.class)
    public void findBooksByAuthorInLibraryExceptionTest() throws ServiceException {
        String author = "dqwesdasdas asd ad ads sa dasd dsadasdsa asd a";
        bookService.findBooksByAuthorInLibrary(author);
    }

    @Test
    public void sortBooksByIdInLibraryPositiveTest() {
        List<Book> actual = bookService.sortBooksByIdInLibrary();
        List<Book> expected = new ArrayList<>();
        expected.add(BookStorageCreator.getCreatedBooks().get(0));
        expected.add(BookStorageCreator.getCreatedBooks().get(1));
        expected.add(BookStorageCreator.getCreatedBooks().get(2));
        expected.add(BookStorageCreator.getCreatedBooks().get(3));
        expected.add(BookStorageCreator.getCreatedBooks().get(4));
        expected.add(BookStorageCreator.getCreatedBooks().get(5));
        expected.add(BookStorageCreator.getCreatedBooks().get(6));
        expected.add(BookStorageCreator.getCreatedBooks().get(7));
        expected.add(BookStorageCreator.getCreatedBooks().get(9));
        expected.add(BookStorageCreator.getCreatedBooks().get(8));
        assertEquals(actual, expected);
    }

    @Test
    public void sortBooksByIdInLibraryNegativeTest() {
        List<Book> actual = bookService.sortBooksByIdInLibrary();
        List<Book> expected = new ArrayList<>(BookStorageCreator.getCreatedBooks());
        assertNotEquals(actual, expected);
    }

    @Test
    public void sortBooksByNameInLibraryPositiveTest() {
        List<Book> actual = bookService.sortBooksByNameInLibrary();
        List<Book> expected = new ArrayList<>();
        expected.add(BookStorageCreator.getCreatedBooks().get(5));
        expected.add(BookStorageCreator.getCreatedBooks().get(9));
        expected.add(BookStorageCreator.getCreatedBooks().get(3));
        expected.add(BookStorageCreator.getCreatedBooks().get(0));
        expected.add(BookStorageCreator.getCreatedBooks().get(4));
        expected.add(BookStorageCreator.getCreatedBooks().get(6));
        expected.add(BookStorageCreator.getCreatedBooks().get(8));
        expected.add(BookStorageCreator.getCreatedBooks().get(1));
        expected.add(BookStorageCreator.getCreatedBooks().get(2));
        expected.add(BookStorageCreator.getCreatedBooks().get(7));
        assertEquals(actual, expected);
    }

    @Test
    public void sortBooksByNameInLibraryNegativeTest() {
        List<Book> actual = bookService.sortBooksByNameInLibrary();
        List<Book> expected = new ArrayList<>(BookStorageCreator.getCreatedBooks());
        assertNotEquals(actual, expected);
    }

    @Test
    public void sortBooksByPriceInLibraryPositiveTest() {
        List<Book> actual = bookService.sortBooksByPriceInLibrary();
        List<Book> expected = new ArrayList<>();
        expected.add(BookStorageCreator.getCreatedBooks().get(6));
        expected.add(BookStorageCreator.getCreatedBooks().get(1));
        expected.add(BookStorageCreator.getCreatedBooks().get(3));
        expected.add(BookStorageCreator.getCreatedBooks().get(5));
        expected.add(BookStorageCreator.getCreatedBooks().get(0));
        expected.add(BookStorageCreator.getCreatedBooks().get(4));
        expected.add(BookStorageCreator.getCreatedBooks().get(8));
        expected.add(BookStorageCreator.getCreatedBooks().get(9));
        expected.add(BookStorageCreator.getCreatedBooks().get(7));
        expected.add(BookStorageCreator.getCreatedBooks().get(2));
        assertEquals(actual, expected);
    }

    @Test
    public void sortBooksByPriceInLibraryNegativeTest() {
        List<Book> actual = bookService.sortBooksByPriceInLibrary();
        List<Book> expected = new ArrayList<>(BookStorageCreator.getCreatedBooks());
        assertNotEquals(actual, expected);
    }

    @Test
    public void sortBooksByPublishingHouseInLibraryPositiveTest() {
        List<Book> actual = bookService.sortBooksByPublishingHouseInLibrary();
        List<Book> expected = new ArrayList<>();
        expected.add(BookStorageCreator.getCreatedBooks().get(5));
        expected.add(BookStorageCreator.getCreatedBooks().get(9));
        expected.add(BookStorageCreator.getCreatedBooks().get(8));
        expected.add(BookStorageCreator.getCreatedBooks().get(0));
        expected.add(BookStorageCreator.getCreatedBooks().get(1));
        expected.add(BookStorageCreator.getCreatedBooks().get(6));
        expected.add(BookStorageCreator.getCreatedBooks().get(4));
        expected.add(BookStorageCreator.getCreatedBooks().get(7));
        expected.add(BookStorageCreator.getCreatedBooks().get(2));
        expected.add(BookStorageCreator.getCreatedBooks().get(3));
        assertEquals(actual, expected);
    }

    @Test
    public void sortBooksByPublishingHouseInLibraryNegativeTest() {
        List<Book> actual = bookService.sortBooksByPublishingHouseInLibrary();
        List<Book> expected = new ArrayList<>(BookStorageCreator.getCreatedBooks());
        assertNotEquals(actual, expected);
    }

    @Test
    public void sortBooksByAuthorsInLibraryPositiveTest() {
        List<Book> actual = bookService.sortBooksByAuthorsInLibrary();
        List<Book> expected = new ArrayList<>();
        expected.add(BookStorageCreator.getCreatedBooks().get(6));
        expected.add(BookStorageCreator.getCreatedBooks().get(0));
        expected.add(BookStorageCreator.getCreatedBooks().get(2));
        expected.add(BookStorageCreator.getCreatedBooks().get(4));
        expected.add(BookStorageCreator.getCreatedBooks().get(7));
        expected.add(BookStorageCreator.getCreatedBooks().get(8));
        expected.add(BookStorageCreator.getCreatedBooks().get(3));
        expected.add(BookStorageCreator.getCreatedBooks().get(9));
        expected.add(BookStorageCreator.getCreatedBooks().get(1));
        expected.add(BookStorageCreator.getCreatedBooks().get(5));
        assertEquals(actual, expected);
    }

    @Test
    public void sortBooksByAuthorsInLibraryNegativeTest() {
        List<Book> actual = bookService.sortBooksByAuthorsInLibrary();
        List<Book> expected = new ArrayList<>(BookStorageCreator.getCreatedBooks());
        assertNotEquals(actual, expected);
    }
}
