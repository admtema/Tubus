package com.admolodtsov.Tubus.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="userDetail")
public class UserDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="personnel_number")
    private int personnelNumber;

    @OneToOne(mappedBy ="userDetail", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private User user;

    @NotBlank(message ="'поле обязательное для заполнения'")
    @Size(min = 2, message ="поле должно содержать минимум 2 символа")
    @Size(max = 20, message ="слишком длинная запись. Проверьте правильность написания или укажите сокращенную форму")
    @Column(name="first_name")
    private String firstName;

    @NotBlank(message ="'поле обязательное для заполнения'")
    @Size(min = 2, message ="поле должно содержать минимум 2 символа")
    @Size(max = 20, message ="слишком длинная запись. Проверьте правильность написания или укажите сокращенную форму")
    @Column(name="last_name")
    private String lastName;

    @NotBlank(message ="'поле обязательное для заполнения'")
    @Size(max = 20, message ="слишком длинная запись. Проверьте правильность написания или укажите сокращенную форму")
    @Column(name="patronymic")
    private String patronymic;

    @NotBlank(message ="'если телефонный номер не предоставлен, укажите номер руководителя'")
    @Size(min = 4, message ="добавочный номер содержит 4 символа")
    @Size(max = 4, message ="добавочный номер содержит 4 символа")
    @Column(name="phone_number")
    private String phoneNumber;

    @NotBlank(message ="'подразделение должно быть определено'")
    @Column(name="department")
    private String department;

    @NotBlank(message ="'должность должна быть определена'")
    @Column(name="position")
    private String position;

    //полное ФИО
    private String getFullName(){
        return this.lastName + " "
             + this.firstName + " "
             + this.patronymic;
    }
    //Фамилия И.О.
    private String getShortName(){
        return this.lastName + " "
                + this.firstName.substring(1) + ". "
                + this.patronymic.substring(1) + ". ";
    }

    public UserDetail() {
    }

    public UserDetail(String firstName, String lastName, String patronymic, String phoneNumber, String department, String position) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.phoneNumber = phoneNumber;
        this.department = department;
        this.position = position;
    }

    public int getPersonnelNumber() {
        return personnelNumber;
    }

    public void setPersonnelNumber(int personnelNumber) {
        this.personnelNumber = personnelNumber;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getPhoneNumber() {return phoneNumber;}

    public void setPhoneNumber(String phoneNumber) {this.phoneNumber = phoneNumber;}

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "UserDetail{" +
                "personnelNumber=" + personnelNumber +
                ", user=" + user +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", department='" + department + '\'' +
                ", position='" + position + '\'' +
                '}';
    }
}
