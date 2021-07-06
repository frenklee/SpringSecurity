package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import web.service.UserService;

import javax.annotation.PostConstruct;

@Controller
public class LoginController {

    @Autowired
    UserService userService;

    @PostConstruct
    public void init(){
        userService.addInitData();
    }
    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }
    @GetMapping("/logout")
    public String logout() {
        return "redirect:/login";
    }
}
