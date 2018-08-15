package com.RRH.EntityRO;

import com.RRH.Interfaces.MetaCategory;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Date;




@Embeddable
public class CustomAllergyIntolerance implements Serializable,MetaCategory {
    // Using only Primitive or Wrapper classes as members in all Meta Categories as extra tables will need to be created for nested tables.
    // Avoiding it for the purpose of simplicity here.

    private String category;

    private String criticality;

    private Date last_occurrence_date;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCriticality() {
        return criticality;
    }

    public void setCriticality(String criticality) {
        this.criticality = criticality;
    }

    public Date getLastOccurrence() {
        return last_occurrence_date;
    }

    public void setLastOccurrence(Date last_occurrence_date) {
        this.last_occurrence_date = last_occurrence_date;
    }

    public String toString() {
        return "\nCategory: " + category + ", Criticality: " + criticality
                + ", Last occurrence: " + (last_occurrence_date != null ? last_occurrence_date.toString() : null);
    }
}
