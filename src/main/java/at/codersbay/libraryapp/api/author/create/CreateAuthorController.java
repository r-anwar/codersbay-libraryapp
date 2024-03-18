package at.codersbay.libraryapp.api.author.create;

import at.codersbay.libraryapp.api.author.Author;
import at.codersbay.libraryapp.api.author.AuthorResponseBody;
import at.codersbay.libraryapp.api.book.Book;
import at.codersbay.libraryapp.api.book.BookRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.List;

@RestController
@RequestMapping("/api/author/")
public class CreateAuthorController {

    @Autowired
    private CreateAuthorService authorService;

    @Autowired
    private BookRepository bookRepository;

    @PostMapping
    public ResponseEntity<AuthorResponseBody> create(
            @RequestBody
            CreateAuthorDTO createAuthorDTO) {

        if (createAuthorDTO == null || StringUtils.isEmpty(createAuthorDTO.getLastName())) {
            return new ResponseEntity<>(new AuthorResponseBody(), HttpStatus.BAD_REQUEST);
        }

        List<Book> books = this.bookRepository.findAllById(createAuthorDTO.getBookIds());

        Author author = this.authorService.create(createAuthorDTO.getFirstName(), createAuthorDTO.getLastName(),
                new HashSet<>(books));

        return new ResponseEntity<>(new AuthorResponseBody(author), HttpStatus.OK);
    }
}