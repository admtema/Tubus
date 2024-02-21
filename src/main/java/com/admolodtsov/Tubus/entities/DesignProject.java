package com.admolodtsov.Tubus.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="designProjects")
public class DesignProject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotBlank(message = "'поле обязательное для заполнения'")
    @Size(min= 2, message ="поле должно содержать минимум 2 символа")
    @Column(name="name")
    private String name;

    @NotBlank(message = "'поле должно содержать 15 символов'")
    @Size(min= 15, message ="'поле должно содержать 15 символов'")
    @Size(max= 15, message ="'поле должно содержать 15 символов'")
    @Column(name="designation")
    private String designation;

    @Column(name="project_date")
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

    public DesignProject() {
    }

    public DesignProject(int id, String name, String designation, String date) {
        this.id = id;
        this.name = name;
        this.designation = designation;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
