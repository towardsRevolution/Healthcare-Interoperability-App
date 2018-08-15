package com.RRH.EntityRO;

import com.RRH.Interfaces.MetaCategory;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.*;


@Embeddable
public class CustomFamilyMemberHistory implements Serializable, MetaCategory {

    private String name;

    private String gender;

    private String status;

    private Date last_recorded_date;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getLast_recorded_date() {
        return last_recorded_date;
    }

    public void setLast_recorded_date(Date last_recorded_date) {
        this.last_recorded_date = last_recorded_date;
    }

    public String toString() {
        return "\nName: " + name + ", Gender: " + gender
                + ", Status: " + status + ", Last recorded: " + (last_recorded_date != null ? last_recorded_date.toString() : null);
    }
}
