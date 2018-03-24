package hackatonkm.response;

import java.util.List;

import models.User;

public class GetUsersResponse implements GeneralResponse {
    private List<User> users;

    public GetUsersResponse(List<User> users) {
        super();
        this.users = users;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}