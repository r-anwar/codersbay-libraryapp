package at.codersbay.libraryapp.api.books.borrow;

import at.codersbay.libraryapp.api.books.Book;
import at.codersbay.libraryapp.api.books.BookRepository;
import at.codersbay.libraryapp.api.books.BookResponseBody;
import at.codersbay.libraryapp.api.books.Borrowed;
import at.codersbay.libraryapp.api.books.create.CreateBookService;
import at.codersbay.libraryapp.api.user.User;
import at.codersbay.libraryapp.api.user.UserRepository;
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

    @Autowired
    UserRepository userRepository;

    @PatchMapping("/")
    public ResponseEntity<BookResponseBody> borrow(
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

        if(patchBorrowDTO.isBorrow()) {
            Optional<User> optionalUser = this.userRepository.findById(patchBorrowDTO.getUserId());

            if(optionalUser.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            User user = optionalUser.get();

            book.borrow(user);
            this.bookRepository.save(book);
        } else {
            if(book.getBorrowed() != null) {
                book.setBorrowed(null);
                this.bookRepository.save(book);
            }
        }

        return new ResponseEntity<>(new BookResponseBody(book), HttpStatus.OK);
    }
}
