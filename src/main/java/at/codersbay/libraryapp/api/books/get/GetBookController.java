package at.codersbay.libraryapp.api.books.get;

import at.codersbay.libraryapp.api.books.Book;
import at.codersbay.libraryapp.api.books.BookRepository;
import at.codersbay.libraryapp.api.books.BookResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/book/")
public class GetBookController {

    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/")
    public ResponseEntity<List<Book>> getAll() {
        List<Book> books = bookRepository.findAll();

        return ResponseEntity.ok(books);
    }


    @GetMapping("/isbn/{isbn}")
    public ResponseEntity<BookResponse> getByISBN(
            @PathVariable
            String isbn) {

        BookResponse bookResponse = BookResponse.getInstance();

        if (StringUtils.isEmpty(isbn)) {
            bookResponse.addErrorMessage(BookResponse.ISBN_NOT_PROVIDED);
            return new ResponseEntity(bookResponse, HttpStatus.BAD_REQUEST);
        }

        Optional<Book> optionalBook = bookRepository.findBookByIsbn(isbn);

        if (optionalBook.isPresent()) {
            return new ResponseEntity(optionalBook.get(), HttpStatus.OK);
        }
        return new ResponseEntity(null, HttpStatus.NOT_FOUND);
    }
}
