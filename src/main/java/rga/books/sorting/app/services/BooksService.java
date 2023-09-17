package rga.books.sorting.app.services;

import rga.books.sorting.app.model.Book;

import java.util.List;

public interface BooksService {

    List<Book> getOldBooks (List<Book> books, int publicationYear);

    List<Book> getNewBooks (List<Book> books, int publicationYear);

}
