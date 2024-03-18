package at.codersbay.libraryapp.api.book;

import at.codersbay.libraryapp.api.ResponseBody;

public class BookResponseBody extends ResponseBody {

    public static final String ISBN_NOT_PROVIDED = "ISBN IS NOT PROVIDED.";

    private Book book;

    public BookResponseBody() {

    }

    public BookResponseBody(Book book) {
        this.book = book;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
