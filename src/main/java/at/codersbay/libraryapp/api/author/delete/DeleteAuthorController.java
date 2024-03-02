package at.codersbay.libraryapp.api.author.delete;

import at.codersbay.libraryapp.api.author.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/author/")
public class DeleteAuthorController {

    @Autowired
    private AuthorRepository authorRepository;

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<DeleteAuthorResponse> delete(
            @PathVariable Long id) {

        this.authorRepository.deleteById(id);

        return new ResponseEntity<>(DeleteAuthorResponse.getInstance(true), HttpStatus.OK);
    }

}