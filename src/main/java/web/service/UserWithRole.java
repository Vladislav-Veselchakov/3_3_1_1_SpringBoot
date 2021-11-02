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

    public void setIdRoles(List<Role> roles) {
        idRoles = new ArrayList<>();
        for(int i = 0; i < roles.size(); i++) {
            idRoles.add(new CheckIDRole(false, roles.get(i).getId(), roles.get(i).getName()));
            if (i == 2) {
                idRoles.get(i).setChecked(true);
            }
        }

    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
