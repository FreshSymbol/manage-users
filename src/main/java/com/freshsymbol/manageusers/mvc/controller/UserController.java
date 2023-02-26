package com.freshsymbol.manageusers.mvc.controller;

import com.freshsymbol.manageusers.mvc.exceptions.ExistingUserException;
import com.freshsymbol.manageusers.mvc.model.User;
import com.freshsymbol.manageusers.mvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService service;


    @GetMapping("/all")
    public String getAllUsers(Model model) {
        List<User> users = service.getAllUsers();
        model.addAttribute("users", users);
        return "show-all-users";
    }

    @GetMapping("/new")
    public String createUser(Model model) {
        model.addAttribute("user", new User());
        return "user-info";
    }

    @PostMapping("/save")
    public String saveUser(@ModelAttribute("user") User user) {
        if (service.findByEmail(user.getEmail()) != null) {
            throw new ExistingUserException(
                    String.format("User with such an email: %s already exists", user.getEmail()));
        }
        service.saveUser(user);
        return "redirect:/users/all";
    }

    @PostMapping("/update")
    public String updateUser(@ModelAttribute("user") User user) {
        service.updateUser(user);
        return "redirect:/users/all";
    }

    @GetMapping("/edit/{id}")
    public String editeUser(@PathVariable Long id, Model model) {
        model.addAttribute("user", service.getUserById(id));
        return "edit-user-info";
    }

    @GetMapping("/delete/{id}")
    public String deleteUserById(@PathVariable Long id) {
        service.deleteById(id);
        return "redirect:/users/all";
    }
}