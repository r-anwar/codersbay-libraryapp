package at.codersbay.libraryapp.api.author.update;

import at.codersbay.libraryapp.api.books.Book;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.ManyToMany;
import java.util.Set;

public class UpdateAuthorDTO {

    private long id;

    private String firstName;

    private String lastName;

    public String getFirstName() {
        return firstName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
