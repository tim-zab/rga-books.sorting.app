package rga.books.sorting.app;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.xml.sax.SAXException;
import rga.books.sorting.app.exception.AbsentElementsException;
import rga.books.sorting.app.model.Book;
import rga.books.sorting.app.services.BooksService;
import rga.books.sorting.app.services.BooksSortingService;
import rga.books.sorting.app.services.CsvFileCreateService;
import rga.books.sorting.app.services.XmlFileParseService;
import rga.books.sorting.app.services.impl.BooksSortingServiceImpl;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class BooksSortingServiceTest {
    @Mock
    private XmlFileParseService xmlFileParseService;

    @Mock
    private BooksService booksService;

    @Mock
    private CsvFileCreateService csvFileCreateService;

    @Test
    @DisplayName("Test books sorting")
    void testSort() throws IOException, AbsentElementsException, ParserConfigurationException, SAXException {

        // Arrange
        BooksSortingService booksSortingService = new BooksSortingServiceImpl(xmlFileParseService, booksService, csvFileCreateService);
        String inputXmlFileName = "example.xml";
        int publicationYear = 2021;
        String oldBooksFileName = "old_books.csv";
        String newBooksFileName = "new_books.csv";

        List<Book> booksList = new ArrayList<>();
        List<Book> oldBooksList = new ArrayList<>();
        List<Book> newBooksList = new ArrayList<>();

        Mockito.when(xmlFileParseService.parseXmlFile(inputXmlFileName)).thenReturn(booksList);
        Mockito.when(booksService.getOldBooks(booksList, publicationYear)).thenReturn(oldBooksList);
        Mockito.when(booksService.getNewBooks(booksList, publicationYear)).thenReturn(newBooksList);

        // Act
        booksSortingService.sort(inputXmlFileName, publicationYear, oldBooksFileName, newBooksFileName);

        // Assert
        Mockito.verify(csvFileCreateService).createCsvFile(oldBooksList, oldBooksFileName);
        Mockito.verify(csvFileCreateService).createCsvFile(newBooksList, newBooksFileName);
    }
}
