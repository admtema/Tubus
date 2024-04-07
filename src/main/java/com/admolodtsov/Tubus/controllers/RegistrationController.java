package com.admolodtsov.Tubus.controllers;

import com.admolodtsov.Tubus.entities.Role;
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

import java.util.Collections;

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
    //Сохраняет в бд нового пользователя
    public String addUser(@Valid @ModelAttribute("userForm") UserForm userForm,
                          BindingResult bindingResult, Model model){
        model.addAttribute("userForm", userForm);
        User user = userForm.getUser();
        user.setUserDetail(userForm.getUserDetail());
        //Если есть ошибки валидации вернуть страницу регистрации
        if(bindingResult.hasErrors()){
            return "registration-view";
        }
        if(!user.getPassword().equals(user.getPasswordConfirm())){
            model.addAttribute("passwordError", "Введенные пароли не совпадают");
            return "registration-view";
        }
        //Пробуем сохранить пользователя
        if(!userService.saveUser(user)){
            model.addAttribute("usernameError", "Пользователь с таким именем уже существует");
            return "registration-view";
        }
        //Сохраняем информацию о пользователе
            userDetailService.saveUserDetail(user.getUserDetail());
            return "redirect:/users";
        }
}
