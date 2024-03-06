package at.codersbay.libraryapp.api.books.create;

public class EmptyAuthorException extends Exception {

    EmptyAuthorException(String message) {
        super(message);
    }
}
