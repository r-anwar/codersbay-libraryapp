package at.codersbay.libraryapp.api.books.create;

import at.codersbay.libraryapp.api.author.Author;
import at.codersbay.libraryapp.api.author.AuthorRepository;
import at.codersbay.libraryapp.api.books.Book;
import at.codersbay.libraryapp.api.books.BookResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/api/book/")
public class CreateBookController {
    @Autowired
    private CreateBookService createBookService;

    @PostMapping
    public ResponseEntity<BookResponse> create(
            @RequestBody
            CreateBookDTO createBookDTO) {

        if (createBookDTO == null) {
            BookResponse response = BookResponse.getInstance(null);
            response.addErrorMessage("post body is empty.");

            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        Book book = null;

        try {
            book = this.createBookService.createByAuthorIds(createBookDTO.getTitle(), createBookDTO.getIsbn(),
                    createBookDTO.getAuthorIds());
        } catch (TitleIsEmptyException | ISBNIsEmptyException | EmptyAuthorException exception) {
            BookResponse response = BookResponse.getInstance(null);
            response.addErrorMessage(exception.getMessage());

            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(BookResponse.getInstance(book), HttpStatus.OK);
    }
}
