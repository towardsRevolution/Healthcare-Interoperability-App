package com.RRH.Roles;

import com.RRH.EntityRO.PatientDetails;
import com.RRH.EntityRO.User;

import java.util.Date;

public class UserRole extends User {

    public UserRole(Date registration_date, String name, String emailID, String role){
        super(registration_date,name,emailID,role);
    }

    @Override
    public String displayPHR(PatientDetails patientDetails) {
        String result = "\n\n PatientID : " + patientDetails.getPatientID();
        result += "\n\n Patient: " + patientDetails.getPatient();
        result += "\n\n MedicationOrder : " + patientDetails.getMedicationOrders();

        return result;
    }

}
