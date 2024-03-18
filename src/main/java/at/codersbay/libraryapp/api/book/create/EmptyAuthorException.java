package at.codersbay.libraryapp.api.book.create;

public class EmptyAuthorException extends Exception {

    EmptyAuthorException(String message) {
        super(message);
    }
}
