package com.admolodtsov.Tubus.services;

import com.admolodtsov.Tubus.dao.UserDetailRepository;
import com.admolodtsov.Tubus.entities.UserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailServiceImpl implements UserDetailService {

    @Autowired
    private UserDetailRepository userDetailRepository;

    @Override
    public boolean saveUserDetail(UserDetail userDetail) {
        userDetailRepository.save(userDetail);
        return true;
    }

    @Override
    public UserDetail findUserDetailById(int id) {
        Optional<UserDetail> userDetailFromDb = userDetailRepository.findById(id);
        return userDetailFromDb.orElse(new UserDetail());
    }

    @Override
    public void updateUserDetail(UserDetail userDetail) {
        userDetailRepository.save(userDetail);
    }

    @Override
    public void deleteUserDetail(UserDetail userDetail) {
        userDetailRepository.delete(userDetail);
    }
}
