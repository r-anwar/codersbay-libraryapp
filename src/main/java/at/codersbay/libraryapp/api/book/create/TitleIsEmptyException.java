package at.codersbay.libraryapp.api.book.create;

public class TitleIsEmptyException extends Exception {

    public TitleIsEmptyException(String message) {
        super(message);
    }
}
