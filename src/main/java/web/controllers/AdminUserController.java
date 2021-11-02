package web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import web.model.Role;
import web.model.User;
import web.service.CheckIDRole;
import web.service.UserWithRole;
import web.service.RoleService;
import web.service.UserService;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
public class AdminUserController {
    private final UserService userService;
    private final RoleService roleService;
    public AdminUserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping(value = "/addUser")
    String addPage(ModelMap model) {
        User usr = new User();
        model.addAttribute("user", usr);
        return "addUser";
    }

    @PostMapping(value = "/addUser",  produces = {"application/xml; charset=UTF-8"})
    public String addUser(@ModelAttribute("user") User user, ModelMap model) {

        userService.setCreated(user, new GregorianCalendar().getTime());
        userService.setModified(user, new GregorianCalendar().getTime());
        userService.add(user);
        return "redirect:/admin";
    }

    @GetMapping(value = "/edit")
    String editPage(@RequestParam Long id, ModelMap model) {
        User user = userService.getUserById(id);
        List<Role> roles = roleService.getRoles();

        UserWithRole userWrole = new UserWithRole();
        userWrole.setUser(user);
        userWrole.setIdRoles(roles);
        model.addAttribute("userWrole", userWrole);
        return "editUser";
    }

    @PostMapping(value = "/editUser")
    String editUser(@ModelAttribute("userWrole") UserWithRole userWrole,
//            @ModelAttribute("roleName") String roleName,
                    ModelMap model) {
        User user = userWrole.getUser();
//        userService.setRoleByName(userWrole.getUser(), roleName); //user, roleName);
        List<Long> roleIds =  userWrole.getIdRoles().stream()
                .filter(x-> x.getChecked() == true)
                .map(x-> x.getId())
                .collect(Collectors.toList());
        List<Role> roles2add = roleService.getRolesByIdList(roleIds);
        userService.setRoles(user, roles2add.stream().collect(Collectors.toSet()));
        userService.setModified(user, new GregorianCalendar().getTime());
        userService.update(user);
        return "redirect:/admin";
    }

    @GetMapping(value = "/delete")
    public String DeleteUser(@RequestParam Long id, RedirectAttributes attr, ModelMap model) {
        userService.deleteUser(id);
        DateFormat df = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
        attr.addFlashAttribute("result001", "User deleted at " + df.format((new GregorianCalendar()).getTime()));
        return "redirect:/admin";
    }

    /////////////////// пробуем SELECTION тэг для  заполнения полями //////////////////
    @GetMapping(value = "/edit2")
    String editPage2(@RequestParam Long id, ModelMap model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);

        List<Role> roles = roleService.getRoles();
        model.addAttribute("selectableRoles", roles);

        return "editUser2";
    }

    @PostMapping(value = "/editUser2")
    String editUser2(
                    @ModelAttribute("user") User user,
                    @ModelAttribute("selectableRoles") List<Role> selectableRoles,
                    @ModelAttribute("roleName") String roleName,
                    ModelMap model) {
        userService.setRoleByName(user, roleName); //user, roleName);
        userService.setModified(user, new GregorianCalendar().getTime());
        userService.update(user);
        return "redirect:/admin";
    }


    /////////////////// end of пробуем SELECTION тэг для  заполнения полями //////////////////


}
