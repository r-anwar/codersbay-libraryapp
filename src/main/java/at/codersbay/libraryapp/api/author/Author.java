package at.codersbay.libraryapp.api.author;

import at.codersbay.libraryapp.api.books.Book;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "TB_AUTHORS")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false, unique = false)
    private String firstName;

    @Column(nullable = false, unique = false)
    private String lastName;

    @ManyToMany(mappedBy = "authors")
    private Set<Book> books;
}
