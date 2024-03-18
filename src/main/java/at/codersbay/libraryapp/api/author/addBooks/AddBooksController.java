package at.codersbay.libraryapp.api.author.addBooks;


import at.codersbay.libraryapp.api.author.Author;
import at.codersbay.libraryapp.api.author.AuthorRepository;
import at.codersbay.libraryapp.api.author.AuthorResponseBody;
import at.codersbay.libraryapp.api.book.Book;
import at.codersbay.libraryapp.api.book.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/author/books")
public class AddBooksController {


    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    BookRepository bookRepository;

    @PostMapping
    public ResponseEntity<AuthorResponseBody> addBooks(
            @RequestBody
            AddBooksDTO addBooksDTO) {

        if (addBooksDTO == null || addBooksDTO.getBookIds() == null || addBooksDTO.getBookIds().size() == 0
                || addBooksDTO.getAuthorId() <= 0) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        Optional<Author> optionalAuthor = this.authorRepository.findById(addBooksDTO.getAuthorId());

        if(optionalAuthor.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Author author = optionalAuthor.get();

        List<Book> books = this.bookRepository.findAllById(addBooksDTO.getBookIds());

        if(books == null || books.size() == 0) {

            AuthorResponseBody authorResponseBody = new AuthorResponseBody();
            authorResponseBody.addMessage("Konnt keine Bücher anhand der Ids finden");

            return new ResponseEntity<>(authorResponseBody, HttpStatus.NOT_FOUND);
        }

        for(Book book : books) {
            if(book.getAuthors() == null) {
                book.setAuthors(new HashSet<>());
            }

            /*
            Set<Author> authors = book.getAuthors();
            authors.add(author);
            book.setAuthors(authors);
             */
            book.getAuthors().add(author);
            this.bookRepository.save(book);
        }

        AuthorResponseBody authorResponseBody = new AuthorResponseBody(author);
        authorResponseBody.addMessage("Alle Bücher wurden erfolgreich hinzugefügt");

        return ResponseEntity.ok(authorResponseBody);
    }
}
