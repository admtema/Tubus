package com.admolodtsov.Tubus.services;

import com.admolodtsov.Tubus.entities.UserDetail;
import org.springframework.security.access.prepost.PreAuthorize;

public interface UserDetailService {

    boolean saveUserDetail(UserDetail userDetail);

    UserDetail findUserDetailById(int id);

    @PreAuthorize(value = "hasRole('ADMIN')")
    void updateUserDetail(UserDetail userDetail);

    @PreAuthorize(value = "hasRole('ADMIN')")
    void deleteUserDetail(UserDetail userDetail);
}
