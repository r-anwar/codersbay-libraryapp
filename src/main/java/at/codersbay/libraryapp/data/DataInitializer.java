package at.codersbay.libraryapp.data;

import at.codersbay.libraryapp.api.author.Author;
import at.codersbay.libraryapp.api.author.AuthorRepository;
import at.codersbay.libraryapp.api.books.Book;
import at.codersbay.libraryapp.api.books.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashSet;

@Service
public class DataInitializer {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @PostConstruct
    public void setup() {
        importBooks();
    }


    public void importBooks() {
        Author rowling = new Author();
        rowling.setFirstName("J.K");
        rowling.setLastName("Rowling");

        authorRepository.save(rowling);

        Book potter1 = new Book();
        potter1.setTitle("Harry Potter Teil 1");
        potter1.setIsbn("yxcvbnm1234567890");
        potter1.setAuthors(new HashSet<>());
        potter1.getAuthors().add(rowling);
        bookRepository.save(potter1);


        Book potter2 = new Book();
        potter2.setTitle("Harry Potter Teil 2");
        potter2.setIsbn("1234567890yxcvbnm");
        potter2.setAuthors(new HashSet<>());
        potter2.getAuthors().add(rowling);

        Book potter3 = new Book();
        potter3.setTitle("Harry Potter Teil 3");
        potter3.setIsbn("mnbvcxy1234567890");
        potter3.setAuthors(new HashSet<>());
        potter3.getAuthors().add(rowling);

        rowling.setBooks(new HashSet<>());
        rowling.getBooks().add(potter1);
        rowling.getBooks().add(potter2);

        bookRepository.save(potter2);

        bookRepository.save(potter3);










        Author hesse = new Author();
        hesse.setFirstName("Hermann");
        hesse.setLastName("Hesse");
        authorRepository.save(hesse);

        Book narzissUndGoldmund = new Book();
        narzissUndGoldmund.setTitle("Narziß und Goldmund");
        narzissUndGoldmund.setIsbn("ISBNYXCVBNM");
        narzissUndGoldmund.setAuthors(new HashSet<>());
        narzissUndGoldmund.getAuthors().add(hesse);

        bookRepository.save(narzissUndGoldmund);



    }
}
