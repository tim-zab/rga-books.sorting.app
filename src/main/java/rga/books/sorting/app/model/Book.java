package rga.books.sorting.app.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Book {

    private String isbn;

    private int publicationYear;

    private String title;

    private Author author;

}
