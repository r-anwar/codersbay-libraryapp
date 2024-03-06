package at.codersbay.libraryapp.api.author.get;

import at.codersbay.libraryapp.api.author.Author;
import at.codersbay.libraryapp.api.author.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/author/")
public class GetAuthorController {

    @Autowired
    private AuthorRepository authorRepository;

    @GetMapping
    public ResponseEntity<List<Author>> getAll() {

        List<Author> authors = authorRepository.findAll();

        if(authors.size() == 0) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(authors, HttpStatus.OK);
    }

    @GetMapping("/lastname/{lastName}")
    public ResponseEntity<List<Author>> getByLastName(
            @PathVariable String lastName) {
        return new ResponseEntity<>(authorRepository.findByLastName(lastName), HttpStatus.OK);
    }
}
