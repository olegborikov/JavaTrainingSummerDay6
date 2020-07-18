package test.borikov.day6.model.dao.impl;

import com.borikov.day6.exception.StorageException;
import com.borikov.day6.model.dao.BookListDao;
import com.borikov.day6.model.dao.impl.BookListDaoImpl;
import com.borikov.day6.model.entity.Book;
import com.borikov.day6.exception.DaoException;
import test.borikov.day6.creator.BookStorageCreator;
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.testng.Assert.*;

public class BookListDaoImplTest {
    private BookListDao bookDao;
    private BookStorageCreator bookStorageCreator;

    @BeforeClass
    public void setUpClass() throws StorageException {
        bookDao = new BookListDaoImpl();
        bookStorageCreator = BookStorageCreator.getInstance();
        bookStorageCreator.setUpBookStorage();
    }

    @AfterClass
    public void tearDownClass() {
        bookDao = null;
        bookStorageCreator = null;
    }

    @AfterMethod
    public void tearDownMethod() throws StorageException {
        bookStorageCreator.setUpBookStorage();
    }

    @DataProvider(name = "addPositiveData")
    public Object[][] createAddPositiveData() {
        Book newBook1 =
                new Book("Война и мир", 2020, "Минск", Arrays.asList("Лев"));
        List<Book> expected1 = new ArrayList<>(bookStorageCreator.getCreatedBooks());
        expected1.add(newBook1);
        Book newBook2 = new Book("Qw", 1999, "Москва", Arrays.asList("Qwe"));
        List<Book> expected2 = new ArrayList<>(bookStorageCreator.getCreatedBooks());
        expected2.add(newBook2);
        Book newBook3 = new Book("мир", 2000, "Минск", Arrays.asList("Oleg"));
        List<Book> expected3 = new ArrayList<>(bookStorageCreator.getCreatedBooks());
        expected3.add(newBook3);
        return new Object[][]{
                {newBook1, expected1},
                {newBook2, expected2},
                {newBook3, expected3}
        };
    }

    @Test(dataProvider = "addPositiveData")
    public void addPositiveTest(Book newBook, List<Book> expected) {
        try {
            bookDao.add(newBook);
            List<Book> actual = bookDao.findAll();
            assertEquals(actual, expected);
        } catch (DaoException e) {
            fail("Incorrect input");
        }
    }

    @DataProvider(name = "addNegativeData")
    public Object[][] createAddNegativeData() {
        Book newBook1 =
                new Book("Война и мир", 1000, "Минск", Arrays.asList("Лев"));
        List<Book> expected1 = new ArrayList<>(bookStorageCreator.getCreatedBooks());
        expected1.add(null);
        Book newBook2 = new Book("Qw", 1999, "Москва", new ArrayList<>());
        List<Book> expected2 = bookStorageCreator.getCreatedBooks();
        Book newBook3 = new Book("Nice?", 2000, "Minsk", new ArrayList<>());
        List<Book> expected3 = new ArrayList<>();
        return new Object[][]{
                {newBook1, expected1},
                {newBook2, expected2},
                {newBook3, expected3}
        };
    }

    @Test(dataProvider = "addNegativeData")
    public void addNegativeTest(Book newBook, List<Book> expected) {
        try {
            bookDao.add(newBook);
            List<Book> actual = bookDao.findAll();
            assertNotEquals(actual, expected);
        } catch (DaoException e) {
            fail("Incorrect input");
        }
    }

    @DataProvider(name = "addExceptionData")
    public Object[][] createAddExceptionData() {
        Book newBook1 = bookStorageCreator.getCreatedBooks().get(4);
        Book newBook2 = bookStorageCreator.getCreatedBooks().get(7);
        Book newBook3 = bookStorageCreator.getCreatedBooks().get(0);
        Book newBook4 =
                new Book("Война и мир", 1984, "Минск", Arrays.asList("Лев Толстой"));
        Book newBook5 =
                new Book("История Минска", 1000, "Минск", new ArrayList<>());
        return new Object[][]{
                {newBook1},
                {newBook2},
                {newBook3},
                {newBook4},
                {newBook5}
        };
    }

    @Test(dataProvider = "addExceptionData",
            expectedExceptions = DaoException.class)
    public void addExceptionTest(Book newBook)
            throws DaoException {
        bookDao.add(newBook);
    }

