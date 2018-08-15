package com.RRH.EntityRO;


import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.*;

@Embeddable
public class CustomPatient implements Serializable {

    // Using only Primitive or Wrapper classes as members in all Meta Categories as extra tables will need to be created for nested tables.
    // Avoiding it for the purpose of simplicity here.

    private Date date_of_birth;

    private String gender;

    private boolean active;

    public Date getDateOfBirth() {
        return date_of_birth;
    }

    public void setDateOfBirth(Date date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public String getGender() {
        return this.gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public boolean getActive() {
        return this.active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String toString() {
        return "\nDate of birth: " + (date_of_birth != null ? date_of_birth.toString() : null) + ", Gender: " + gender
                + ", Active: " + active ;
    }
}
