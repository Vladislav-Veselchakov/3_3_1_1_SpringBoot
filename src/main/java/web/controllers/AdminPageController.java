package web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.model.Role;
import web.model.User;
import web.service.RoleService;
import web.service.UserService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Controller
public class AdminPageController {
    @PersistenceContext
    EntityManager entityManager;

    private UserService userService;
    private RoleService roleService;

    public AdminPageController(UserService service, RoleService roleService) {
        this.userService = service;
        this.roleService = roleService;
    }

    @GetMapping
    public String AdminPage(Model model) {
        List<User> users = userService.getUsers();
        model.addAttribute("users", users);
        List<Role> roles = roleService.getRoles();
        model.addAttribute("roles", roles);
        model.addAttribute("result001", "result001");

        List<Object> userRole = entityManager.createNativeQuery( "SELECT user_id, role_id FROM user_role").getResultList();
        model.addAttribute("userRole", userRole);

        return "adminPage";
    }
    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

}
