package com.admolodtsov.Tubus.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name="designProject")
public class DesignProject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "'поле обязательное для заполнения'")
    @Size(min= 2, message ="поле должно содержать минимум 2 символа")
    @Column(name="title")
    private String title;

    @NotBlank(message = "'поле должно содержать 15 символов'")
    @Size(min= 15, message ="'поле должно содержать 15 символов'")
    @Size(max= 15, message ="'поле должно содержать 15 символов'")
    @Column(name="designation")
    private String designation;

    @Column(name="creation_date")
    private String date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Transient
    private String username;

    public String getUsername() {
        return user.getUsername();
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @OneToMany(mappedBy = "designProject", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Assembly> assemblies;

    public DesignProject() {
    }

    public DesignProject(String title, String designation, String date) {
        this.title = title;
        this.designation = designation;
        this.date = java.time.LocalDate.now().toString();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
