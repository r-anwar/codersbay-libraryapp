package at.codersbay.libraryapp.api.author;

import at.codersbay.libraryapp.api.ResponseBody;

public class AuthorResponseBody extends ResponseBody {

    private Author author;

    public AuthorResponseBody() {

    }

    public AuthorResponseBody(Author author) {
        this.author = author;
    }
    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}
