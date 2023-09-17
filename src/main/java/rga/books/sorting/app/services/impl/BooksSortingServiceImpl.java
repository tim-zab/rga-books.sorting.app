package rga.books.sorting.app.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;
import rga.books.sorting.app.model.Book;
import rga.books.sorting.app.exception.AbsentElementsException;
import rga.books.sorting.app.services.BooksService;
import rga.books.sorting.app.services.BooksSortingService;
import rga.books.sorting.app.services.CsvFileCreateService;
import rga.books.sorting.app.services.XmlFileParseService;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

@Service
public class BooksSortingServiceImpl implements BooksSortingService {

    private final XmlFileParseService xmlFileParseService;
    private final BooksService booksService;
    private final CsvFileCreateService csvFileCreateService;

    @Autowired
    public BooksSortingServiceImpl(XmlFileParseService xmlFileParseService,
                                   BooksService booksService,
                                   CsvFileCreateService csvFileCreateService) {
        this.xmlFileParseService = xmlFileParseService;
        this.booksService = booksService;
        this.csvFileCreateService = csvFileCreateService;
    }

    @Override
    public void sort (String inputXmlFileName,
                      int publicationYear,
                      String oldBooksFileName,
                      String newBooksFileName)
            throws ParserConfigurationException, IOException, AbsentElementsException, SAXException {

        List<Book> booksList = xmlFileParseService.parseXmlFile(inputXmlFileName);

        List<Book> oldBooksList = booksService.getOldBooks(booksList, publicationYear);

        List<Book> newBooksList = booksService.getNewBooks(booksList, publicationYear);

        csvFileCreateService.createCsvFile(oldBooksList, oldBooksFileName);

        csvFileCreateService.createCsvFile(newBooksList, newBooksFileName);

    }
}
