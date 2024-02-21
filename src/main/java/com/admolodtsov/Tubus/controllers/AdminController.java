package com.admolodtsov.Tubus.controllers;

import com.admolodtsov.Tubus.entities.User;
import com.admolodtsov.Tubus.entities.UserForm;
import com.admolodtsov.Tubus.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public String showUserList(Model model) {
        List<User> allUsers = userService.getAllUsers();
        model.addAttribute("allUsers", allUsers);
        return "users-view";
    }

    @PostMapping("/users/{userId}/remove")
    /* Удаление пользователя по id, указанному в URL-адресе */
    public String deleteUser(@PathVariable(value = "userId") Long userId){
        userService.deleteUser(userId);
        return "redirect:/users";
    }

    @GetMapping("/users/{userId}/edit")
    public String editUserDetails(@PathVariable("userId") Long userId, Model model) {
        if(!userService.existById(userId)){
            return "redirect:/users";
        }
        User user = userService.findUserById(userId);
        UserForm userForm = new UserForm(user, user.getUserDetail());
        model.addAttribute("userForm", userForm);
        return "user-edit-view";
    }

}
