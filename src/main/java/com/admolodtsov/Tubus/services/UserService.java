package com.admolodtsov.Tubus.services;

import com.admolodtsov.Tubus.dao.RoleRepository;
import com.admolodtsov.Tubus.dao.UserRepository;
import com.admolodtsov.Tubus.entities.Role;
import com.admolodtsov.Tubus.entities.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }

    public User findUserById(Long userId){
        Optional<User> userFromDb = userRepository.findById(userId);
        return userFromDb.orElse(new User());
    }
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
    public boolean saveUser(User user){
        User userFromDb = userRepository.findByUsername(user.getUsername());
        if(userFromDb != null){
            return false;
        }
        switch (user.getUserDetail().getDepartment()) {
//            case "Конструкторский отдел" -> user.setRoles(Collections.singleton(new Role(1L, "ROLE_DESIGNER")));
            case "Отдел информатизации" -> user.setRoles(Collections.singleton(new Role(2L, "ROLE_ADMIN")));
            case "Технологический отдел" -> user.setRoles(Collections.singleton(new Role(3L, "ROLE_TECHNOLOGIST")));
            case "Отдел стандартизации и нормоконтроля" ->
                    user.setRoles(Collections.singleton(new Role(4L, "ROLE_STANDARDS_INSPECTOR")));
            case "Отдел технической документации" ->
                    user.setRoles(Collections.singleton(new Role(5L, "ROLE_ARCHIVIST")));
            default -> user.setRoles(Collections.singleton(new Role(1L, "ROLE_DESIGNER")));
        }

//        user.setRoles(Collections.singleton(new Role(1L, "ROLE_USER")));
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return true;
    }
    public boolean updateUser(User user){
        User userFromDb = userRepository.findByUsername(user.getUsername());
        if(userFromDb != null && !userFromDb.getId().equals(user.getId())){
            return false;
        }
//        user.setRoles(Collections.singleton(new Role(1L, "ROLE_USER")));
//        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return true;
    }

    public boolean deleteUser(Long userId){
        if (userRepository.findById(userId).isPresent()){
            userRepository.deleteById(userId);
            return true;
        }
        return false;
    }

    public boolean existById(Long userId){
        return userRepository.existsById(userId);
    }

    public List<User> userGetList(Long idMin) {
        return entityManager.createQuery(
                "SELECT u FROM User u WHERE u.id > : paramId", User.class)
                .setParameter("paramId", idMin).getResultList();
    }


}
