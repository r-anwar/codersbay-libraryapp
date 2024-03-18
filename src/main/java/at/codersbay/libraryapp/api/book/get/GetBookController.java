package at.codersbay.libraryapp.api.book.get;

import at.codersbay.libraryapp.api.book.Book;
import at.codersbay.libraryapp.api.book.BookRepository;
import at.codersbay.libraryapp.api.book.BookResponseBody;
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

    @GetMapping("/{id}")
    public ResponseEntity<Book> getById(
            @PathVariable
            long id) {
        Optional<Book> optionalBook = this.bookRepository.findById(id);

        if(!optionalBook.isPresent()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }

        Book book = optionalBook.get();

        return ResponseEntity.ok(book);
    }


    @GetMapping("/isbn/{isbn}")
    public ResponseEntity<BookResponseBody> getByISBN(
            @PathVariable
            String isbn) {

        BookResponseBody bookResponseBody = new BookResponseBody();

        if (StringUtils.isEmpty(isbn)) {
            bookResponseBody.addErrorMessage(BookResponseBody.ISBN_NOT_PROVIDED);
            return new ResponseEntity(bookResponseBody, HttpStatus.BAD_REQUEST);
        }

        Optional<Book> optionalBook = bookRepository.findByIsbn(isbn);

        if (optionalBook.isPresent()) {
            return new ResponseEntity(optionalBook.get(), HttpStatus.OK);
        }
        return new ResponseEntity(null, HttpStatus.NOT_FOUND);
    }
}
