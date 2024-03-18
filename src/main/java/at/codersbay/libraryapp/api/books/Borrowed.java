package at.codersbay.libraryapp.api.books;

import at.codersbay.libraryapp.api.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name="TB_BORROWS")
public class Borrowed {

    @EmbeddedId
    private BorrowedId id;

    @JsonIgnore
    @OneToOne
    @MapsId("bookId")
    private Book book;

    @JsonIgnore
    @ManyToOne
    @MapsId("userId")
    private User user;

    @Column
    private LocalDateTime createdOn = LocalDateTime.now();

    protected Borrowed() {

    }

    public Borrowed(Book book, User user) {
        this.book = book;
        this.user = user;
        this.id = new BorrowedId(book.getId(), user.getId());
    }


    public BorrowedId getId() {
        return id;
    }

    public void setId(BorrowedId id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }
}
