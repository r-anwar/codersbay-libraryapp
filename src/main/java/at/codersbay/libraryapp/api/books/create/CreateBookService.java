package at.codersbay.libraryapp.api.books.create;

import at.codersbay.libraryapp.api.author.Author;
import at.codersbay.libraryapp.api.books.Book;
import at.codersbay.libraryapp.api.books.BookRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class CreateBookService {

    @Autowired
    private BookRepository bookRepository;

    public Book create(String title, String isbn, Set<Author> authors) {

        if (StringUtils.isEmpty(title)) {
            // throw new TitleIsEmptyException
        } else if (StringUtils.isEmpty(isbn)) {

        } else if (authors == null || authors.isEmpty()) {

        }

        Book book = new Book();
        book.setTitle(title);
        book.setIsbn(isbn);
        book.setAuthors(authors);

        return this.bookRepository.save(book);
    }

}
