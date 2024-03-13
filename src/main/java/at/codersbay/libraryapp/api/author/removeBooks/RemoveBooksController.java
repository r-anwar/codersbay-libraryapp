package at.codersbay.libraryapp.api.author.removeBooks;


import at.codersbay.libraryapp.api.author.Author;
import at.codersbay.libraryapp.api.author.AuthorRepository;
import at.codersbay.libraryapp.api.author.AuthorResponseBody;
import at.codersbay.libraryapp.api.author.addBooks.AddBooksDTO;
import at.codersbay.libraryapp.api.books.Book;
import at.codersbay.libraryapp.api.books.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/author/books")
public class RemoveBooksController {


    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    BookRepository bookRepository;

    @PutMapping
    public ResponseEntity<AuthorResponseBody> removeBooks(
            @RequestBody
            RemoveBooksDTO removeBooksDTO) {

        if (removeBooksDTO == null || removeBooksDTO.getBookIds() == null || removeBooksDTO.getBookIds().size() == 0
                || removeBooksDTO.getAuthorId() <= 0) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        Optional<Author> optionalAuthor = this.authorRepository.findById(removeBooksDTO.getAuthorId());

        if(optionalAuthor.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Author author = optionalAuthor.get();

        List<Book> books = this.bookRepository.findAllById(removeBooksDTO.getBookIds());

        if(books == null || books.size() == 0) {

            AuthorResponseBody authorResponseBody = new AuthorResponseBody();
            authorResponseBody.addMessage("Konnte keine Bücher anhand der Ids finden");

            return new ResponseEntity<>(authorResponseBody, HttpStatus.NOT_FOUND);
        }

        for(Book book : books) {
            if(book.getAuthors() == null) {
                book.setAuthors(new HashSet<>());
            }

            book.getAuthors().remove(author);
            this.bookRepository.save(book);
        }

        AuthorResponseBody authorResponseBody = new AuthorResponseBody(author);
        authorResponseBody.addMessage("Alle Bücher wurden erfolgreich entfernt.");

        return ResponseEntity.ok(authorResponseBody);
    }
}
