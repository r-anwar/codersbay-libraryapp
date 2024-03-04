package at.codersbay.libraryapp.api.books;

import at.codersbay.libraryapp.api.author.Author;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;


import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Set;

@Entity
@Table(name = "TB_BOOKS")
public class Book {

    @Id
    @GeneratedValue(generator = "book-sequence-generator")
    @GenericGenerator(
            name = "book-sequence-generator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "book_sequence"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            }
    )
    private long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, unique = true)
    private String isbn;

    @ManyToMany()
    @JoinTable(
            name = "TB_BOOKS_AUTHORS",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id"))
    private Set<Author> authors;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Set<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<Author> authors) {
        this.authors = authors;
    }
}
