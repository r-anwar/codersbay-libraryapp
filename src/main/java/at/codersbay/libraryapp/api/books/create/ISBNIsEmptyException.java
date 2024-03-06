package at.codersbay.libraryapp.api.books.create;

public class ISBNIsEmptyException extends Exception {

    public ISBNIsEmptyException(String message) {
        super(message);
    }
}
