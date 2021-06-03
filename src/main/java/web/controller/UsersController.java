package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserServiceImp;

@Controller
@RequestMapping(value="/users")
public class UsersController {
    @Autowired
    private UserServiceImp userService;

    @GetMapping()
    public String getUsers(ModelMap model){
        model.addAttribute("list",userService.listUsers());
        return "allUsers";
    }
    @GetMapping("/{id}")
    public String getUser(@PathVariable(name = "id") int id, ModelMap model){
        model.addAttribute("user",userService.getUser(id));
        return "user";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable(name = "id") int id, ModelMap model){
        userService.deleteUser(id);
        return "redirect:/users";
    }

    @GetMapping("/createForm")
    public String createForm(ModelMap model){
        model.addAttribute("user", new User());
        return "createUser";
    }
    @PostMapping()
    public String createUser(@RequestParam("name") String name,
                             @RequestParam("age") int age,
                             @RequestParam("weight") int weight,
                             ModelMap model){
        User user = new User();
        user.setName(name);
        user.setAge(age);
        user.setWeight(weight);
        userService.addUser(user);

        return "redirect:/users";
    }
    @GetMapping("/{id}/edit")
    public String edit(ModelMap model, @PathVariable("id") int id){
        model.addAttribute("user",userService.getUser(id));
        return "edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") User user, @PathVariable("id") int id){
        userService.updateUser(id, user);
        return "redirect:/users";
    }
}
