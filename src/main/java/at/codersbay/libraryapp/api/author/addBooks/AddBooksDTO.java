package at.codersbay.libraryapp.api.author.addBooks;

import java.util.Set;

public class AddBooksDTO {

    private long authorId;
    private Set<Long> bookIds;

    public long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(long authorId) {
        this.authorId = authorId;
    }

    public Set<Long> getBookIds() {
        return bookIds;
    }

    public void setBookIds(Set<Long> bookIds) {
        this.bookIds = bookIds;
    }
}