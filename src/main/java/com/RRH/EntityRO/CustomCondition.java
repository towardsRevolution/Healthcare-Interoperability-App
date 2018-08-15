package com.RRH.EntityRO;

import com.RRH.Interfaces.MetaCategory;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.*;


@Embeddable
public class CustomCondition implements Serializable, MetaCategory {

    // Using only Primitive or Wrapper classes as members in all Meta Categories as extra tables will need to be created for nested tables.
    // Avoiding it for the purpose of simplicity here.

    private Date date_recorded;

    private String clinical_status;

    private String notes;

    public Date getDateRecorded() {
        return date_recorded;
    }

    public void setDateRecorded(Date date_recorded) {
        this.date_recorded = date_recorded;
    }

    public String getClinicalStatus() {
        return clinical_status;
    }

    public void setClinicalStatus(String clinical_status) {
        this.clinical_status = clinical_status;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String toString() {
        return "\nDate recorded: " + (date_recorded != null ? date_recorded.toString() : null) + ", Clinical status: " + clinical_status
                + ", Notes: " + notes;
    }
}
