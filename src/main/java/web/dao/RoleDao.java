package web.dao;

import web.model.Role;
import web.model.User;
import web.service.CheckIDRole;

import java.util.List;
import java.util.Set;

public interface RoleDao {
    void add(Role role);
    void update(Role role);
    List<Role> getRoles();
    Set<Role> getRolesByIdList(List<Long> id);
    void deleteRole(Long id);
    List<CheckIDRole> getCheckIDRoles(User user);
    Role getRoleById(Long id);
}
