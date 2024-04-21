package com.admolodtsov.Tubus.services;

import com.admolodtsov.Tubus.entities.DesignProject;
import com.admolodtsov.Tubus.entities.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

public interface DesignProjectService {

    List<DesignProject> getAllDesignProjects();

    boolean saveDesignProject(DesignProject designProject);

    DesignProject findDesignProjectById(Long id);

    boolean existsById(Long id);

    @PreAuthorize(value = "hasAnyRole('ADMIN','DESIGNER')" +
    "or authentication.name.equals(#designProject.username)")
    void updateDesignProject(DesignProject designProject);

    @PreAuthorize(value = "hasAnyRole('ADMIN', 'DESIGNER')" +
    "or authentication.name.equals(#designProject.username)")
    void deleteDesignProject(DesignProject designProject);

    List<DesignProject> findAllByUser(User user);
}
