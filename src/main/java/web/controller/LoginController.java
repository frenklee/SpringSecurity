package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {
    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }
    @GetMapping("/logout")
    public String logout() {
        return "redirect:/login";
    }
}
