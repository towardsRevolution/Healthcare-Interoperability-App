package com.RRH;

import ca.uhn.fhir.model.dstu2.resource.*;
import com.RRH.EntityRO.*;

public final class CustomHAPIClasses {

    // Here we choose the relevant content we need from each of the three HAPI classes.

    public static CustomPatient getCustomPatient(Patient patient) {
        CustomPatient customPatient = null;
        if(patient != null) {
            customPatient = new CustomPatient();
            customPatient.setActive(patient.getActive());
            customPatient.setDateOfBirth(patient.getBirthDate());
            customPatient.setGender(patient.getGender());
        }
        return customPatient;
    }

    public static CustomAllergyIntolerance getCustomAllergyIntolerance(AllergyIntolerance allergyIntolerance) {
        CustomAllergyIntolerance customAllergyIntolerance = null;
        if(allergyIntolerance != null) {
            customAllergyIntolerance = new CustomAllergyIntolerance();
            customAllergyIntolerance.setCategory(allergyIntolerance.getCategory());
            customAllergyIntolerance.setCriticality(allergyIntolerance.getCriticality());
            customAllergyIntolerance.setLastOccurrence(allergyIntolerance.getLastOccurence());
        }
        return customAllergyIntolerance;
    }

    public static CustomCondition getCustomCondition(Condition condition) {
        CustomCondition customCondition = null;
        if(condition != null) {
            customCondition = new CustomCondition();
            customCondition.setClinicalStatus(condition.getClinicalStatus());
            customCondition.setDateRecorded(condition.getDateRecorded());
            customCondition.setNotes(condition.getNotes());
        }
        return customCondition;
    }

    public static CustomMedicationOrder getCustomMedicationOrder(MedicationOrder medicationOrder) {
        CustomMedicationOrder customMedicationOrder = null;
        if(medicationOrder != null) {
            customMedicationOrder = new CustomMedicationOrder();
            customMedicationOrder.setStatus(medicationOrder.getStatus());
            customMedicationOrder.setDateWritten(medicationOrder.getDateWritten());
            customMedicationOrder.setDateEnded(medicationOrder.getDateEnded());
        }
        return customMedicationOrder;
    }

    public static CustomFamilyMemberHistory getCustomFamilyMemberHistory(FamilyMemberHistory familyMemberHistory) {
        CustomFamilyMemberHistory customFamilyMemberHistory = null;
        if(familyMemberHistory != null) {
            customFamilyMemberHistory = new CustomFamilyMemberHistory();
            customFamilyMemberHistory.setGender(familyMemberHistory.getGender());
            customFamilyMemberHistory.setLast_recorded_date(familyMemberHistory.getDate());
            customFamilyMemberHistory.setName(familyMemberHistory.getName());
            customFamilyMemberHistory.setStatus(familyMemberHistory.getStatus());
        }
        return customFamilyMemberHistory;
    }

    public static CustomDevice getCustomDevice(Device device) {
        CustomDevice customDevice = null;
        if(device != null) {
            customDevice = new CustomDevice();
            customDevice.setModel(device.getModel());
            customDevice.setVersion(device.getVersion());
            customDevice.setManufacturer(device.getManufacturer());
        }
        return customDevice;
    }

    public static CustomCarePlan getCustomCarePlan(CarePlan carePlan) {
        CustomCarePlan customCarePlan = null;
        if(carePlan != null) {
            customCarePlan = new CustomCarePlan();
            customCarePlan.setDescription(carePlan.getDescription());
            customCarePlan.setLast_modified_date(carePlan.getModified());
            customCarePlan.setStatus(carePlan.getStatus());
        }
        return customCarePlan;
    }
}
