package test.borikov.day6.model.service.impl;

import com.borikov.day6.exception.ServiceException;
import com.borikov.day6.model.entity.Book;
import com.borikov.day6.model.service.BookService;
import com.borikov.day6.model.service.impl.BookServiceImpl;
import org.testng.annotations.*;
import test.borikov.day6.creator.BookStorageCreator;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.*;

public class BookServiceImplTest {
    private BookService bookService;

    @BeforeClass
    public void setUpClass() {
        bookService = new BookServiceImpl();
        BookStorageCreator.setUpBookStorage();
    }

    @AfterClass
    public void tearDownClass() {
        bookService = null;
    }

    @AfterMethod
    public void tearDownMethod() {
        BookStorageCreator.setUpBookStorage();
    }

    @DataProvider(name = "addBookPositiveData")
    public Object[][] createAddBookPositiveData() {
        List<String> authors1 = new ArrayList<>();
        authors1.add("Лев");
        Book newBook1 = new Book("Война", 120, "Минск", authors1);
        List<Book> expected1 = new ArrayList<>();
        expected1.add(newBook1);
        List<String> authors2 = new ArrayList<>();
        authors2.add("Олег");
        Book newBook2 = new Book("Я", 13, "Москва", authors2);
        List<Book> expected2 = new ArrayList<>();
        expected2.add(newBook2);
        List<String> authors3 = new ArrayList<>();
        authors3.add("Саша");
        authors3.add("Олег");
        Book newBook3 = new Book("Мир", 20, "Минск", authors3);
        List<Book> expected3 = new ArrayList<>();
        expected3.add(newBook3);
        Book newBook4 = new Book("Qwerty", 1001, "qwerty", new ArrayList<>());
        List<String> expected4 = new ArrayList<>();
        Book newBook5 = new Book("this is very very long line with 43 symbols",
                100, "qwerty", new ArrayList<>());
        List<String> expected5 = new ArrayList<>();
        Book newBook6 = new Book("Qwerty", 100,
                "this is very very long line with 43 symbols", new ArrayList<>());
        List<String> expected6 = new ArrayList<>();
        List<String> authors7 = new ArrayList<>();
        authors7.add(null);
        Book newBook7 = new Book("Qwerty", 100,
                "this is very very long line with 43 symbols", authors7);
        List<String> expected7 = new ArrayList<>();
        List<String> expected8 = new ArrayList<>();
        return new Object[][]{
                {newBook1, expected1},
                {newBook2, expected2},
                {newBook3, expected3},
                {newBook4, expected4},
                {newBook5, expected5},
                {newBook6, expected6},
                {newBook7, expected7},
                {null, expected8},
        };
    }

    @Test(dataProvider = "addBookPositiveData")
    public void addBookPositiveTest(Book newBook, List<Book> expected) {
        try {
            List<Book> actual = bookService.addBook(newBook);
            assertEquals(actual, expected);
        } catch (ServiceException e) {
            fail("Incorrect input");
        }
    }

    @DataProvider(name = "addBookNegativeData")
    public Object[][] createAddBookNegativeData() {
        List<String> authors1 = new ArrayList<>();
        authors1.add("Лев");
        Book newBook1 = new Book("Война", 120, "Минск", authors1);
        List<Book> expected1 = new ArrayList<>(BookStorageCreator.getCreatedBooks());
        List<String> authors2 = new ArrayList<>();
        authors2.add("Олег");
        Book newBook2 = new Book("Я", 13, "Москва", authors2);
        List<Book> expected2 = new ArrayList<>();
        List<String> authors3 = new ArrayList<>();
        authors3.add("Саша");
        authors3.add("Олег");
        Book newBook3 = new Book("Мир", 20, "Минск", authors3);
        List<Book> expected3 = new ArrayList<>();
        expected3.add(newBook2);
        expected3.add(newBook3);
        Book newBook4 = new Book("Qwerty", 100,
                "this is very very long line with 43 symbols", new ArrayList<>());
        List<Book> expected4 = new ArrayList<>();
        expected4.add(newBook4);
        List<Book> expected5 = new ArrayList<>();
        expected5.add(newBook4);
        return new Object[][]{
                {newBook1, expected1},
                {newBook2, expected2},
                {newBook3, expected3},
                {newBook4, expected4},
                {null, expected5}
        };
    }

