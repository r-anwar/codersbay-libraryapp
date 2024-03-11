package at.codersbay.libraryapp.api.books.create;

import at.codersbay.libraryapp.api.books.Book;
import at.codersbay.libraryapp.api.books.BookResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/book/")
public class CreateBookController {
    @Autowired
    private CreateBookService createBookService;

    @PostMapping
    public ResponseEntity<BookResponseBody> create(
            @RequestBody
            CreateBookDTO createBookDTO) {

        if (createBookDTO == null) {
            BookResponseBody response = new BookResponseBody();
            response.addErrorMessage("post body is empty.");

            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        Book book = null;

        try {
            book = this.createBookService.createByAuthorIds(createBookDTO.getTitle(), createBookDTO.getIsbn(),
                    createBookDTO.getAuthorIds());
        } catch (TitleIsEmptyException | ISBNIsEmptyException | EmptyAuthorException exception) {
            BookResponseBody response = new BookResponseBody();
            response.addErrorMessage(exception.getMessage());

            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(new BookResponseBody(book), HttpStatus.OK);
    }
}
