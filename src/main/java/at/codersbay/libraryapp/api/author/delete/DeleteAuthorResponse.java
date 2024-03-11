package at.codersbay.libraryapp.api.author.delete;

import at.codersbay.libraryapp.api.ResponseBody;


public class DeleteAuthorResponse extends ResponseBody {

    private boolean succeeded = false;

    public static DeleteAuthorResponse getInstance(boolean succeeded) {
        DeleteAuthorResponse response = new DeleteAuthorResponse();
        response.succeeded = succeeded;
        return response;
    }
}
