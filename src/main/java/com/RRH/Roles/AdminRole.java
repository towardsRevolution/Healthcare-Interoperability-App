package com.RRH.Roles;

import com.RRH.EntityRO.PatientDetails;
import com.RRH.EntityRO.User;

import java.util.Date;

public class AdminRole extends User {

    public AdminRole(Date registration_date, String name, String emailID, String role){
        super(registration_date,name,emailID,role);
    }

    @Override
    public String displayPHR(PatientDetails patientDetails) {
        return patientDetails.toString();
    }
}
