package rga.books.sorting.app.services;

import org.xml.sax.SAXException;
import rga.books.sorting.app.exception.AbsentElementsException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public interface BooksSortingService {

    void sort (String inputXmlFileName, int publicationYear, String oldBooksFileName, String newBooksFileName) throws ParserConfigurationException, IOException, AbsentElementsException, SAXException;

}
