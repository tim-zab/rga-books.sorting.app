package rga.books.sorting.app.services;

import rga.books.sorting.app.model.Book;

import java.util.List;

public interface CsvFileCreateService {

    void createCsvFile(List<Book> booksList, String fileName);
}
