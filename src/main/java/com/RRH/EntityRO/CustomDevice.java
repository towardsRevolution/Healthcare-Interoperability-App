package com.RRH.EntityRO;

import com.RRH.Interfaces.MetaCategory;

import javax.persistence.Embeddable;

import java.io.Serializable;


@Embeddable
public class CustomDevice implements Serializable, MetaCategory {

    private String model;

    private String version;

    private String manufacturer;

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String toString() {
        return "\nModel: " + model + ", Version: " + version
                + ", Manufacturer: " + manufacturer;
    }
}
