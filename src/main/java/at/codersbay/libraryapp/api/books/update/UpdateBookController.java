package at.codersbay.libraryapp.api.books.update;

import at.codersbay.libraryapp.api.books.Book;
import at.codersbay.libraryapp.api.books.BookRepository;
import at.codersbay.libraryapp.api.books.BookResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/book")
public class UpdateBookController {

    @Autowired
    BookRepository bookRepository;


    @PutMapping
    public ResponseEntity<BookResponse> update(
            @RequestBody
            UpdateBookDTO updateBookDTO) {

        if (updateBookDTO == null) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        Optional<Book> optionalBook = this.bookRepository.findById(updateBookDTO.getId());

        if (optionalBook.isEmpty()) {
            BookResponse response = BookResponse.getInstance(null);
            response.addErrorMessage("could not find book by id '" + updateBookDTO.getId());

            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        Book book = optionalBook.get();


        /** statt so bitte die StringUtils Klasse verwenden.
        if(updateBookDTO.getTitle() != null && updateBookDTO.getTitle().length() > 0) {
        }
         */


        if(!StringUtils.isEmpty(updateBookDTO.getTitle())) {
            book.setTitle(updateBookDTO.getTitle());
        }

        if(!StringUtils.isEmpty(updateBookDTO.getIsbn())) {
            book.setIsbn(updateBookDTO.getIsbn());
        }

        this.bookRepository.save(book);

        BookResponse response = BookResponse.getInstance(book);

        return ResponseEntity.ok(response);
    }
}