    @DataProvider(name = "removePositiveData")
    public Object[][] createRemovePositiveData() {
        Book removeBook1 =
                new Book("Война и мир", 1990, "Москва", Arrays.asList("Лев Толстой"));
        List<Book> expected1 =
                new ArrayList<>(bookStorageCreator.getCreatedBooks());
        expected1.remove(bookStorageCreator.getCreatedBooks().get(4));
        Book removeBook2 = bookStorageCreator.getCreatedBooks().get(7);
        List<Book> expected2 =
                new ArrayList<>(bookStorageCreator.getCreatedBooks());
        expected2.remove(removeBook2);
        Book removeBook3 = bookStorageCreator.getCreatedBooks().get(0);
        List<Book> expected3 =
                new ArrayList<>(bookStorageCreator.getCreatedBooks());
        expected3.remove(removeBook3);
        Book removeBook4 =
                new Book("История Минска", 1000, "Минск", new ArrayList<>());
        List<Book> expected4 = new ArrayList<>(bookStorageCreator.getCreatedBooks());
        expected4.remove(bookStorageCreator.getCreatedBooks().get(6));
        return new Object[][]{
                {removeBook1, expected1},
                {removeBook2, expected2},
                {removeBook3, expected3},
                {removeBook4, expected4},
        };
    }

    @Test(dataProvider = "removePositiveData")
    public void removePositiveTest(Book removeBook, List<Book> expected) {
        try {
            bookDao.remove(removeBook);
            List<Book> actual = bookDao.findAll();
            assertEquals(actual, expected);
        } catch (DaoException e) {
            fail("Incorrect input");
        }
    }

    @DataProvider(name = "removeNegativeData")
    public Object[][] createRemoveNegativeData() {
        Book removeBook1 = bookStorageCreator.getCreatedBooks().get(4);
        List<Book> expected1 =
                new ArrayList<>(bookStorageCreator.getCreatedBooks());
        expected1.remove(null);
        Book removeBook2 = bookStorageCreator.getCreatedBooks().get(7);
        List<Book> expected2 =
                new ArrayList<>(bookStorageCreator.getCreatedBooks());
        Book removeBook3 = bookStorageCreator.getCreatedBooks().get(8);
        List<Book> expected3 = new ArrayList<>();
        return new Object[][]{
                {removeBook1, expected1},
                {removeBook2, expected2},
                {removeBook3, expected3}
        };
    }

    @Test(dataProvider = "removeNegativeData")
    public void removeNegativeTest(Book removeBook, List<Book> expected) {
        try {
            bookDao.remove(removeBook);
            List<Book> actual = bookDao.findAll();
            assertNotEquals(actual, expected);
        } catch (DaoException e) {
            fail("Incorrect input");
        }
    }

    @DataProvider(name = "removeExceptionData")
    public Object[][] createRemoveExceptionData() {
        Book newBook1 =
                new Book("Война и мир", 100, "Минск", Arrays.asList("Лев Толстой"));
        Book newBook2 =
                new Book("Qw", 10, "Москва", Arrays.asList("Qwe"));
        Book newBook3 =
                new Book("Война и мир", 1984, "Минск", Arrays.asList("Лев"));
        Book newBook4 =
                new Book("Война и мир", 1984, "Минск1", Arrays.asList("Лев Толстой"));
        Book newBook5 =
                new Book("История Минска", 1001, "Минск", new ArrayList<>());
        return new Object[][]{
                {newBook1},
                {newBook2},
                {newBook3},
                {newBook4},
                {newBook5}
        };
    }

    @Test(dataProvider = "removeExceptionData",
            expectedExceptions = DaoException.class)
    public void removeExceptionTest(Book removeBook)
            throws DaoException {
        bookDao.remove(removeBook);
    }

    @Test
    public void findAllPositiveTest() {
        List<Book> actual = bookDao.findAll();
        List<Book> expected = bookStorageCreator.getCreatedBooks();
        assertEquals(actual, expected);
    }

    @Test
    public void findAllNegativeTest() {
        List<Book> actual = bookDao.findAll();
        List<Book> expected =
                new ArrayList<>(bookStorageCreator.getCreatedBooks());
        expected.add(null);
        assertNotEquals(actual, expected);
    }

