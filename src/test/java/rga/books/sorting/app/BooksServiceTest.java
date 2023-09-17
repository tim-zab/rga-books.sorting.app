package rga.books.sorting.app;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

import rga.books.sorting.app.model.Author;
import rga.books.sorting.app.model.Book;
import rga.books.sorting.app.services.BooksService;
import rga.books.sorting.app.services.impl.BooksServiceImpl;

import java.util.ArrayList;
import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BooksServiceTest {

    private BooksService booksService;
    private List<Book> books;

    @BeforeEach
    public void setUp() {
        booksService = new BooksServiceImpl();
        books = new ArrayList<>();
        books.add(new Book("ISBN 1", 2015, "Title 1", new Author("Name 1", "Surname 1")));
        books.add(new Book("ISBN 2", 2018, "Title 2", new Author("Name 2", "Surname 2")));
        books.add(new Book("ISBN 3", 2021, "Title 3", new Author("Name 3", "Surname 3")));
    }

    @Test
    @Order(1)
    @DisplayName("Test get old books")
    void testGetOldBooks() {
        int publicationYear = 2018;
        List<Book> oldBooks = booksService.getOldBooks(books, publicationYear);
        assertEquals(1, oldBooks.size());
        assertEquals("Title 1", oldBooks.get(0).getTitle());
    }

    @Test
    @Order(2)
    @DisplayName("Test get new books")
    void testGetNewBooks() {
        int publicationYear = 2018;
        List<Book> newBooks = booksService.getNewBooks(books, publicationYear);
        assertEquals(2, newBooks.size());
        assertEquals("Title 2", newBooks.get(0).getTitle());
        assertEquals("Title 3", newBooks.get(1).getTitle());
    }
}