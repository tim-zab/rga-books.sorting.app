package rga.books.sorting.app.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.xml.sax.SAXException;
import rga.books.sorting.app.exception.AbsentElementsException;
import rga.books.sorting.app.services.BooksSortingService;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

@Controller
@RequestMapping("/rest/v1/sort")
public class BooksSortingController {

    private final BooksSortingService service;

    private final String oldBooksFileName;

    private final String newBooksFileName;

    @Autowired
    public BooksSortingController(BooksSortingService service,
                                  @Value("${app.old_books}") String oldBooksFileName,
                                  @Value("${app.new_books}") String newBooksFileName) {
        this.oldBooksFileName = oldBooksFileName;
        this.newBooksFileName = newBooksFileName;
        this.service = service;
    }

    @Operation(summary = "Sorting books",
            description = "Method separates old and new books by year")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "OK",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "Bad request",
                    content = @Content)})
    @PostMapping(value = "/books")
    public ResponseEntity<String> booksSorting (
            @RequestParam("file") String fileName,
            @RequestParam ("year") int publicationYear)
            throws ParserConfigurationException, IOException, AbsentElementsException, SAXException {
        service.sort(fileName, publicationYear, oldBooksFileName, newBooksFileName);
        return ResponseEntity.ok("Files have been written successfully!");
    }
}
