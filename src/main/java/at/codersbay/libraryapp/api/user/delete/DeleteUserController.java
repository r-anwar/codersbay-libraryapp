package at.codersbay.libraryapp.api.user.delete;

import at.codersbay.libraryapp.api.ResponseBody;
import at.codersbay.libraryapp.api.books.Book;
import at.codersbay.libraryapp.api.user.User;
import at.codersbay.libraryapp.api.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/user/")
public class DeleteUserController {

    @Autowired
    UserRepository userRepository;


    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseBody> delete(
            @PathVariable
            long id) {

        userRepository.deleteById(id);

        Optional<User> optionalUser = userRepository.findById(id);

        ResponseBody responseBody = new ResponseBody();

        if(optionalUser.isPresent()) {
            responseBody.addErrorMessage("could not delete user by id '" + id + "'.");
            return new ResponseEntity(responseBody, HttpStatus.BAD_REQUEST);
        } else {
            responseBody.addMessage("Ok");
            return new ResponseEntity(responseBody, HttpStatus.ACCEPTED);
        }
    }
}
