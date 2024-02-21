package com.admolodtsov.Tubus.entities;

import jakarta.validation.Valid;
//Класс-обертка для помещения его полей (пользователя и его данных) в единую форму при регистрации
public class UserForm {
    @Valid
    private User user;

    @Valid
    private UserDetail userDetail;

    public UserForm(User user, UserDetail userDetail) {
        this.user = user;
        this.userDetail = userDetail;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public UserDetail getUserDetail() {
        return userDetail;
    }

    public void setUserDetail(UserDetail userDetail) {
        this.userDetail = userDetail;
    }
}
