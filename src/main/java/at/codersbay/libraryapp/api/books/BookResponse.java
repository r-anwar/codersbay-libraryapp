package at.codersbay.libraryapp.api.books;

import at.codersbay.libraryapp.api.Response;

public class BookResponse extends Response {

    public static final String ISBN_NOT_PROVIDED = "ISBN IS NOT PROVIDED.";

    private Book book;

    public BookResponse() {

    }

    public BookResponse(Book book) {
        this.book = book;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
