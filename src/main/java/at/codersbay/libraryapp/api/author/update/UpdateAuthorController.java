package at.codersbay.libraryapp.api.author.update;

import at.codersbay.libraryapp.api.author.Author;
import at.codersbay.libraryapp.api.author.AuthorRepository;
import at.codersbay.libraryapp.api.author.AuthorResponse;
import at.codersbay.libraryapp.api.books.update.UpdateBookDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/author")
public class UpdateAuthorController {

    @Autowired
    AuthorRepository authorRepository;


    @PutMapping
    public ResponseEntity<AuthorResponse> update(
            @RequestBody
            UpdateAuthorDTO updateAuthorDTO) {

        if(updateAuthorDTO == null ||updateAuthorDTO.getId() <= 0) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        Optional<Author> optionalAuthor = this.authorRepository.findById(updateAuthorDTO.getId());


        if(optionalAuthor.isEmpty()) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        Author author = optionalAuthor.get();

        author.setFirstName(updateAuthorDTO.getFirstName());
        author.setLastName(updateAuthorDTO.getLastName());

        this.authorRepository.save(author);

        return ResponseEntity.ok(new AuthorResponse(author));
    }
}
