package com.admolodtsov.Tubus.controllers;

import com.admolodtsov.Tubus.entities.Role;
import com.admolodtsov.Tubus.entities.User;
import com.admolodtsov.Tubus.entities.UserForm;
import com.admolodtsov.Tubus.services.UserDetailService;
import com.admolodtsov.Tubus.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Set;

@Controller
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserDetailService userDetailService;

    @GetMapping("/users")
    public String showUserList(Model model) {
        List<User> allUsers = userService.getAllUsers();
        model.addAttribute("allUsers", allUsers);
        return "users-view";
    }


    @GetMapping("/users/{userId}/edit")
    public String editUserForm(@PathVariable("userId") Long userId, Model model) {
        if(!userService.existById(userId)){
            return "redirect:/users";
        }
        User user = userService.findUserById(userId);
        UserForm userForm = new UserForm(user, user.getUserDetail());
        model.addAttribute("userForm", userForm);
        return "user-edit-view";
    }

    @PostMapping("/users/{userId}/edit")
    /* Сохранение существующего пользователя (апдейт) с id из URL адреса и с атрибутами, взятыми из формы */
    public String updateUserForm(@PathVariable("userId") Long userId,
                                 @Valid @ModelAttribute ("userForm") UserForm newUserForm,
                                 BindingResult bindingResult, Model model){
        model.addAttribute("userForm", newUserForm);
        newUserForm.getUser().setUserDetail(newUserForm.getUserDetail());
        //Если есть ошибки валидации вернуть страницу редактирования
        if(bindingResult.hasErrors()){
            return "user-edit-view";
        }

        //Если не совпадают пароли, вернуть страницу редактирования
        if(!newUserForm.getUser().getPassword().equals(newUserForm.getUser().getPasswordConfirm())){
            model.addAttribute("passwordError", "Введенные пароли не совпадают");
            return "user-edit-view";
        }
        User user = userService.findUserById(userId);
        user.setUsername(newUserForm.getUser().getUsername());
        user.getUserDetail().setDepartment(newUserForm.getUserDetail().getDepartment());
        user.getUserDetail().setFirstName(newUserForm.getUserDetail().getFirstName());
        user.getUserDetail().setLastName(newUserForm.getUserDetail().getLastName());
        user.getUserDetail().setPatronymic(newUserForm.getUserDetail().getPatronymic());
        user.getUserDetail().setDepartment(newUserForm.getUserDetail().getDepartment());
        user.getUserDetail().setPosition(newUserForm.getUserDetail().getPosition());
        user.getUserDetail().setPhoneNumber(newUserForm.getUserDetail().getPhoneNumber());
        System.out.println(user.getUserDetail().toString());
        System.out.println(user);
        //Пробуем сохранить обновленного пользователя
        if(!userService.updateUser(user)){
            model.addAttribute("usernameError", "Пользователь с таким именем уже существует");
            return "user-edit-view";
        }
//        Сохраняем обновленную информацию о пользователе
        userDetailService.updateUserDetail(user.getUserDetail());
        return "redirect:/users";
    }

    @PostMapping("/users/{userId}/remove")
    /* Удаление пользователя по id, указанному в URL-адресе */
    public String deleteUser(@PathVariable(value = "userId") Long userId){
       User user = userService.findUserById(userId);
       // Если среди прав пользователя нет прав Администратора
       if(!user.getRoles().contains("ADMIN")){
           userService.deleteUser(userId);
       }
        return "redirect:/users";
    }
}
