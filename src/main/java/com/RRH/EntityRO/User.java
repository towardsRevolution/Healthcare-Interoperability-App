package com.RRH.EntityRO;


import com.sun.istack.internal.NotNull;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "users")
@NamedNativeQuery(
        name = "User.getUserFromId",
        query = "SELECT * FROM users U WHERE U.user_id = :id", resultClass = User.class
)
public class User {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int userID;

    @Column(name = "registration_date")
    private Date registration_date;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String emailID;

    @Column(name = "role")
    private String role;

    public User() { }

    public User(Date registration_date, String name, String emailID, String role) {
        this.registration_date = registration_date;
        this.name = name;
        this.emailID = emailID;
        this.role = role;
    }

    public int getUserID() {
        return userID;
    }


    public Date getRegistration_date() {
        return registration_date;
    }

    public void setRegistration_date(Date registration_date) {
        this.registration_date = registration_date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailID() {
        return emailID;
    }

    public void setEmailID(String emailID) {
        this.emailID = emailID;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String displayPHR(PatientDetails patientDetails){ return null; }
}