    @Test(dataProvider = "addBookNegativeData")
    public void addBookNegativeTest(Book newBook, List<Book> expected) {
        try {
            List<Book> actual = bookService.addBook(newBook);
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
        return new Object[][]{
                {newBook1},
                {newBook2},
                {newBook3}
        };
    }

    @Test(dataProvider = "addBookInLibraryExceptionData",
            expectedExceptions = ServiceException.class)
    public void addBookInLibraryExceptionTest(Book newBook) throws ServiceException {
        bookService.addBook(newBook);
    }

    @DataProvider(name = "removeBookPositiveData")
    public Object[][] createRemoveBookPositiveData() {
        Book removeBook1 = BookStorageCreator.getCreatedBooks().get(1);
        List<Book> expected1 = new ArrayList<>();
        expected1.add(removeBook1);
        Book removeBook2 = BookStorageCreator.getCreatedBooks().get(5);
        List<Book> expected2 = new ArrayList<>();
        expected2.add(removeBook2);
        Book removeBook3 = BookStorageCreator.getCreatedBooks().get(7);
        List<Book> expected3 = new ArrayList<>();
        expected3.add(removeBook3);
        List<String> expected8 = new ArrayList<>();
        return new Object[][]{
                {removeBook1, expected1},
                {removeBook2, expected2},
                {removeBook3, expected3},
                {null, expected8}
        };
    }

    @Test(dataProvider = "removeBookPositiveData")
    public void removeBookPositiveTest(Book removeBook, List<Book> expected) {
        try {
            List<Book> actual = bookService.removeBook(removeBook);
            assertEquals(actual, expected);
        } catch (ServiceException e) {
            fail("Incorrect input");
        }
    }

    @DataProvider(name = "removeBookNegativeData")
    public Object[][] createRemoveBookNegativeData() {
        Book removeBook1 = BookStorageCreator.getCreatedBooks().get(1);
        List<Book> expected1 = new ArrayList<>();
        Book removeBook2 = BookStorageCreator.getCreatedBooks().get(5);
        List<Book> expected2 = new ArrayList<>();
        expected2.add(BookStorageCreator.getCreatedBooks().get(4));
        Book removeBook3 = BookStorageCreator.getCreatedBooks().get(7);
        List<Book> expected3 = new ArrayList<>();
        expected3.add(removeBook2);
        return new Object[][]{
                {removeBook1, expected1},
                {removeBook2, expected2},
                {removeBook3, expected3}
        };
    }

    @Test(dataProvider = "removeBookNegativeData")
    public void removeBookNegativeTest(Book removeBook, List<Book> expected) {
        try {
            List<Book> actual = bookService.removeBook(removeBook);
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
                {removeBook3}
        };
    }

    @Test(dataProvider = "removeBookFromLibraryExceptionData",
            expectedExceptions = ServiceException.class)
    public void removeBookFromLibraryExceptionTest(Book removeBook)
            throws ServiceException {
        bookService.removeBook(removeBook);
    }

    @Test
    public void findAllBooksPositiveTest() {
        try {
            List<Book> actual = bookService.findAllBooks();
            List<Book> expected = new ArrayList<>(BookStorageCreator.getCreatedBooks());
            assertEquals(actual, expected);
        } catch (ServiceException e) {
            fail("Incorrect input");
        }
    }

    @Test
    public void findAllBooksNegativeTest() {
        try {
            List<Book> actual = bookService.findAllBooks();
            List<Book> expected = new ArrayList<>(BookStorageCreator.getCreatedBooks());
            expected.add(null);
            assertNotEquals(actual, expected);
        } catch (ServiceException e) {
            fail("Incorrect input");
        }
    }

    @DataProvider(name = "findBookByIdPositiveData")
    public Object[][] createFindBookByIdPositiveData() {
        long id1 = 1;
        List<Book> expected1 = new ArrayList<>();
        expected1.add(BookStorageCreator.getCreatedBooks().get(0));
        long id2 = 9;
        List<Book> expected2 = new ArrayList<>();
        expected2.add(BookStorageCreator.getCreatedBooks().get(9));
        long id3 = 7;
        List<Book> expected3 = new ArrayList<>();
        expected3.add(BookStorageCreator.getCreatedBooks().get(6));
        long id4 = 100_001;
        List<Book> expected4 = new ArrayList<>();
        long id5 = -7;
        List<Book> expected5 = new ArrayList<>();
        return new Object[][]{
                {id1, expected1},
                {id2, expected2},
                {id3, expected3},
                {id4, expected4},
                {id5, expected5}
        };
    }

    @Test(dataProvider = "findBookByIdPositiveData")
    public void findBookByIdPositiveTest(long id, List<Book> expected) {
        try {
            List<Book> actual = bookService.findBookById(id);
            assertEquals(actual, expected);
        } catch (ServiceException e) {
            fail("Incorrect input");
        }
    }

    @DataProvider(name = "findBookByIdNegativeData")
    public Object[][] createFindBookByIdNegativeData() {
        long id1 = 1;
        List<Book> expected1 = new ArrayList<>();
        expected1.add(BookStorageCreator.getCreatedBooks().get(1));
        long id2 = 9;
        List<Book> expected2 = new ArrayList<>();
        expected2.add(BookStorageCreator.getCreatedBooks().get(3));
        long id3 = -7;
        List<Book> expected3 = new ArrayList<>();
        expected3.add(BookStorageCreator.getCreatedBooks().get(2));
        return new Object[][]{
                {id1, expected1},
                {id2, expected2},
                {id3, expected3}
        };
    }

    @Test(dataProvider = "findBookByIdNegativeData")
    public void findBookByIdNegativeTest(long id, List<Book> expected) {
        try {
            List<Book> actual = bookService.findBookById(id);
            assertNotEquals(actual, expected);
        } catch (ServiceException e) {
            fail("Incorrect input");
        }
    }

    @DataProvider(name = "findBooksByNamePositiveData")
    public Object[][] createFindBooksByNamePositiveData() {
        String name1 = "Война и мир";
        List<Book> expected1 = new ArrayList<>();
        expected1.add(BookStorageCreator.getCreatedBooks().get(0));
        expected1.add(BookStorageCreator.getCreatedBooks().get(4));
        String name2 = "Метро 2033";
        List<Book> expected2 = new ArrayList<>();
        expected2.add(BookStorageCreator.getCreatedBooks().get(8));
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
        try {
            List<Book> actual = bookService.findBooksByName(name);
            assertEquals(actual, expected);
        } catch (ServiceException e) {
            fail("Incorrect input");
        }
    }

    @DataProvider(name = "findBooksByNameNegativeData")
    public Object[][] createFindBooksByNameNegativeData() {
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
        String name4 = "this is very very long line with 43 symbols";
        List<Book> expected4 = new ArrayList<>();
        expected4.add(BookStorageCreator.getCreatedBooks().get(0));
        return new Object[][]{
                {name1, expected1},
                {name2, expected2},
                {name3, expected3},
                {name4, expected4}
        };
    }

    @Test(dataProvider = "findBooksByNameNegativeData")
    public void findBooksByNameNegativeTest(String name, List<Book> expected) {
        try {
            List<Book> actual = bookService.findBooksByName(name);
            assertNotEquals(actual, expected);
        } catch (ServiceException e) {
            fail("Incorrect input");
        }
    }

    @DataProvider(name = "findBooksByPricePositiveData")
    public Object[][] createFindBooksByPricePositiveData() {
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
        double price4 = -100;
        List<Book> expected4 = new ArrayList<>();
        return new Object[][]{
                {price1, expected1},
                {price2, expected2},
                {price3, expected3},
                {price4, expected4}
        };
    }

    @Test(dataProvider = "findBooksByPricePositiveData")
    public void findBooksByPricePositiveTest(double price,
                                             List<Book> expected) {
        try {
            List<Book> actual = bookService.findBooksByPrice(price);
            assertEquals(actual, expected);
        } catch (ServiceException e) {
            fail("Incorrect input");
        }
    }

    @DataProvider(name = "findBooksByPriceNegativeData")
    public Object[][] createFindBooksByPriceNegativeData() {
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
        double price4 = -100;
        List<Book> expected4 = new ArrayList<>();
        expected4.add(BookStorageCreator.getCreatedBooks().get(0));
        return new Object[][]{
                {price1, expected1},
                {price2, expected2},
                {price3, expected3},
                {price4, expected4}
        };
    }

    @Test(dataProvider = "findBooksByPriceNegativeData")
    public void findBooksByPriceNegativeTest(double price,
                                             List<Book> expected) {
        try {
            List<Book> actual = bookService.findBooksByPrice(price);
            assertNotEquals(actual, expected);
        } catch (ServiceException e) {
            fail("Incorrect input");
        }
    }

    @DataProvider(name = "findBooksByPublishingHousePositiveData")
    public Object[][] createFindBooksByPublishingHousePositiveData() {
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
        try {
            List<Book> actual =
                    bookService.findBooksByPublishingHouse(publishingHouse);
            assertEquals(actual, expected);
        } catch (ServiceException e) {
            fail("Incorrect input");
        }
    }

    @DataProvider(name = "findBooksByPublishingHouseNegativeData")
    public Object[][] createFindBooksByPublishingHouseNegativeData() {
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
        String publishingHouse4 = "this is very very long line with 43 symbols";
        List<Book> expected4 = new ArrayList<>();
        expected4.add(BookStorageCreator.getCreatedBooks().get(0));
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
        try {
            List<Book> actual =
                    bookService.findBooksByPublishingHouse(publishingHouse);
            assertNotEquals(actual, expected);
        } catch (ServiceException e) {
            fail("Incorrect input");
        }
    }

    @DataProvider(name = "findBooksByAuthorPositiveData")
    public Object[][] createFindBooksByAuthorPositiveData() {
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
        try {
            List<Book> actual = bookService.findBooksByAuthor(author);
            assertEquals(actual, expected);
        } catch (ServiceException e) {
            fail("Incorrect input");
        }
    }

    @DataProvider(name = "findBooksByAuthorNegativeData")
    public Object[][] createFindBooksByAuthorNegativeData() {
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
        String author5 = "this is very very long line with 43 symbols";
        List<Book> expected5 = new ArrayList<>();
        expected5.add(BookStorageCreator.getCreatedBooks().get(0));
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
        try {
            List<Book> actual = bookService.findBooksByAuthor(author);
            assertNotEquals(actual, expected);
        } catch (ServiceException e) {
            fail("Incorrect input");
        }
    }

    @Test
    public void sortBooksByIdPositiveTest() {
        try {
            List<Book> actual = bookService.sortBooksById();
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
        } catch (ServiceException e) {
            fail("Incorrect input");
        }
    }

    @Test
    public void sortBooksByIdNegativeTest() {
        try {
            List<Book> actual = bookService.sortBooksById();
            List<Book> expected = new ArrayList<>(BookStorageCreator.getCreatedBooks());
            assertNotEquals(actual, expected);
        } catch (ServiceException e) {
            fail("Incorrect input");
        }
    }

    @Test
    public void sortBooksByNamePositiveTest() {
        try {
            List<Book> actual = bookService.sortBooksByName();
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
        } catch (ServiceException e) {
            fail("Incorrect input");
        }
    }

    @Test
    public void sortBooksByNameNegativeTest() {
        try {
            List<Book> actual = bookService.sortBooksByName();
            List<Book> expected = new ArrayList<>(BookStorageCreator.getCreatedBooks());
            assertNotEquals(actual, expected);
        } catch (ServiceException e) {
            fail("Incorrect input");
        }
    }

    @Test
    public void sortBooksByPricePositiveTest() {
        try {
            List<Book> actual = bookService.sortBooksByPrice();
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
        } catch (ServiceException e) {
            fail("Incorrect input");
        }
    }

    @Test
    public void sortBooksByPriceNegativeTest() {
        try {
            List<Book> actual = bookService.sortBooksByPrice();
            List<Book> expected = new ArrayList<>(BookStorageCreator.getCreatedBooks());
            assertNotEquals(actual, expected);
        } catch (ServiceException e) {
            fail("Incorrect input");
        }
    }

    @Test
    public void sortBooksByPublishingHousePositiveTest() {
        try {
            List<Book> actual = bookService.sortBooksByPublishingHouse();
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
        } catch (ServiceException e) {
            fail("Incorrect input");
        }
    }

    @Test
    public void sortBooksByPublishingHouseNegativeTest() {
        try {
            List<Book> actual = bookService.sortBooksByPublishingHouse();
            List<Book> expected = new ArrayList<>(BookStorageCreator.getCreatedBooks());
            assertNotEquals(actual, expected);
        } catch (ServiceException e) {
            fail("Incorrect input");
        }
    }

    @Test
    public void sortBooksByAuthorsPositiveTest() {
        try {
            List<Book> actual = bookService.sortBooksByAuthors();
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
        } catch (ServiceException e) {
            fail("Incorrect input");
        }
    }

    @Test
    public void sortBooksByAuthorsNegativeTest() {
        try {
            List<Book> actual = bookService.sortBooksByAuthors();
            List<Book> expected = new ArrayList<>(BookStorageCreator.getCreatedBooks());
            assertNotEquals(actual, expected);
        } catch (ServiceException e) {
            fail("Incorrect input");
        }
    }
}

