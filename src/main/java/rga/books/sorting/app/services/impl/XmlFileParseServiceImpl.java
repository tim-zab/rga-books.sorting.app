package rga.books.sorting.app.services.impl;

import org.springframework.stereotype.Service;
import org.w3c.dom.*;
import org.xml.sax.SAXException;
import rga.books.sorting.app.exception.AbsentElementsException;
import rga.books.sorting.app.model.Author;
import rga.books.sorting.app.model.Book;
import rga.books.sorting.app.services.XmlFileParseService;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class XmlFileParseServiceImpl implements XmlFileParseService {

    @Override
    public List<Book> parseXmlFile(String fileName) throws ParserConfigurationException, IOException, SAXException, AbsentElementsException {

        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        builderFactory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);

        DocumentBuilder documentBuilder = builderFactory.newDocumentBuilder();

        Document document = documentBuilder.parse(new File(fileName));

        NodeList books = document.getDocumentElement().getElementsByTagName("Kniha");

        int elementsQuantity = books.getLength();

        ArrayList<Book> booksList = new ArrayList<>(elementsQuantity);

        if (elementsQuantity == 0)
            throw new AbsentElementsException ("There is no information with needed tags in: " + fileName);

        for (int i = 0; i < elementsQuantity; i++) {
            Node book = books.item(i);
            NamedNodeMap attributes = book.getAttributes();
            Element element = (Element) book;

            Book bookFromXmlFile = getBookFromXmlFile(attributes, element);
            if (bookFromXmlFile != null) {
                booksList.add(bookFromXmlFile);
            }
        }
        return booksList;
    }

    public Book getBookFromXmlFile(NamedNodeMap attributes, Element element) {

            String isbn = attributes.getNamedItem("ISBN").getNodeValue();

            int publicationYear = Integer.parseInt(attributes.getNamedItem("Vydano").getNodeValue());

            String title = element.getElementsByTagName("Nazev").item(0).getTextContent();

            Node elementAuthor = element.getElementsByTagName("Autor").item(0);

            String name = elementAuthor.getAttributes().getNamedItem("Jmeno").getNodeValue();
            String surname = elementAuthor.getAttributes().getNamedItem("Prijmeni").getNodeValue();
            Author author = new Author(name, surname);

            return new Book (isbn, publicationYear, title, author);
    }
}
