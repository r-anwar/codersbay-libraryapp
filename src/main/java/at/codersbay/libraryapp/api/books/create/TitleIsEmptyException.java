package at.codersbay.libraryapp.api.books.create;

public class TitleIsEmptyException extends Exception {

    public TitleIsEmptyException(String message) {
        super(message);
    }
}
