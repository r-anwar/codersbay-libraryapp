package at.codersbay.libraryapp.api.books.create;

import at.codersbay.libraryapp.api.author.Author;
import at.codersbay.libraryapp.api.author.AuthorRepository;
import at.codersbay.libraryapp.api.books.Book;
import at.codersbay.libraryapp.api.books.BookRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

@Service
public class CreateBookService {


    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;

    public Book createByAuthorIds(String title, String isbn, Set<Long> authorIds)
            throws TitleIsEmptyException, ISBNIsEmptyException, EmptyAuthorException {

        List<Author> authors = this.authorRepository.findAllById(authorIds);

        return create(title, isbn, new HashSet<>(authors));
    }

    public Book create(String title, String isbn, Set<Author> authors)
            throws TitleIsEmptyException, ISBNIsEmptyException, EmptyAuthorException {

        if (StringUtils.isEmpty(title)) {
            throw new TitleIsEmptyException("title is empty or null.");
        } else if (StringUtils.isEmpty(isbn)) {
            throw new ISBNIsEmptyException("ISBN is empty or null.");
        } else if (authors == null || authors.isEmpty()) {
            throw new EmptyAuthorException("given author list is null or empty.");
        }

        Book book = new Book();
        book.setTitle(title);
        book.setIsbn(isbn);
        book.setAuthors(authors);

        return this.bookRepository.save(book);
    }

}
