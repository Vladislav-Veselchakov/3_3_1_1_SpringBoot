package web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import web.model.User;
import web.service.UserService;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

@Controller
@RequestMapping("/admin")
public class AdminUserController {
    private UserService userService;
    public AdminUserController(UserService service) {
        this.userService = service;
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
        // user.roles.iterator().next().getAuthority()
        model.addAttribute("user", user);
        return "editUser";
    }

    @PostMapping(value = "/editUser")
    String editUser(@ModelAttribute("user") User user, ModelMap model, @ModelAttribute("roleName") String roleName) {
        userService.setRoleByName(user, roleName);
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


}
