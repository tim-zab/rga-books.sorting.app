package rga.books.sorting.app;

import org.junit.jupiter.api.*;
import org.xml.sax.SAXException;
import rga.books.sorting.app.exception.AbsentElementsException;
import rga.books.sorting.app.model.Book;
import rga.books.sorting.app.services.XmlFileParseService;
import rga.books.sorting.app.services.impl.XmlFileParseServiceImpl;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class XmlFileParseServiceTest {

    private XmlFileParseService xmlFileParseService;

    @BeforeEach
    public void setUp() {
        xmlFileParseService = new XmlFileParseServiceImpl();
    }

    @Test
    @Order(2)
    @DisplayName("Test for parsing XML file with invalid data")
    void testParseXmlFile_InvalidData_ThrowsException() {

        String fileName = "src/test/resources/invalid.xml";

        Assertions.assertThrows(AbsentElementsException.class,
                () -> xmlFileParseService.parseXmlFile(fileName));
    }

    @Test
    @Order(1)
    @DisplayName("Test for parsing XML file with valid data")
    void testParseXmlFile_ValidData_ReturnsBookList() throws ParserConfigurationException, IOException, SAXException, AbsentElementsException {

        String fileName = "src/test/resources/valid.xml";

        List<Book> result = xmlFileParseService.parseXmlFile(fileName);

        Assertions.assertNotNull(result);
        Assertions.assertFalse(result.isEmpty());
    }

}
