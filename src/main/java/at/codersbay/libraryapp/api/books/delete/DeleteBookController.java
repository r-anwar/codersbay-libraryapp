package at.codersbay.libraryapp.api.books.delete;

import at.codersbay.libraryapp.api.Response;
import at.codersbay.libraryapp.api.books.Book;
import at.codersbay.libraryapp.api.books.BookRepository;
import at.codersbay.libraryapp.api.books.create.CreateBookService;
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
    public ResponseEntity<Response> delete(
            @PathVariable
            long id) {

        bookRepository.deleteById(id);

        Optional<Book> optionalBook = bookRepository.findById(id);

        Response response = new Response();

        if(optionalBook.isPresent()) {
            response.addErrorMessage("could not delete book by id '" + id + "'.");
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        } else {
            response.addMessage("Ok");
            return new ResponseEntity(response, HttpStatus.ACCEPTED);
        }
    }
}
