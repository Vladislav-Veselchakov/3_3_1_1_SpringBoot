package web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdminPageController {
    @GetMapping
    public String AdminPage(@RequestParam(name = "sometext", required = false, defaultValue = "my master") String someText,
    Model model) {
        model.addAttribute("sometext", someText);
        return "adminPage";
    }
}
