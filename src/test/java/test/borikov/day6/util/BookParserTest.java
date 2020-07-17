package test.borikov.day6.util;

import com.borikov.day6.util.BookParser;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class BookParserTest {
    private BookParser bookParser;

    @BeforeClass
    public void setUp() {
        bookParser = new BookParser();
    }

    @AfterClass
    public void tearDown() {
        bookParser = null;
    }

    @DataProvider(name = "parseIdPositiveData")
    public Object[][] createParseIdPositiveData() {
        return new Object[][]{
                {"123", 123},
                {"1", 1},
                {"3.9", -1},
                {"-5", -5},
                {"1.", -1},
                {"abc", -1},
                {"?", -1},
                {null, -1},
                {"null", -1}
        };
    }

    @Test(dataProvider = "parseIdPositiveData")
    public void parseIdPositiveTest(String id, long expected) {
        long actual = bookParser.parseId(id);
        assertEquals(actual, expected);
    }

    @DataProvider(name = "parseIdNegativeData")
    public Object[][] createParseIdNegativeData() {
        return new Object[][]{
                {"123.", 123},
                {"3.9", 4},
                {"-5", -1},
                {"1.", 1},
                {"?", 1},
                {null, 0}
        };
    }

    @Test(dataProvider = "parseIdNegativeData")
    public void parseIdNegativeTest(String id, long expected) {
        long actual = bookParser.parseId(id);
        assertNotEquals(actual, expected);
    }

    @DataProvider(name = "parsePublishingYearPositiveData")
    public Object[][] createParsePublishingYearPositiveData() {
        return new Object[][]{
                {"123", 123},
                {"1", 1},
                {"3.9", -1},
                {"-5", -5},
                {"1.", -1},
                {"abc", -1},
                {"?", -1},
                {null, -1},
                {"null", -1}
        };
    }

    @Test(dataProvider = "parsePublishingYearPositiveData")
    public void parsePublishingYearPositiveTest(String publishingYear,
                                                int expected) {
        long actual = bookParser.parsePublishingYear(publishingYear);
        assertEquals(actual, expected);
    }

    @DataProvider(name = "parsePublishingYearNegativeData")
    public Object[][] createParsePublishingYearNegativeData() {
        return new Object[][]{
                {"123.", 123},
                {"3.9", 4},
                {"-5", -1},
                {"1.", 1},
                {"?", 1},
                {null, 0}
        };
    }

    @Test(dataProvider = "parsePublishingYearNegativeData")
    public void parsePublishingYearNegativeTest(String publishingYear,
                                                int expected) {
        long actual = bookParser.parsePublishingYear(publishingYear);
        assertNotEquals(actual, expected);
    }
}
