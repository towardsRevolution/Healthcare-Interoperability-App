package com.RRH.EntityRO;

import com.RRH.Interfaces.MetaCategory;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.*;


@Embeddable
public class CustomMedicationOrder implements Serializable, MetaCategory {

    // Using only Primitive or Wrapper classes as members in all Meta Categories as extra tables will need to be created for nested tables.
    // Avoiding it for the purpose of simplicity here.

    private Date date_written;

    private Date date_ended;

    private String status;

    public Date getDateWritten() {
        return date_written;
    }

    public Date getDateEnded() {
        return date_ended;
    }

    public String getStatus() {
        return status;
    }

    public void setDateWritten(Date date_written) {
        this.date_written = date_written;
    }

    public void setDateEnded(Date date_ended) {
        this.date_ended = date_ended;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String toString() {
        return "\nDate written: " + (date_written != null ? date_written.toString() : null) +
                ", Date ended: " + (date_ended != null ? date_ended.toString() : null)
                + ", Status: " + status ;
    }
}
