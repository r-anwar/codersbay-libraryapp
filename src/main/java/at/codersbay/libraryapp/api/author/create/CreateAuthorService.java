package at.codersbay.libraryapp.api.author.create;

import at.codersbay.libraryapp.api.author.Author;
import at.codersbay.libraryapp.api.author.AuthorRepository;
import at.codersbay.libraryapp.api.books.Book;
import at.codersbay.libraryapp.api.books.BookRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class CreateAuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    public Author create(String firstName, String lastName, Set<Book> books) {

        if (StringUtils.isEmpty(firstName)) {
            // throw new TitleIsEmptyException
        } else if (StringUtils.isEmpty(lastName)) {

        }

        Author author = new Author();
        author.setFirstName(firstName);
        author.setLastName(lastName);
        author.setBooks(books);

        return authorRepository.save(author);
    }
}
