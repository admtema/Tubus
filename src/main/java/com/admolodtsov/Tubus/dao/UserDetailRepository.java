package com.admolodtsov.Tubus.dao;

import com.admolodtsov.Tubus.entities.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDetailRepository extends JpaRepository<UserDetail, Integer> {
}
