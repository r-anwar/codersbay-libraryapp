package at.codersbay.libraryapp.api.author;

public class AuthorResponse {

    private Author author;

    public AuthorResponse() {

    }

    public AuthorResponse(Author author) {
        this.author = author;
    }
    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}
