package at.codersbay.libraryapp.api.user;

import at.codersbay.libraryapp.api.ResponseBody;

public class UserResponseBody extends ResponseBody {

    private User user;

    public UserResponseBody() {

    }

    public UserResponseBody(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
