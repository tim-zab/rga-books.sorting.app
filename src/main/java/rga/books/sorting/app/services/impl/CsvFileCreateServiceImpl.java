package rga.books.sorting.app.services.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import rga.books.sorting.app.model.Book;
import rga.books.sorting.app.services.CsvFileCreateService;

import java.io.*;
import java.util.List;

@Service
@Slf4j
public class CsvFileCreateServiceImpl implements CsvFileCreateService {

    @Override
    public void createCsvFile(List<Book> booksList, String fileName) {
        StringBuilder sb = new StringBuilder();
        String fileHeader = "ISBN" + ";" + "Nazev" + ";" + "Autor" + ";" + "Vydano" + "\n";
        sb.append(fileHeader);
        for (Book book : booksList) {
            sb.append(buildLine(book));
        }
        writeToCsvFile(fileName, sb.toString());
    }

    private String buildLine (Book book) {
        String delimiter = ";";
        String delimiterForAuthor = " ";
        String lineEnd = "\n";
        return book.getIsbn() + delimiter
                + book.getTitle() + delimiter
                + book.getAuthor().getName() + delimiterForAuthor
                + book.getAuthor().getSurname() + delimiter
                + book.getPublicationYear()
                + lineEnd;
    }

    private void writeToCsvFile(String fileName, String fileContent){
        try {
            PrintWriter pw = new PrintWriter(fileName);
            pw.write(fileContent);
            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
