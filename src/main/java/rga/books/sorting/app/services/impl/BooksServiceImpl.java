package rga.books.sorting.app.services.impl;

import org.springframework.stereotype.Service;
import rga.books.sorting.app.model.Book;
import rga.books.sorting.app.services.BooksService;

import java.util.List;

@Service
public class BooksServiceImpl implements BooksService {

    @Override
    public List<Book> getOldBooks (List<Book> books, int publicationYear) {
        return books.parallelStream()
                .filter(b -> publicationYear > b.getPublicationYear())
                .toList();
    }

    @Override
    public List<Book> getNewBooks (List<Book> books, int publicationYear) {
        return books.parallelStream()
                .filter(b -> publicationYear <= b.getPublicationYear())
                .toList();
    }
}
