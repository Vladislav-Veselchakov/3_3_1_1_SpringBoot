package web.service;

import org.springframework.stereotype.Service;
import web.model.Role;
import web.model.User;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserWithRole {
    private User user;
    private List<CheckIDRole> idRoles;

    public List<CheckIDRole> getIdRoles() {
        return idRoles;
    }

    public void setIdRoles(List<CheckIDRole> CheckIDRoles) {
        idRoles = CheckIDRoles;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
