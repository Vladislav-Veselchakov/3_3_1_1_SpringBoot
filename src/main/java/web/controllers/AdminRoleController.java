package web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import web.model.Role;
import web.service.RoleService;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

@Controller
@RequestMapping("/admin")
public class AdminRoleController {
    private RoleService roleService;
    public AdminRoleController(RoleService service) {
        this.roleService = service;
    }

    @GetMapping(value = "/addRole")
    String addRolePage(ModelMap model) {
        Role role = new Role();
        model.addAttribute("role", role);
        return "addRole";
    }

    @PostMapping(value = "/addRole")
    public String addRole(@ModelAttribute("name") String name, ModelMap model)  {
        roleService.add(new Role(name));
        return "redirect:/admin";
    }

    @GetMapping(value = "/deleteRole")
    public String deleteRole(@RequestParam long id, RedirectAttributes attr, ModelMap model) {
        roleService.deleteRole(id);
        DateFormat df = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
        attr.addFlashAttribute("result002", "Role deleted at " + df.format((new GregorianCalendar()).getTime()));
        return "redirect:/admin";
    }

//    как ловим атрибут от метода addFlashAttribute
//@GetMapping(value = "/")
//public String printWelcome(ModelMap model) {
//    List<String> messages = new ArrayList<>();
//    messages.add("Hello!");
//    messages.add("I'm Spring MVC application");
//    messages.add("5.2.0 version by sep'19 ");
//    model.addAttribute("messages", messages);
//
//    List<User> users = userService.getAllUsers();
//    model.addAttribute("users", users);
//    if(!model.containsAttribute("result"))
//        model.addAttribute("result", "at Home");
//
//    return "index";
//}

//    Если используем метод addAtrubute, то просто к пути добавиться параметр: localhost/admin?result=lalala


    @GetMapping(value = "/editRole")
    public String editRole(@RequestParam long id, RedirectAttributes attr, ModelMap model) {
        Role role = roleService.getRoleById(id);
        model.addAttribute("role", role);
        return "editRole";
    }

    @PostMapping(value = "/editRole")
    String editUser(@ModelAttribute("role") Role role, ModelMap model) {
        roleService.update(role);
        return "redirect:/admin";
    }


}


