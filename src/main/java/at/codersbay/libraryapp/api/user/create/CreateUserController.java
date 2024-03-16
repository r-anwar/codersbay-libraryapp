package at.codersbay.libraryapp.api.user.create;

import at.codersbay.libraryapp.api.ResponseBody;
import at.codersbay.libraryapp.api.books.Book;
import at.codersbay.libraryapp.api.books.BookResponseBody;
import at.codersbay.libraryapp.api.books.create.*;
import at.codersbay.libraryapp.api.user.User;
import at.codersbay.libraryapp.api.user.UserRepository;
import at.codersbay.libraryapp.api.user.UserResponseBody;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user/")
public class CreateUserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public ResponseEntity<UserResponseBody> create(
            @RequestBody
            CreateUserDTO createUserDTO) {

        if (createUserDTO == null || StringUtils.isEmpty(createUserDTO.getUsername())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        User user = new User();
        user.setUsername(createUserDTO.getUsername());
        user.setFirstName(createUserDTO.getFirstName());
        user.setLastName(createUserDTO.getLastName());

        try {
            this.userRepository.save(user);
        } catch(DataIntegrityViolationException dive) {
            // duplicate username
            ResponseBody body = new ResponseBody();
            body.addErrorMessage("Username bereits in Verwendung");
            return new ResponseEntity(body, HttpStatus.CONFLICT);
        } catch(Throwable t) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return ResponseEntity.ok(new UserResponseBody(user));
    }
}