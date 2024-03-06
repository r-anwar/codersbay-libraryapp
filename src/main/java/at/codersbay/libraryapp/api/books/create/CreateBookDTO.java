package at.codersbay.libraryapp.api.books.create;

import at.codersbay.libraryapp.api.books.Book;

import java.util.Set;

public class CreateBookDTO {

    private String title;

    private String isbn;

    private Set<Long> authorIds;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Set<Long> getAuthorIds() {
        return authorIds;
    }

    public void setAuthorIds(Set<Long> authorIds) {
        this.authorIds = authorIds;
    }
}
