/*
package test.borikov.day6.model.entity;

import com.borikov.day6.exception.StorageException;
import com.borikov.day6.model.entity.Book;
import com.borikov.day6.model.entity.BookStorage;
import org.testng.annotations.*;
import test.borikov.day6.creator.BookStorageCreator;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.*;

public class BookStorageTest {
    private BookStorage bookStorage;

    @BeforeClass
    public void setUpClass() {
        bookStorage = BookStorage.getInstance();
        BookStorageCreator.setUpBookStorage();
    }

    @AfterClass
    public void tearDownClass() {
        bookStorage = null;
    }

    @AfterMethod
    public void tearDownMethod() {
        BookStorageCreator.setUpBookStorage();
    }

    @Test
    public void getPositiveTest() {
        try {
            List<Book> actual = bookStorage.get();
            List<Book> expected = BookStorageCreator.getCreatedBooks();
            assertEquals(actual, expected);
        } catch (StorageException e) {
            fail("Incorrect input");
        }
    }

    @Test
    public void getNegativeTest() {
        try {
            List<Book> actual = bookStorage.get();
            List<Book> expected = null;
            assertNotEquals(actual, expected);
        } catch (StorageException e) {
            fail("Incorrect input");
        }
    }

    @Test(expectedExceptions = StorageException.class)
    public void getExceptionTest() throws StorageException {
        bookStorage.set(null);
        bookStorage.get();
    }

    @DataProvider(name = "addPositiveData")
    public Object[][] createAddPositiveData() {
        List<String> authors1 = new ArrayList<>();
        authors1.add("Лев");
        Book newBook1 = new Book("Война и мир", 100, "Минск", authors1);
        List<Book> expected1 = new ArrayList<>(BookStorageCreator.getCreatedBooks());
        expected1.add(newBook1);

        List<String> authors2 = new ArrayList<>();
        authors1.add("Qwe");
        Book newBook2 = new Book("Qw", 10, "Москва", authors2);
        List<Book> expected2 = new ArrayList<>(BookStorageCreator.getCreatedBooks());
        expected2.add(newBook2);

        List<String> authors3 = new ArrayList<>();
        authors3.add("Oleg");
        Book newBook3 = new Book("мир", 23, "Минск", authors3);
        List<Book> expected3 = new ArrayList<>(BookStorageCreator.getCreatedBooks());
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
        List<String> authors1 = new ArrayList<>();
        authors1.add("Лев");
        Book newBook1 = new Book("Война и мир", 100, "Минск", authors1);
        List<Book> expected1 = new ArrayList<>(BookStorageCreator.getCreatedBooks());
        expected1.add(null);
        List<String> authors2 = new ArrayList<>();
        authors1.add("Qwe");
        Book newBook2 = new Book("Qw", 10, "Москва", authors2);
        List<Book> expected2 = new ArrayList<>(BookStorageCreator.getCreatedBooks());
        return new Object[][]{
                {newBook1, expected1},
                {newBook2, expected2}
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
        Book newBook1 = BookStorageCreator.getCreatedBooks().get(4);
        Book newBook2 = BookStorageCreator.getCreatedBooks().get(7);
        Book newBook3 = BookStorageCreator.getCreatedBooks().get(0);
        return new Object[][]{
                {newBook1},
                {newBook2},
                {newBook3}
        };
    }

    @Test(dataProvider = "addExceptionData",
            expectedExceptions = StorageException.class,
            expectedExceptionsMessageRegExp = "There is no book storage")
    public void addExceptionTest1(Book newBook) throws StorageException {
        bookStorage.set(null);
        bookStorage.add(newBook);
    }

    @Test(dataProvider = "addExceptionData",
            expectedExceptions = StorageException.class,
            expectedExceptionsMessageRegExp = "Book storage is overflowed")
    public void addExceptionTest2(Book newBook) throws StorageException {
        List<Book> books = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Book book = new Book("Qw", 10, "Москва", new ArrayList<>());
            books.add(book);
        }
        bookStorage.set(books);
        bookStorage.add(newBook);
    }

    @Test(dataProvider = "addExceptionData",
            expectedExceptions = StorageException.class,
            expectedExceptionsMessageRegExp = "Book already in storage")
    public void addExceptionTest3(Book newBook) throws StorageException {
        bookStorage.add(newBook);
    }

    @DataProvider(name = "removePositiveData")
    public Object[][] createRemovePositiveData() {
        Book removeBook1 = BookStorageCreator.getCreatedBooks().get(4);
        List<Book> expected1 = new ArrayList<>(BookStorageCreator.getCreatedBooks());
        expected1.remove(removeBook1);
        Book removeBook2 = BookStorageCreator.getCreatedBooks().get(7);
        List<Book> expected2 = new ArrayList<>(BookStorageCreator.getCreatedBooks());
        expected2.remove(removeBook2);
        Book removeBook3 = BookStorageCreator.getCreatedBooks().get(0);
        List<Book> expected3 = new ArrayList<>(BookStorageCreator.getCreatedBooks());
        expected3.remove(removeBook3);
        return new Object[][]{
                {removeBook1, expected1},
                {removeBook2, expected2},
                {removeBook3, expected3},
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
        Book removeBook1 = BookStorageCreator.getCreatedBooks().get(4);
        List<Book> expected1 = new ArrayList<>(BookStorageCreator.getCreatedBooks());
        expected1.remove(null);
        Book removeBook2 = BookStorageCreator.getCreatedBooks().get(7);
        List<Book> expected2 = new ArrayList<>(BookStorageCreator.getCreatedBooks());
        Book removeBook3 = BookStorageCreator.getCreatedBooks().get(8);
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
        List<String> authors1 = new ArrayList<>();
        authors1.add("Лев");
        Book newBook1 = new Book("Война и мир", 100, "Минск", authors1);
        List<String> authors2 = new ArrayList<>();
        authors1.add("Qwe");
        Book newBook2 = new Book("Qw", 10, "Москва", authors2);
        List<String> authors3 = new ArrayList<>();
        authors3.add("Oleg");
        Book newBook3 = new Book("мир", 23, "Минск", authors3);
        return new Object[][]{
                {newBook1},
                {newBook2},
                {newBook3}
        };
    }

    @Test(dataProvider = "removeExceptionData",
            expectedExceptions = StorageException.class,
            expectedExceptionsMessageRegExp = "There is no book storage")
    public void removeExceptionTest1(Book removeBook) throws StorageException {
        bookStorage.set(null);
        bookStorage.remove(removeBook);
    }

    @Test(dataProvider = "removeExceptionData",
            expectedExceptions = StorageException.class,
            expectedExceptionsMessageRegExp = "No such book in storage")
    public void removeExceptionTest2(Book removeBook) throws StorageException {
        bookStorage.remove(removeBook);
    }
}
*/
