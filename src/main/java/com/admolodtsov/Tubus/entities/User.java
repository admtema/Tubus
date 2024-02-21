package com.admolodtsov.Tubus.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message ="поле обязательное для заполнения")
    @Size(min=4, message = "Имя должно содержать не менее 4 знаков")
    @Size(max = 20, message = "Имя должно содержать не более 20 знаков")
    private String username;

    @NotBlank(message ="поле обязательное для заполнения")
    @Size(min=5, message = "Пароль должен содержать меньше 5 знаков")
    private String password;

    @NotBlank(message ="поле обязательное для заполнения")
    @Transient
    private String passwordConfirm;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="userDetail_personnel_number")
    private UserDetail userDetail;


    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DesignProject> designProjects = new ArrayList<>();

    public void addDesignProjectToUser(DesignProject designProject){
        if(designProject==null){
            designProjects = new ArrayList<>();
        }
        designProjects.add(designProject);
        designProject.setUser(this);
    }

    public void removeDesignProject(DesignProject designProject){
        designProjects.remove(designProject);
        designProject.setUser(null);
    }


    public User() {
    }



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public List<DesignProject> getDesignProjects() {
        return designProjects;
    }

    public void setDesignProjects(List<DesignProject> designProjects) {
        this.designProjects = designProjects;
    }

    public UserDetail getUserDetail() {
        return userDetail;
    }

    public void setUserDetail(UserDetail userDetail) {
        this.userDetail = userDetail;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", roles=" + roles +
                ", designProjects=" + designProjects +
                '}';
    }
}
