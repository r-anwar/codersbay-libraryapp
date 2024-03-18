package at.codersbay.libraryapp.api.user.create;

import at.codersbay.libraryapp.api.user.User;
import at.codersbay.libraryapp.api.user.UserRepository;
import at.codersbay.libraryapp.api.user.UserResponseBody;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/user/")
public class CreateUserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public ResponseEntity<UserResponseBody> create(
            @RequestBody
            CreateUserDTO createUserDTO) {

        UserResponseBody body = new UserResponseBody();


        if (createUserDTO == null || StringUtils.isEmpty(createUserDTO.getUsername())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Optional<User> optionalUser = this.userRepository.findByUsername(createUserDTO.getUsername());

        if(optionalUser.isPresent()) {
            body.addErrorMessage("User ist bereits in Verwendung");
            return new ResponseEntity<>(body, HttpStatus.CONFLICT);
        }

        User user = new User(createUserDTO.getUsername(), createUserDTO.getFirstName(),
                createUserDTO.getLastName());


        try {
            this.userRepository.save(user);
        } catch(Throwable t) {
            body.addErrorMessage("Es ist ein Fehler aufgetreten");
            body.addErrorMessage(t.getMessage());

            return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        body.setUser(user);
        return ResponseEntity.ok(body);
    }
}