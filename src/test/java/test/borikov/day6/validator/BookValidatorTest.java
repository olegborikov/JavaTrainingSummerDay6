package test.borikov.day6.validator;

import com.borikov.day6.validator.BookValidator;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.*;

public class BookValidatorTest {
    BookValidator bookValidator;

    @BeforeClass
    public void setUp() {
        bookValidator = new BookValidator();
    }

    @AfterClass
    public void tearDown() {
        bookValidator = null;
    }

    @DataProvider(name = "isIdCorrectPositiveData")
    public Object[][] createIsIdCorrectPositiveData() {
        return new Object[][]{
                {1},
                {100_000},
                {123}
        };
    }

    @Test(dataProvider = "isIdCorrectPositiveData")
    public void isIdCorrectPositiveTest(long id) {
        boolean actual = bookValidator.isIdCorrect(id);
        assertTrue(actual);
    }

    @DataProvider(name = "isIdCorrectNegativeData")
    public Object[][] createIsIdCorrectNegativeData() {
        return new Object[][]{
                {0},
                {100_001},
                {-123}
        };
    }

    @Test(dataProvider = "isIdCorrectNegativeData")
    public void isIdCorrectNegativeTest(long id) {
        boolean actual = bookValidator.isIdCorrect(id);
        assertFalse(actual);
    }

    @DataProvider(name = "isNameCorrectPositiveData")
    public Object[][] createIsNameCorrectPositiveData() {
        return new Object[][]{
                {"Я, ты"},
                {"War and peace part 1?"},
                {"Я"}
        };
    }

    @Test(dataProvider = "isNameCorrectPositiveData")
    public void isNameCorrectPositiveTest(String name) {
        boolean actual = bookValidator.isNameCorrect(name);
        assertTrue(actual);
    }

    @DataProvider(name = "isNameCorrectNegativeData")
    public Object[][] createIsNameCorrectNegativeData() {
        return new Object[][]{
                {""},
                {"    "},
                {null}
        };
    }

    @Test(dataProvider = "isNameCorrectNegativeData")
    public void isNameCorrectNegativeTest(String name) {
        boolean actual = bookValidator.isNameCorrect(name);
        assertFalse(actual);
    }

    @DataProvider(name = "isPriceCorrectPositiveData")
    public Object[][] createIsPriceCorrectPositiveData() {
        return new Object[][]{
                {0.01},
                {1000.0},
                {10.18}
        };
    }

    @Test(dataProvider = "isPriceCorrectPositiveData")
    public void isPriceCorrectPositiveTest(Double price) {
        boolean actual = bookValidator.isPriceCorrect(price);
        assertTrue(actual);
    }

    @DataProvider(name = "isPriceCorrectNegativeData")
    public Object[][] createIsPriceCorrectNegativeData() {
        return new Object[][]{
                {0.009},
                {1000.01},
                {-10.18}
        };
    }

    @Test(dataProvider = "isPriceCorrectNegativeData")
    public void isPriceCorrectNegativeTest(Double price) {
        boolean actual = bookValidator.isPriceCorrect(price);
        assertFalse(actual);
    }

    @DataProvider(name = "isPublishingHouseCorrectPositiveData")
    public Object[][] createIsPublishingHouseCorrectPositiveData() {
        return new Object[][]{
                {"\"Минск\" печать"},
                {"Московская печатная студия"},
                {"Москва\\Питер"}
        };
    }

    @Test(dataProvider = "isPublishingHouseCorrectPositiveData")
    public void isPublishingHouseCorrectPositiveTest(String publishingHouse) {
        boolean actual = bookValidator.isPublishingHouseCorrect(publishingHouse);
        assertTrue(actual);
    }

    @DataProvider(name = "isPublishingHouseCorrectNegativeData")
    public Object[][] createIsPublishingHouseCorrectNegativeData() {
        return new Object[][]{
                {"   "},
                {null},
                {""}
        };
    }

    @Test(dataProvider = "isPublishingHouseCorrectNegativeData")
    public void isPublishingHouseCorrectNegativeTest(String publishingHouse) {
        boolean actual = bookValidator.isPublishingHouseCorrect(publishingHouse);
        assertFalse(actual);
    }

    @DataProvider(name = "isAuthorsCorrectPositiveData")
    public Object[][] createIsAuthorsCorrectPositiveData() {
        List<String> authors1 = new ArrayList<>();
        authors1.add("Олег");
        authors1.add("Oleg");
        List<String> authors2 = new ArrayList<>();
        authors2.add("Alex");
        return new Object[][]{
                {authors1},
                {authors2}
        };
    }

    @Test(dataProvider = "isAuthorsCorrectPositiveData")
    public void isAuthorsCorrectPositiveTest(List<String> authors) {
        boolean actual = bookValidator.isAuthorsCorrect(authors);
        assertTrue(actual);
    }

    @DataProvider(name = "isAuthorsCorrectNegativeData")
    public Object[][] createIsAuthorsCorrectNegativeData() {
        List<String> authors1 = new ArrayList<>();
        authors1.add(null);
        authors1.add("Oleg");
        List<String> authors2 = new ArrayList<>();
        return new Object[][]{
                {authors1},
                {authors2},
                {null}
        };
    }

    @Test(dataProvider = "isAuthorsCorrectNegativeData")
    public void isAuthorsCorrectNegativeTest(List<String> authors) {
        boolean actual = bookValidator.isAuthorsCorrect(authors);
        assertFalse(actual);
    }

    @DataProvider(name = "isAuthorCorrectPositiveData")
    public Object[][] createIsAuthorCorrectPositiveData() {
        return new Object[][]{
                {"Олег"},
                {"Qwerty"},
                {"Alex"}
        };
    }

    @Test(dataProvider = "isAuthorCorrectPositiveData")
    public void isAuthorCorrectPositiveTest(String author) {
        boolean actual = bookValidator.isAuthorCorrect(author);
        assertTrue(actual);
    }

    @DataProvider(name = "isAuthorCorrectNegativeData")
    public Object[][] createIsAuthorCorrectNegativeData() {
        return new Object[][]{
                {"    "},
                {null},
                {""}
        };
    }

    @Test(dataProvider = "isAuthorCorrectNegativeData")
    public void isAuthorCorrectNegativeTest(String author) {
        boolean actual = bookValidator.isAuthorCorrect(author);
        assertFalse(actual);
    }
}
