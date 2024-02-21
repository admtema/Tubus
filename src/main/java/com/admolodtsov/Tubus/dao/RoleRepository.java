package com.admolodtsov.Tubus.dao;

import com.admolodtsov.Tubus.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
