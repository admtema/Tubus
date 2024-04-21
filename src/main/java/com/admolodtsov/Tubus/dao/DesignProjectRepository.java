package com.admolodtsov.Tubus.dao;

import com.admolodtsov.Tubus.entities.DesignProject;
import com.admolodtsov.Tubus.entities.User;
import com.admolodtsov.Tubus.services.DesignProjectService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DesignProjectRepository extends JpaRepository<DesignProject, Long> {
    List<DesignProject> findProjectsByUser(User user);
}
