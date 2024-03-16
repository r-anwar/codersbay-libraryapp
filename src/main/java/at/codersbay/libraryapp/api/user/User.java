package at.codersbay.libraryapp.api.user;

import at.codersbay.libraryapp.api.books.Book;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name= "TB_USERS")
public class User {

    @Id
    @GeneratedValue(generator = "user-sequence-generator")
    @GenericGenerator(
            name = "user-sequence-generator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "user_sequence"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            }
    )
    private long id;

    @Column(nullable = false, unique = true)
    private String username;

    private String firstName;

    private String lastName;

    @JsonIgnore
    @OneToMany(mappedBy="user")
    private Set<Book> books;

    public User() {

    }

    public User(String username, String firstName, String lastName) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
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

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }

    public int hashCode() {
        if(this.username == null) {
            return 0;
        }

        return this.username.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        if(this == other) {
            return true;
        } else if(other == null) {
            return false;
        }

        if(!(other instanceof User)) {
            return false;
        }

        User otherUser = (User) other;

        if(this.username != null && this.username.equals(otherUser.username)) {
            return true;
        }

        return false;
    }
}