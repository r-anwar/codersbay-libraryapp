package at.codersbay.libraryapp.api.books.borrow;

import at.codersbay.libraryapp.api.books.Book;
import at.codersbay.libraryapp.api.books.BookRepository;
import at.codersbay.libraryapp.api.books.BookResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/book/")
public class BorrowBookController {

    @Autowired
    BookRepository bookRepository;


    @PatchMapping("/")
    public ResponseEntity<BookResponse> borrow(
            @RequestBody
            BorrowDTO patchBorrowDTO) {

        if(patchBorrowDTO == null) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        Optional<Book> optionalBook = bookRepository.findById(patchBorrowDTO.getId());

        if (optionalBook.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Book book = optionalBook.get();

        if(patchBorrowDTO.isBorrow() && book.isAvailable()) {
            book.setAvailable(false);
            this.bookRepository.save(book);
        } else if(!patchBorrowDTO.isBorrow() && !book.isAvailable()) {
            book.setAvailable(true);
            this.bookRepository.save(book);
        }

        return new ResponseEntity<>(BookResponse.getInstance(book), HttpStatus.OK);
    }
}
