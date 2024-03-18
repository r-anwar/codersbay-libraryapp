package at.codersbay.libraryapp.api.book.create;

public class ISBNIsEmptyException extends Exception {

    public ISBNIsEmptyException(String message) {
        super(message);
    }
}
