package at.codersbay.libraryapp.api.books;

import at.codersbay.libraryapp.api.Response;

public class BookResponse extends Response {

    public static final String ISBN_NOT_PROVIDED = "ISBN IS NOT PROVIDED.";

    private Book book;

    private BookResponse() {

    }
    public static BookResponse getInstance(Book book) {
        BookResponse response = getInstance();
        response.book = book;

        return response;
    }

    public static BookResponse getInstance() {
        return new BookResponse();
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
