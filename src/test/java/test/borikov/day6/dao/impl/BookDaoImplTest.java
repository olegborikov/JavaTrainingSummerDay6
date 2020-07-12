package test.borikov.day6.dao.impl;

import com.borikov.day6.dao.impl.BookDaoImpl;
import com.borikov.day6.entity.Book;
import com.borikov.day6.entity.Library;
import com.borikov.day6.util.BooksCreator;
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.*;

public class BookDaoImplTest {
    private Library library;
    private BookDaoImpl bookDaoImpl;

    @BeforeClass
    public void setUpClass() {
        bookDaoImpl = new BookDaoImpl();
    }

    @AfterClass
    public void tearDownClass() {
        bookDaoImpl = null;
    }

    @BeforeMethod
    public void setUpMethod() {
        library = Library.getInstance();
        List<Book> books = BooksCreator.createBooks();
        for (Book book : books) {
            library.add(book);
        }
    }

    @AfterMethod
    public void tearDownMethod() {
        library.reset();
    }

    @Test
    public void addBookTest() {
    }

    @Test
    public void addBookExceptionTest() {
    }

    @Test
    public void removeBookTest() {
    }

    @Test
    public void removeBookExceptionTest() {
    }

    @Test
    public void findAllBooksTest() {
    }

    @Test
    public void findBookByIdTest() {
    }

    @DataProvider(name = "findBooksByNameData")
    public Object[][] createFindBooksByNameData() {
        List<Book> books = BooksCreator.createBooks();
        String name1 = "Война и мир";
        List<Book> expected1 = new ArrayList<>();
        expected1.add(books.get(0));
        expected1.add(books.get(4));
        String name2 = "Метро 2033";
        List<Book> expected2 = new ArrayList<>();
        expected2.add(books.get(8));
        String name3 = "я";
        List<Book> expected3 = new ArrayList<>();
        return new Object[][]{
                {name1, expected1},
                {name2, expected2},
                {name3, expected3}
        };
    }

    @Test(dataProvider = "findBooksByNameData")
    public void findBooksByNameTest(String name, List<Book> expected) {
        List<Book> actual = bookDaoImpl.findBooksByName(name);
        assertEquals(actual, expected);
    }

    @Test
    public void findBooksByPriceTest() {
    }

    @Test
    public void findBooksByPublishingHouseTest() {
    }

    @Test
    public void findBooksByAuthorTest() {
    }

    @Test
    public void sortBooksByIdTest() {
    }

    @Test
    public void sortBooksByNameTest() {
    }

    @Test
    public void sortBooksByPriceTest() {
    }

    @Test
    public void sortBooksByPublishingHouseTest() {
    }

    @Test
    public void sortBooksByAuthorsTest() {
    }
}
