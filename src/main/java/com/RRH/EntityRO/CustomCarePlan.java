package com.RRH.EntityRO;

import com.RRH.Interfaces.MetaCategory;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.*;


@Embeddable
public class CustomCarePlan implements Serializable, MetaCategory {

    private String description;

    private Date last_modified_date;

    private String status;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getLast_modified_date() {
        return last_modified_date;
    }

    public void setLast_modified_date(Date last_modified_date) {
        this.last_modified_date = last_modified_date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String toString() {
        return "\nDescription: " + description + ", Status: " + status
                + ", Last Modified: " + (last_modified_date != null ? last_modified_date.toString() : null) ;
    }
}
