package com.RRH.Enums;

public enum Categories {
    ALLERGY("allergy"), MEDICATION_ORDER("medication order");

    private String type;

    Categories(String type) {
        this.type = type;
    }

}
