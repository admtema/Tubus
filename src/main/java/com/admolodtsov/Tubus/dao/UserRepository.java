package com.admolodtsov.Tubus.dao;

import com.admolodtsov.Tubus.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);


}
