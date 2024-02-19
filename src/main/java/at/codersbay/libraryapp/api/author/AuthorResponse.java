package at.codersbay.libraryapp.api.author;

import at.codersbay.libraryapp.api.books.Book;

import javax.persistence.*;
import java.util.Set;


public class AuthorResponse {


    public static AuthorResponse getInstance(Author author) {
        AuthorResponse response = new AuthorResponse();
        return response;
    }
}
