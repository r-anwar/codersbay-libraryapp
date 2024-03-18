package at.codersbay.libraryapp.api.book.borrow;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class BorrowedId implements Serializable {

    @Column(name="book_id")
    private long bookId;

    @Column(name="user_id")
    private long userId;

    protected BorrowedId() {}

    public BorrowedId(long bookId, long userId) {
        this.bookId = bookId;
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        BorrowedId that = (BorrowedId) o;
        return Objects.equals(bookId, that.bookId) &&
                Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookId, userId);
    }

}
