package at.codersbay.libraryapp.api.books.create;

import at.codersbay.libraryapp.api.author.Author;
import at.codersbay.libraryapp.api.author.AuthorRepository;
import at.codersbay.libraryapp.api.books.Book;
import at.codersbay.libraryapp.api.books.BookResponse;
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
@RequestMapping("/api/book/")
public class CreateBookController {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private CreateBookService createBookService;

    @PostMapping
    public ResponseEntity<BookResponse> create(
            @RequestBody
            CreateBookDTO createBookDTO) {

        System.out.println(createBookDTO.getTitle());

        if (createBookDTO == null || StringUtils.isEmpty(createBookDTO.getTitle())
                || StringUtils.isEmpty(createBookDTO.getIsbn()) || createBookDTO.getAuthorIds() == null
                || createBookDTO.getAuthorIds().isEmpty()) {
            return new ResponseEntity<>(BookResponse.getInstance(null), HttpStatus.BAD_REQUEST);
        }

        List<Author> authors = this.authorRepository.findAllById(createBookDTO.getAuthorIds());

        System.out.println(authors.size());

        Book book = this.createBookService.create(createBookDTO.getTitle(), createBookDTO.getIsbn(),
                new HashSet<>(authors));

        return new ResponseEntity<>(BookResponse.getInstance(book), HttpStatus.OK);
    }
}