    @DataProvider(name = "findByIdPositiveData")
    public Object[][] createFindByIdPositiveData() {
        long id1 = 1;
        Optional<Book> expected1 =
                Optional.of(bookStorageCreator.getCreatedBooks().get(0));
        long id2 = 9;
        Optional<Book> expected2 =
                Optional.of(bookStorageCreator.getCreatedBooks().get(9));
        long id3 = 11;
        Optional<Book> expected3 = Optional.empty();
        return new Object[][]{
                {id1, expected1},
                {id2, expected2},
                {id3, expected3}
        };
    }

    @Test(dataProvider = "findByIdPositiveData")
    public void findByIdPositiveTest(long id, Optional<Book> expected) {
        Optional<Book> actual = bookDao.findById(id);
        assertEquals(actual, expected);
    }

    @DataProvider(name = "findByIdNegativeData")
    public Object[][] createFindByIdNegativeData() {
        long id1 = 1;
        Optional<Book> expected1 =
                Optional.of(bookStorageCreator.getCreatedBooks().get(3));
        long id2 = 9;
        Optional<Book> expected2 = Optional.empty();
        long id3 = 11;
        return new Object[][]{
                {id1, expected1},
                {id2, expected2},
                {id3, null}
        };
    }

    @Test(dataProvider = "findByIdNegativeData")
    public void findByIdNegativeTest(long id, Optional<Book> expected) {
        Optional<Book> actual = bookDao.findById(id);
        assertNotEquals(actual, expected);
    }

    @DataProvider(name = "findByNamePositiveData")
    public Object[][] createFindByNamePositiveData() {
        String name1 = "Война и мир";
        List<Book> expected1 = new ArrayList<>();
        expected1.add(bookStorageCreator.getCreatedBooks().get(0));
        expected1.add(bookStorageCreator.getCreatedBooks().get(4));
        String name2 = "Метро 2033";
        List<Book> expected2 = new ArrayList<>();
        expected2.add(bookStorageCreator.getCreatedBooks().get(8));
        String name3 = "я";
        List<Book> expected3 = new ArrayList<>();
        return new Object[][]{
                {name1, expected1},
                {name2, expected2},
                {name3, expected3}
        };
    }

    @Test(dataProvider = "findByNamePositiveData")
    public void findByNamePositiveTest(String name, List<Book> expected) {
        List<Book> actual = bookDao.findByName(name);
        assertEquals(actual, expected);
    }

    @DataProvider(name = "findByNameNegativeData")
    public Object[][] createFindByNameNegativeData() {
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
        return new Object[][]{
                {name1, expected1},
                {name2, expected2},
                {name3, expected3}
        };
    }

    @Test(dataProvider = "findByNameNegativeData")
    public void findByNameNegativeTest(String name, List<Book> expected) {
        List<Book> actual = bookDao.findByName(name);
        assertNotEquals(actual, expected);
    }

    @DataProvider(name = "findByPublishingYearPositiveData")
    public Object[][] createFindByPublishingYearPositiveData() {
        int publishingYear1 = 1000;
        List<Book> expected1 = new ArrayList<>();
        expected1.add(bookStorageCreator.getCreatedBooks().get(2));
        expected1.add(bookStorageCreator.getCreatedBooks().get(6));
        int publishingYear2 = 1984;
        List<Book> expected2 = new ArrayList<>();
        expected2.add(bookStorageCreator.getCreatedBooks().get(0));
        int publishingYear3 = 2015;
        List<Book> expected3 = new ArrayList<>();
        expected3.add(bookStorageCreator.getCreatedBooks().get(8));
        int publishingYear4 = 2016;
        List<Book> expected4 = new ArrayList<>();
        return new Object[][]{
                {publishingYear1, expected1},
                {publishingYear2, expected2},
                {publishingYear3, expected3},
                {publishingYear4, expected4}
        };
    }

    @Test(dataProvider = "findByPublishingYearPositiveData")
    public void findByPublishingYearPositiveTest(int publishingYear,
                                                 List<Book> expected) {
        List<Book> actual = bookDao.findByPublishingYear(publishingYear);
        assertEquals(actual, expected);
    }

