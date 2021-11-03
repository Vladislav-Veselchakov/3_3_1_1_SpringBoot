package web.service;

import web.model.Role;
import web.model.User;

import java.util.List;
import java.util.Set;

public interface RoleService {
    void add(Role role);
    void update(Role role);
    List<Role> getRoles();
    Set<Role> getRolesByIdList(List<Long> id);
    List<CheckIDRole> getCheckIDRoles(User user);
    public void deleteRole(Long id);
    Role getRoleById(Long id);
}
