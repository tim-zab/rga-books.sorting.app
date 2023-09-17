package rga.books.sorting.app.services;

import org.xml.sax.SAXException;
import rga.books.sorting.app.model.Book;
import rga.books.sorting.app.exception.AbsentElementsException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

public interface XmlFileParseService {

    List<Book> parseXmlFile (String fileName) throws ParserConfigurationException, IOException, SAXException, AbsentElementsException;
}
