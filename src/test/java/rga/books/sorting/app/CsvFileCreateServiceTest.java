package rga.books.sorting.app;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import rga.books.sorting.app.model.Author;
import rga.books.sorting.app.model.Book;
import rga.books.sorting.app.services.CsvFileCreateService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CsvFileCreateServiceTest {

    @Mock
    private CsvFileCreateService csvFileCreateService;

    @Test
    @DisplayName("Create CSV-file test")
    void testCreateCsvFile() {
        List<Book> booksList = new ArrayList<>();
        booksList.add(new Book("1234567890", 2021, "Book 1", new Author("Author 1", "Surname 1")));
        booksList.add(new Book("0987654321", 2022, "Book 2", new Author("Author 2", "Surname 2")));
        String fileName = "books.csv";

        csvFileCreateService.createCsvFile(booksList, fileName);

        verify(csvFileCreateService, times(1)).createCsvFile(booksList, fileName);
    }
}