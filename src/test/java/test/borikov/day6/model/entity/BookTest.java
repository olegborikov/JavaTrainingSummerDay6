package test.borikov.day6.model.entity;

import com.borikov.day6.model.entity.Book;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import test.borikov.day6.creator.BookStorageCreator;

import java.util.ArrayList;
import java.util.Arrays;

import static org.testng.Assert.*;

public class BookTest {
    private BookStorageCreator bookStorageCreator;

    @BeforeClass
    public void setUp() {
        bookStorageCreator = BookStorageCreator.getInstance();
    }

    @AfterClass
    public void tearDown() {
        bookStorageCreator = null;
    }

    @DataProvider(name = "equalsToBookPositiveData")
    public Object[][] createEqualsToBookPositiveData() {
        Book firstBook1 = bookStorageCreator.getCreatedBooks().get(1);
        Book secondBook1 = bookStorageCreator.getCreatedBooks().get(1);
        Book firstBook2 = bookStorageCreator.getCreatedBooks().get(5);
        Book firstBook3 = bookStorageCreator.getCreatedBooks().get(4);
        Book secondBook3 = new Book("Война и мир", 100,
                "Москва", new ArrayList<>(Arrays.asList("Лев Толстой")));
        Book firstBook4 = new Book("Qwerty", 1,
                "Qwerty", new ArrayList<>(Arrays.asList("Qwerty")));
        Book secondBook4 = new Book("Qwerty", 1,
                "Qwerty", new ArrayList<>(Arrays.asList("Qwerty")));
        return new Object[][]{
                {firstBook1, secondBook1},
                {firstBook2, firstBook2},
                {firstBook3, secondBook3},
                {firstBook4, secondBook4}
        };
    }

    @Test(dataProvider = "equalsToBookPositiveData")
    public void equalsToBookPositiveTest(Book firstBook, Book secondBook) {
        boolean actual = firstBook.equalsToBook(secondBook);
        assertTrue(actual);
    }

    @DataProvider(name = "equalsToBookNegativeData")
    public Object[][] createEqualsToBookNegativeData() {
        Book firstBook1 = bookStorageCreator.getCreatedBooks().get(1);
        Book firstBook2 = bookStorageCreator.getCreatedBooks().get(5);
        Book secondBook2 = bookStorageCreator.getCreatedBooks().get(6);
        Book firstBook3 = bookStorageCreator.getCreatedBooks().get(4);
        Book secondBook3 = new Book("Война и мир", 101,
                "Москва", new ArrayList<>(Arrays.asList("Лев Толстой")));
        Book firstBook4 = new Book("Qwerty1", 1,
                "Qwerty", new ArrayList<>(Arrays.asList("Qwerty")));
        Book secondBook4 = new Book("Qwerty", 1,
                "Qwerty", new ArrayList<>(Arrays.asList("Qwerty")));
        Book firstBook5 = new Book("Qwerty", 1,
                "Qwerty", new ArrayList<>(Arrays.asList("Qwerty1")));
        Book secondBook5 = new Book("Qwerty", 1,
                "Qwerty", new ArrayList<>(Arrays.asList("Qwerty")));
        Book firstBook6 = new Book("Qwerty", 1,
                "Qwerty", new ArrayList<>(Arrays.asList("Qwerty")));
        Book secondBook6 = new Book("Qwerty", 1,
                "Qwerty1", new ArrayList<>(Arrays.asList("Qwerty")));
        Book firstBook7 = new Book("Qwerty", 1,
                "Qwerty", new ArrayList<>(Arrays.asList("Qwerty")));
        Book secondBook7 = new Book("Qwerty", 1,
                "Qwerty", new ArrayList<>(Arrays.asList("Qwerty", "Qwerty")));
        return new Object[][]{
                {firstBook1, null},
                {firstBook2, secondBook2},
                {firstBook3, secondBook3},
                {firstBook4, secondBook4},
                {firstBook5, secondBook5},
                {firstBook6, secondBook6},
                {firstBook7, secondBook7}
        };
    }

    @Test(dataProvider = "equalsToBookNegativeData")
    public void equalsToBookNegativeTest(Book firstBook, Book secondBook) {
        boolean actual = firstBook.equalsToBook(secondBook);
        assertFalse(actual);
    }

    @DataProvider(name = "equalsPositiveData")
    public Object[][] createEqualsPositiveData() {
        Book firstBook1 = bookStorageCreator.getCreatedBooks().get(1);
        Book secondBook1 = bookStorageCreator.getCreatedBooks().get(1);
        Book firstBook2 = bookStorageCreator.getCreatedBooks().get(5);
        return new Object[][]{
                {firstBook1, secondBook1},
                {firstBook2, firstBook2}
        };
    }

    @Test(dataProvider = "equalsPositiveData")
    public void equalsPositiveTest(Book firstBook, Book secondBook) {
        boolean actual = firstBook.equals(secondBook);
        assertTrue(actual);
    }

    @DataProvider(name = "equalsNegativeData")
    public Object[][] createEqualsNegativeData() {
        Book firstBook1 = bookStorageCreator.getCreatedBooks().get(1);
        Book firstBook2 = bookStorageCreator.getCreatedBooks().get(5);
        Book secondBook2 = bookStorageCreator.getCreatedBooks().get(6);
        Book firstBook3 = bookStorageCreator.getCreatedBooks().get(4);
        Book secondBook3 = new Book("Война и мир", 100,
                "Москва", new ArrayList<>(Arrays.asList("Лев Толстой")));
        Book firstBook4 = new Book("Qwerty", 1,
                "Qwerty", new ArrayList<>(Arrays.asList("Qwerty")));
        Book secondBook4 = new Book("Qwerty", 1,
                "Qwerty", new ArrayList<>(Arrays.asList("Qwerty")));
        return new Object[][]{
                {firstBook1, null},
                {firstBook2, secondBook2},
                {firstBook3, secondBook3},
                {firstBook4, secondBook4}
        };
    }

    @Test(dataProvider = "equalsNegativeData")
    public void equalsNegativeTest(Book firstBook, Book secondBook) {
        boolean actual = firstBook.equals(secondBook);
        assertFalse(actual);
    }
}
