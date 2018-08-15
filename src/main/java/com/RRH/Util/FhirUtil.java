package com.RRH.Util;

import ca.uhn.fhir.context.FhirContext;
import com.RRH.DAC.FhirPersistDAC;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public final class FhirUtil {
    public static final String SERVER_BASE_URL = "https://open-ic.epic.com/FHIR/api/FHIR/DSTU2/";

    public static final String SERVER_PACKAGE_PATH = "ca.uhn.fhir.Entity.dstu2.resource";

    public static String[] RESOURCES = {"AllergyIntolerance","MedicationOrder","Condition",
            "FamilyMemberHistory","Device","CarePlan"}; // "Procedure","Immunization","DiagnosticReport","Immunization","Procedure","Device",""

    public static String[] patients = {"Jason Argonaut","Jessica Argonaut","Flapjacks Ragsdale","Waffles Ragsdale","Emily Williams"};

    private static EntityManagerFactory entityManagerFactory;

    private FhirContext ctx;

    private FhirUtil() {
        entityManagerFactory = Persistence.createEntityManagerFactory(FhirPersistDAC.PERSISTENCE);
        ctx = FhirContext.forDstu2();
        ctx.getRestfulClientFactory().setConnectionRequestTimeout(70*1000);
        ctx.getRestfulClientFactory().setSocketTimeout(70*1000);
    }


    private static class FhirUtil_Holder {
        private static final FhirUtil INSTANCE = new FhirUtil();
    }

     public static FhirUtil getInstance() {
        return FhirUtil_Holder.INSTANCE;
    }

    public FhirContext getCtx() {
        return ctx;
    }

    public EntityManagerFactory getEntityManagerFactory(){
        return entityManagerFactory;
    }
}
