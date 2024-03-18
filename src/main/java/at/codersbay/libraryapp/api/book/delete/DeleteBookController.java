package at.codersbay.libraryapp.api.book.delete;

import at.codersbay.libraryapp.api.ResponseBody;
import at.codersbay.libraryapp.api.book.Book;
import at.codersbay.libraryapp.api.book.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/book/")
public class DeleteBookController {

    @Autowired
    BookRepository bookRepository;


    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseBody> delete(
            @PathVariable
            long id) {

        bookRepository.deleteById(id);

        Optional<Book> optionalBook = bookRepository.findById(id);

        ResponseBody responseBody = new ResponseBody();

        if(optionalBook.isPresent()) {
            responseBody.addErrorMessage("could not delete book by id '" + id + "'.");
            return new ResponseEntity(responseBody, HttpStatus.BAD_REQUEST);
        } else {
            responseBody.addMessage("Ok");
            return new ResponseEntity(responseBody, HttpStatus.ACCEPTED);
        }
    }
}
