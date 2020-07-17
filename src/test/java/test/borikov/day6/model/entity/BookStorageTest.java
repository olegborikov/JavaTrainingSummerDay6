package test.borikov.day6.model.entity;

import com.borikov.day6.exception.StorageException;
import com.borikov.day6.model.entity.Book;
import com.borikov.day6.model.entity.BookStorage;
import org.testng.annotations.*;
import test.borikov.day6.creator.BookStorageCreator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.*;

public class BookStorageTest {
    private BookStorage bookStorage;
    private BookStorageCreator bookStorageCreator;

    @BeforeClass
    public void setUpClass() throws StorageException {
        bookStorage = BookStorage.getInstance();
        bookStorageCreator = BookStorageCreator.getInstance();
        bookStorageCreator.setUpBookStorage();
    }

    @AfterClass
    public void tearDownClass() {
        bookStorage = null;
        bookStorageCreator = null;
    }

    @AfterMethod
    public void tearDownMethod() throws StorageException {
        bookStorageCreator.setUpBookStorage();
    }

    @DataProvider(name = "addPositiveData")
    public Object[][] createAddPositiveData() {
        Book newBook1 =
                new Book("Война и мир", 100, "Минск", Arrays.asList("Лев"));
        List<Book> expected1 = new ArrayList<>(bookStorageCreator.getCreatedBooks());
        expected1.add(newBook1);
        Book newBook2 = new Book("Qw", 10, "Москва", Arrays.asList("Qwe"));
        List<Book> expected2 = new ArrayList<>(bookStorageCreator.getCreatedBooks());
        expected2.add(newBook2);
        Book newBook3 = new Book("мир", 23, "Минск", Arrays.asList("Oleg"));
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
            bookStorage.add(newBook);
            List<Book> actual = bookStorage.get();
            assertEquals(actual, expected);
        } catch (StorageException e) {
            fail("Incorrect input");
        }
    }

    @DataProvider(name = "addNegativeData")
    public Object[][] createAddNegativeData() {
        Book newBook1 =
                new Book("Война и мир", 100, "Минск", Arrays.asList("Лев"));
        List<Book> expected1 = new ArrayList<>(bookStorageCreator.getCreatedBooks());
        expected1.add(null);
        Book newBook2 = new Book("Qw", 10, "Москва", new ArrayList<>());
        List<Book> expected2 = bookStorageCreator.getCreatedBooks();
        Book newBook3 = new Book("Nice?", 23, "Minsk", new ArrayList<>());
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
            bookStorage.add(newBook);
            List<Book> actual = bookStorage.get();
            assertNotEquals(actual, expected);
        } catch (StorageException e) {
            fail("Incorrect input");
        }
    }

    @DataProvider(name = "addExceptionData")
    public Object[][] createAddExceptionData() {
        Book newBook1 = bookStorageCreator.getCreatedBooks().get(4);
        Book newBook2 = bookStorageCreator.getCreatedBooks().get(7);
        Book newBook3 = bookStorageCreator.getCreatedBooks().get(0);
        Book newBook4 =
                new Book("Война и мир", 100, "Минск", Arrays.asList("Лев Толстой"));
        Book newBook5 =
                new Book("История Минска", 10, "Минск", new ArrayList<>());
        return new Object[][]{
                {newBook1},
                {newBook2},
                {newBook3},
                {newBook4},
                {newBook5}
        };
    }

    @Test(dataProvider = "addExceptionData",
            expectedExceptions = StorageException.class,
            expectedExceptionsMessageRegExp = "Book storage is overflowed")
    public void addExceptionTest1(Book newBook) throws StorageException {
        for (int i = 1; i <= 100; i++) {
            Book book = new Book("Qw", i, "Москва", new ArrayList<>());
            bookStorage.add(book);
        }
        bookStorage.add(newBook);
    }

    @Test(dataProvider = "addExceptionData",
            expectedExceptions = StorageException.class,
            expectedExceptionsMessageRegExp = "Book already in storage")
    public void addExceptionTest2(Book newBook) throws StorageException {
        bookStorage.add(newBook);
    }

    @DataProvider(name = "removePositiveData")
    public Object[][] createRemovePositiveData() {
        Book removeBook1 =
                new Book("Война и мир", 100, "Москва", Arrays.asList("Лев Толстой"));
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
                new Book("История Минска", 10, "Минск", new ArrayList<>());
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
            bookStorage.remove(removeBook);
            List<Book> actual = bookStorage.get();
            assertEquals(actual, expected);
        } catch (StorageException e) {
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
            bookStorage.remove(removeBook);
            List<Book> actual = bookStorage.get();
            assertNotEquals(actual, expected);
        } catch (StorageException e) {
            fail("Incorrect input");
        }
    }

    @DataProvider(name = "removeExceptionData")
    public Object[][] createRemoveExceptionData() {
        Book newBook1 =
                new Book("Война и мир", 100, "Минск", Arrays.asList("Лев"));
        Book newBook2 =
                new Book("Qw", 10, "Москва", Arrays.asList("Qwe"));
        Book newBook3 =
                new Book("мир", 23, "Минск", Arrays.asList("Oleg"));
        Book newBook4 =
                new Book("Война и мир", 100, "Минск1", Arrays.asList("Лев Толстой"));
        Book newBook5 =
                new Book("История Минска", 101, "Минск", new ArrayList<>());
        return new Object[][]{
                {newBook1},
                {newBook2},
                {newBook3},
                {newBook4},
                {newBook5}
        };
    }

    @Test(dataProvider = "removeExceptionData",
            expectedExceptions = StorageException.class,
            expectedExceptionsMessageRegExp = "No such book in storage")
    public void removeExceptionTest(Book removeBook) throws StorageException {
        bookStorage.remove(removeBook);
    }
}
