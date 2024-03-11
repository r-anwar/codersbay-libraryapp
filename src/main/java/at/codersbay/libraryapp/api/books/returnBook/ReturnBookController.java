package at.codersbay.libraryapp.api.books.returnBook;

import at.codersbay.libraryapp.api.books.Book;
import at.codersbay.libraryapp.api.books.BookRepository;
import at.codersbay.libraryapp.api.books.BookResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/book/return/")
public class ReturnBookController {

    @Autowired
    BookRepository bookRepository;


    @PatchMapping("/{id}")
    public ResponseEntity<BookResponse> returnBook(
            @PathVariable
            long id) {

        Optional<Book> optionalBook = bookRepository.findById(id);

        if(optionalBook.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Book book = optionalBook.get();

        if(!book.isAvailable()) {
            book.setAvailable(true);
            this.bookRepository.save(book);
        }

        return new ResponseEntity<>(BookResponse.getInstance(book), HttpStatus.OK);
    }
}