    @DataProvider(name = "findByPublishingYearNegativeData")
    public Object[][] createFindByPublishingYearNegativeData() {
        int publishingYear1 = 1000;
        List<Book> expected1 = new ArrayList<>();
        expected1.add(bookStorageCreator.getCreatedBooks().get(2));
        int publishingYear2 = 1984;
        List<Book> expected2 = new ArrayList<>();
        int publishingYear3 = 2015;
        List<Book> expected3 = new ArrayList<>();
        expected3.add(bookStorageCreator.getCreatedBooks().get(1));
        int publishingYear4 = 2016;
        List<Book> expected4 = new ArrayList<>();
        expected4.add(bookStorageCreator.getCreatedBooks().get(8));
        return new Object[][]{
                {publishingYear1, expected1},
                {publishingYear2, expected2},
                {publishingYear3, expected3},
                {publishingYear4, expected4}
        };
    }

    @Test(dataProvider = "findByPublishingYearNegativeData")
    public void findByPublishingYearNegativeTest(int publishingYear,
                                                 List<Book> expected) {
        List<Book> actual = bookDao.findByPublishingYear(publishingYear);
        assertNotEquals(actual, expected);
    }

    @DataProvider(name = "findByPublishingHousePositiveData")
    public Object[][] createFindByPublishingHousePositiveData() {
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
        return new Object[][]{
                {publishingHouse1, expected1},
                {publishingHouse2, expected2},
                {publishingHouse3, expected3}
        };
    }

    @Test(dataProvider = "findByPublishingHousePositiveData")
    public void findByPublishingHousePositiveTest(String publishingHouse,
                                                  List<Book> expected) {
        List<Book> actual = bookDao.findByPublishingHouse(publishingHouse);
        assertEquals(actual, expected);
    }

    @DataProvider(name = "findByPublishingHouseNegativeData")
    public Object[][] createFindByPublishingHouseNegativeData() {
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
        return new Object[][]{
                {publishingHouse1, expected1},
                {publishingHouse2, expected2},
                {publishingHouse3, expected3}
        };
    }

    @Test(dataProvider = "findByPublishingHouseNegativeData")
    public void findByPublishingHouseNegativeTest(String publishingHouse,
                                                  List<Book> expected) {
        List<Book> actual = bookDao.findByPublishingHouse(publishingHouse);
        assertNotEquals(actual, expected);
    }

    @DataProvider(name = "findByAuthorPositiveData")
    public Object[][] createFindByAuthorPositiveData() {
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
        return new Object[][]{
                {author1, expected1},
                {author2, expected2},
                {author3, expected3},
                {author4, expected4}
        };
    }

    @Test(dataProvider = "findByAuthorPositiveData")
    public void findByAuthorPositiveTest(String author, List<Book> expected) {
        List<Book> actual = bookDao.findByAuthor(author);
        assertEquals(actual, expected);
    }

    @DataProvider(name = "findByAuthorNegativeData")
    public Object[][] createFindByAuthorNegativeData() {
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
        return new Object[][]{
                {author1, expected1},
                {author2, expected2},
                {author3, expected3},
                {author4, expected4}
        };
    }

    @Test(dataProvider = "findByAuthorNegativeData")
    public void findByAuthorNegativeTest(String author, List<Book> expected) {
        List<Book> actual = bookDao.findByAuthor(author);
        assertNotEquals(actual, expected);
    }

    @Test
    public void sortByIdPositiveTest() {
        List<Book> actual = bookDao.sortById();
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
    public void sortByIdNegativeTest() {
        List<Book> actual = bookDao.sortById();
        List<Book> expected =
                new ArrayList<>(bookStorageCreator.getCreatedBooks());
        assertNotEquals(actual, expected);
    }

    @Test
    public void sortByNamePositiveTest() {
        List<Book> actual = bookDao.sortByName();
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
    public void sortByNameNegativeTest() {
        List<Book> actual = bookDao.sortByName();
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
    public void sortByPublishingYearPositiveTest() {
        List<Book> actual = bookDao.sortByPublishingYear();
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
    public void sortByPublishingYearNegativeTest() {
        List<Book> actual = bookDao.sortByPublishingYear();
        List<Book> expected =
                new ArrayList<>(bookStorageCreator.getCreatedBooks());
        assertNotEquals(actual, expected);
    }

    @Test
    public void sortByPublishingHousePositiveTest() {
        List<Book> actual = bookDao.sortByPublishingHouse();
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
    public void sortByPublishingHouseNegativeTest() {
        List<Book> actual = bookDao.sortByPublishingHouse();
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
    public void sortByAuthorsPositiveTest() {
        List<Book> actual = bookDao.sortByAuthors();
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
    public void sortByAuthorsNegativeTest() {
        List<Book> actual = bookDao.sortByAuthors();
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

