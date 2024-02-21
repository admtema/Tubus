package com.admolodtsov.Tubus.controllers;

import com.admolodtsov.Tubus.entities.User;
import com.admolodtsov.Tubus.entities.UserDetail;
import com.admolodtsov.Tubus.entities.UserForm;
import com.admolodtsov.Tubus.services.UserDetailService;
import com.admolodtsov.Tubus.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserDetailService userDetailService;

    @GetMapping("/registration")
        public String registration(Model model) {
        User user = new User();
        UserDetail userDetail = new UserDetail();
        UserForm userForm = new UserForm(user, userDetail);
        model.addAttribute(userForm);
        return "registration-view";
        }

    @PostMapping("/registration")
    public String addUser(@Valid @ModelAttribute("userForm") UserForm userForm,
                          BindingResult bindingResult, Model model){
        model.addAttribute("userForm", userForm);
        userForm.getUser().setUserDetail(userForm.getUserDetail());
        if(bindingResult.hasErrors()){
            return "registration-view";
        }
        if(!userForm.getUser().getPassword().equals(userForm.getUser().getPasswordConfirm())){
            model.addAttribute("passwordError", "Введенные пароли не совпадают");
            return "registration-view";
        }
        if(!userService.saveUser(userForm.getUser())){
            model.addAttribute("usernameError", "Пользователь с таким именем уже существует");
            return "registration-view";
        }
            userDetailService.saveUserDetail(userForm.getUserDetail());
            return "redirect:/";
        }
}
