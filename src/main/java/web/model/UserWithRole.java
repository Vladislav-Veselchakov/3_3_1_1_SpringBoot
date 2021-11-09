package web.model;


import org.springframework.stereotype.Service;
import web.model.Role;
import web.model.User;

import java.util.List;

@Service
public class UserWithRole {
    private User user;
    private List<Role> roles;

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}

