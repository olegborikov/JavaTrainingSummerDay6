package test.borikov.day6.dao.impl;

import com.borikov.day6.model.dao.impl.BookListDaoImpl;
import com.borikov.day6.model.entity.Book;
import com.borikov.day6.model.entity.BookStorage;
import com.borikov.day6.exception.DaoException;
import com.borikov.day6.util.BookStorageCreator;
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.testng.Assert.*;

public class BookListDaoImplTest {
    private BookStorage bookStorage;
    private BookListDaoImpl bookDao;

    @BeforeClass
    public void setUpClass() {
        bookStorage = BookStorage.getInstance();
        bookDao = new BookListDaoImpl();
        BookStorageCreator.setUpBookStorage();
    }

    @AfterClass
    public void tearDownClass() {
        bookDao = null;
        bookStorage.reset();
        bookStorage = null;
    }

    @AfterMethod
    public void tearDownMethod() {
        bookStorage.reset();
        BookStorageCreator.setUpBookStorage();
    }

    @DataProvider(name = "addBookPositiveData")
    public Object[][] createAddBookPositiveData() {
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

    @Test(dataProvider = "addBookPositiveData")
    public void addBookPositiveTest(Book newBook, List<Book> expected) {
        try {
            bookDao.add(newBook);
            List<Book> actual = bookStorage.get();
            assertEquals(actual, expected);
        } catch (DaoException e) {
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

    @Test(dataProvider = "addBookNegativeData")
    public void addBookNegativeTest(Book newBook, List<Book> expected) {
        try {
            bookDao.add(newBook);
            List<Book> actual = bookStorage.get();
            assertNotEquals(actual, expected);
        } catch (DaoException e) {
            fail("Incorrect input");
        }
    }

    @DataProvider(name = "addBookExceptionData")
    public Object[][] createAddBookExceptionData() {
        Book newBook1 = BookStorageCreator.getCreatedBooks().get(1);
        Book newBook2 = BookStorageCreator.getCreatedBooks().get(2);
        Book newBook3 = BookStorageCreator.getCreatedBooks().get(9);
        return new Object[][]{
                {newBook1},
                {newBook2},
                {newBook3}
        };
    }

    @Test(dataProvider = "addBookExceptionData",
            expectedExceptions = DaoException.class)
    public void addBookExceptionTest(Book newBook)
            throws DaoException {
        bookDao.add(newBook);
    }

    @DataProvider(name = "removeBookPositiveData")
    public Object[][] createRemoveBookPositiveData() {
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

    @Test(dataProvider = "removeBookPositiveData")
    public void removeBookPositiveTest(Book removeBook, List<Book> expected) {
        try {
            bookDao.remove(removeBook);
            List<Book> actual = bookStorage.get();
            assertEquals(actual, expected);
        } catch (DaoException e) {
            fail("Incorrect input");
        }
    }

    @DataProvider(name = "removeBookNegativeData")
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

    @Test(dataProvider = "removeBookNegativeData")
    public void removeBookNegativeTest(Book removeBook, List<Book> expected) {
        try {
            bookDao.remove(removeBook);
            List<Book> actual = bookStorage.get();
            assertNotEquals(actual, expected);
        } catch (DaoException e) {
            fail("Incorrect input");
        }
    }

    @DataProvider(name = "removeBookExceptionData")
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

    @Test(dataProvider = "removeBookExceptionData",
            expectedExceptions = DaoException.class)
    public void removeBookExceptionTest(Book removeBook)
            throws DaoException {
        bookDao.remove(removeBook);
    }

    @Test
    public void findAllBooksPositiveTest() {
        List<Book> actual = bookDao.findAll();
        List<Book> expected = new ArrayList<>(BookStorageCreator.getCreatedBooks());
        assertEquals(actual, expected);
    }

    @Test
    public void findAllBooksNegativeTest() {
        List<Book> actual = bookDao.findAll();
        List<Book> expected = new ArrayList<>(BookStorageCreator.getCreatedBooks());
        expected.add(null);
        assertNotEquals(actual, expected);
    }

    @DataProvider(name = "findBookByIdPositiveData")
    public Object[][] createFindBookByIdPositiveData() {
        long id1 = 1;
        Optional<Book> expected1 = Optional.of(BookStorageCreator.getCreatedBooks().get(0));
        long id2 = 9;
        Optional<Book> expected2 = Optional.of(BookStorageCreator.getCreatedBooks().get(9));
        long id3 = 11;
        Optional<Book> expected3 = Optional.empty();
        return new Object[][]{
                {id1, expected1},
                {id2, expected2},
                {id3, expected3}
        };
    }

    @Test(dataProvider = "findBookByIdPositiveData")
    public void findBookByIdPositiveTest(long id, Optional<Book> expected) {
        Optional<Book> actual = bookDao.findById(id);
        assertEquals(actual, expected);
    }

    @DataProvider(name = "findBookByIdNegativeData")
    public Object[][] createFindBookByIdNegativeData() {
        long id1 = 1;
        Optional<Book> expected1 = Optional.of(BookStorageCreator.getCreatedBooks().get(3));
        long id2 = 9;
        Optional<Book> expected2 = Optional.empty();
        long id3 = 11;
        return new Object[][]{
                {id1, expected1},
                {id2, expected2},
                {id3, null}
        };
    }

    @Test(dataProvider = "findBookByIdNegativeData")
    public void findBookByIdNegativeTest(long id, Optional<Book> expected) {
        Optional<Book> actual = bookDao.findById(id);
        assertNotEquals(actual, expected);
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
        return new Object[][]{
                {name1, expected1},
                {name2, expected2},
                {name3, expected3}
        };
    }

    @Test(dataProvider = "findBooksByNamePositiveData")
    public void findBooksByNamePositiveTest(String name, List<Book> expected) {
        List<Book> actual = bookDao.findByName(name);
        assertEquals(actual, expected);
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
        return new Object[][]{
                {name1, expected1},
                {name2, expected2},
                {name3, expected3}
        };
    }

    @Test(dataProvider = "findBooksByNameNegativeData")
    public void findBooksByNameNegativeTest(String name, List<Book> expected) {
        List<Book> actual = bookDao.findByName(name);
        assertNotEquals(actual, expected);
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
        return new Object[][]{
                {price1, expected1},
                {price2, expected2},
                {price3, expected3}
        };
    }

    @Test(dataProvider = "findBooksByPricePositiveData")
    public void findBooksByPricePositiveTest(double price, List<Book> expected) {
        List<Book> actual = bookDao.findByPrice(price);
        assertEquals(actual, expected);
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
        return new Object[][]{
                {price1, expected1},
                {price2, expected2},
                {price3, expected3}
        };
    }

    @Test(dataProvider = "findBooksByPriceNegativeData")
    public void findBooksByPriceNegativeTest(double price, List<Book> expected) {
        List<Book> actual = bookDao.findByPrice(price);
        assertNotEquals(actual, expected);
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
        return new Object[][]{
                {publishingHouse1, expected1},
                {publishingHouse2, expected2},
                {publishingHouse3, expected3}
        };
    }

    @Test(dataProvider = "findBooksByPublishingHousePositiveData")
    public void findBooksByPublishingHousePositiveTest(String publishingHouse,
                                                       List<Book> expected) {
        List<Book> actual = bookDao.findByPublishingHouse(publishingHouse);
        assertEquals(actual, expected);
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
        return new Object[][]{
                {publishingHouse1, expected1},
                {publishingHouse2, expected2},
                {publishingHouse3, expected3}
        };
    }

    @Test(dataProvider = "findBooksByPublishingHouseNegativeData")
    public void findBooksByPublishingHouseNegativeTest(String publishingHouse,
                                                       List<Book> expected) {
        List<Book> actual = bookDao.findByPublishingHouse(publishingHouse);
        assertNotEquals(actual, expected);
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
        return new Object[][]{
                {author1, expected1},
                {author2, expected2},
                {author3, expected3},
                {author4, expected4}
        };
    }

    @Test(dataProvider = "findBooksByAuthorPositiveData")
    public void findBooksByAuthorPositiveTest(String author, List<Book> expected) {
        List<Book> actual = bookDao.findByAuthor(author);
        assertEquals(actual, expected);
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
        return new Object[][]{
                {author1, expected1},
                {author2, expected2},
                {author3, expected3},
                {author4, expected4}
        };
    }

    @Test(dataProvider = "findBooksByAuthorNegativeData")
    public void findBooksByAuthorNegativeTest(String author, List<Book> expected) {
        List<Book> actual = bookDao.findByAuthor(author);
        assertNotEquals(actual, expected);
    }

    @Test
    public void sortBooksByIdPositiveTest() {
        List<Book> actual = bookDao.sortById();
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
    public void sortBooksByIdNegativeTest() {
        List<Book> actual = bookDao.sortById();
        List<Book> expected = new ArrayList<>(BookStorageCreator.getCreatedBooks());
        assertNotEquals(actual, expected);
    }

    @Test
    public void sortBooksByNamePositiveTest() {
        List<Book> actual = bookDao.sortByName();
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
    public void sortBooksByNameNegativeTest() {
        List<Book> actual = bookDao.sortByName();
        List<Book> expected = new ArrayList<>(BookStorageCreator.getCreatedBooks());
        assertNotEquals(actual, expected);
    }

    @Test
    public void sortBooksByPricePositiveTest() {
        List<Book> actual = bookDao.sortByPrice();
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
    public void sortBooksByPriceNegativeTest() {
        List<Book> actual = bookDao.sortByPrice();
        List<Book> expected = new ArrayList<>(BookStorageCreator.getCreatedBooks());
        assertNotEquals(actual, expected);
    }

    @Test
    public void sortBooksByPublishingHousePositiveTest() {
        List<Book> actual = bookDao.sortByPublishingHouse();
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
    public void sortBooksByPublishingHouseNegativeTest() {
        List<Book> actual = bookDao.sortByPublishingHouse();
        List<Book> expected = new ArrayList<>(BookStorageCreator.getCreatedBooks());
        assertNotEquals(actual, expected);
    }

    @Test
    public void sortBooksByAuthorsPositiveTest() {
        List<Book> actual = bookDao.sortByAuthors();
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
    public void sortBooksByAuthorsNegativeTest() {
        List<Book> actual = bookDao.sortByAuthors();
        List<Book> expected = new ArrayList<>(BookStorageCreator.getCreatedBooks());
        assertNotEquals(actual, expected);
    }
}
