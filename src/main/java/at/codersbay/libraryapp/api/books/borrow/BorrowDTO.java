package at.codersbay.libraryapp.api.books.borrow;

public class BorrowDTO {

    private long id;

    private boolean borrow;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isBorrow() {
        return borrow;
    }

    public void setBorrow(boolean borrow) {
        this.borrow = borrow;
    }
}
