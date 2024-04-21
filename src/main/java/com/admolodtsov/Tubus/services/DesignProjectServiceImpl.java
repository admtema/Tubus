package com.admolodtsov.Tubus.services;

import com.admolodtsov.Tubus.dao.DesignProjectRepository;
import com.admolodtsov.Tubus.entities.DesignProject;
import com.admolodtsov.Tubus.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DesignProjectServiceImpl implements DesignProjectService {

    @Autowired
    DesignProjectRepository designProjectRepository;

    @Override
    public List<DesignProject> getAllDesignProjects() {
        return designProjectRepository.findAll();
    }

    @Override
    public boolean saveDesignProject(DesignProject designProject) {
        designProjectRepository.save(designProject);
        return true;
    }

    @Override
    public DesignProject findDesignProjectById(Long id) {
        Optional<DesignProject> designProjectFromDb = designProjectRepository.findById(id);
        return designProjectFromDb.orElse(new DesignProject());
    }

    @Override
    public boolean existsById(Long id) {
        return designProjectRepository.existsById(id);
    }

    @Override
    public void updateDesignProject(DesignProject designProject) {
        designProjectRepository.save(designProject);
    }

    @Override
    public void deleteDesignProject(DesignProject designProject) {
        designProjectRepository.delete(designProject);
    }

    @Override
    public List<DesignProject> findAllByUser(User user) {
        return designProjectRepository.findProjectsByUser(user);
    }
}
