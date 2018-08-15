package com.RRH.DAC;

import ca.uhn.fhir.model.dstu2.resource.Bundle;
import ca.uhn.fhir.model.dstu2.resource.Patient;
import ca.uhn.fhir.rest.client.api.IGenericClient;
import com.RRH.CustomHAPIClasses;
import com.RRH.EntityRO.*;
import com.RRH.Roles.Role;
import com.RRH.Util.FhirUtil;
import org.hl7.fhir.instance.model.api.IBaseResource;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.criteria.*;
import java.util.*;

// DAC = Data Access Class

public final class FhirPersistDAC {

    public static final String PATIENT_PHR_QUERY = "PatientDetails.getPHR";
    public static final String PATIENT_FINDALL_QUERY = "PatientDetails.findAll";
    public static final String USER_GET_QUERY = "User.getUserFromId";
    public static final String PERSISTENCE = "rrh_persistence";

    private static FhirUtil fhirUtil;

    private IGenericClient client;

    static {
        // Since the EntityManagerFactory and FhirContext, being expensive operations should be initialized only once.
        fhirUtil = FhirUtil.getInstance();
    }


    // Keeps a record of the base PatientDetails object when it is being populated.
    // *** REQUIRED WHEN POPULATING DB. ***
    private static Map<String, PatientDetails> patientHealthRecords;


    public FhirPersistDAC() {
        patientHealthRecords = new HashMap<>(); //<-----  REQUIRED WHEN POPULATING DB.
        client = fhirUtil.getCtx().newRestfulGenericClient(FhirUtil.SERVER_BASE_URL);
    }


    // ****** DB POPULATE OPERATIONS START ****** (REQUIRED WHEN POPULATING DB.)
    public void createPHR(String name) {
        Bundle response;
        String[] splitName = name.split("\\s+");
        String given = splitName[0], family = splitName[1];
        try {
            response = client.search()
                    .forResource(Patient.class)
                    .where(Patient.GIVEN.matches().value(given))
                    .where(Patient.FAMILY.matches().value(family))
                    .returnBundle(Bundle.class)
                    .execute();
            for(Bundle.Entry entry : response.getEntry()) {
                String ID = entry.getResource().getIdElement().getIdPart();
                CustomPatient customPatient = CustomHAPIClasses.getCustomPatient((Patient) entry.getResource());
                PatientDetails patientDetails = new PatientDetails(ID,customPatient);
                patientHealthRecords.put(ID,patientDetails);
            }
        } catch (Exception e) {
            System.out.println("Error Occurred: " + e);
        }
    }


    public Bundle searchResource(String resourceClass, String patientID) {
        Bundle response = null;
        try {
            String queryURL = FhirUtil.SERVER_BASE_URL + resourceClass + "?patient=" + patientID;
            response = client.search()
                    .byUrl(queryURL)
                    .returnBundle(Bundle.class)
                    .execute();

        } catch (Exception e) {
            System.out.println("An error occurred trying to search:");
            e.printStackTrace();
        }
        return response;
    }

    public void addResourceToPatientDetails(Bundle response, String patientID) throws Exception {
        PatientDetails patientDetails=null;
        for(Bundle.Entry entry : response.getEntry()) {
            IBaseResource resource = entry.getResource();
            patientDetails = patientHealthRecords.get(patientID);
            patientDetails.add(resource);
        }
        performDBUpdate(patientDetails);
    }

    public void performDBUpdate(PatientDetails patientDetails) {
        if(patientDetails != null) {
            EntityManagerFactory entityManagerFactory = fhirUtil.getEntityManagerFactory();
            EntityManager entityManager = entityManagerFactory.createEntityManager();

            entityManager.getTransaction().begin();
            entityManager.persist(patientDetails);
            entityManager.getTransaction().commit();

            entityManager.close();
        }
    }

    public void createUsers() {
        User admin = new User(new Date(),"Test Admin","testadmin@gmail.com","ROLE_ADMIN");
        User user = new User(new Date(),"Test User", "testuser@gmail.com","ROLE_USER");
        EntityManagerFactory entityManagerFactory = fhirUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(admin);
        entityManager.persist(user);
        entityManager.getTransaction().commit();

        entityManager.close();
    }

    public void populateDB() throws Exception {
        for(String patient : FhirUtil.patients)
            createPHR(patient);

        System.out.println("\n PHR map created: \n" + patientHealthRecords.size());

        Iterator iterator = patientHealthRecords.entrySet().iterator();
        while(iterator.hasNext()) {
            Map.Entry<String, PatientDetails> pairs = (Map.Entry<String, PatientDetails>) iterator.next();
            String patientID = pairs.getKey();
            PatientDetails patientDetails = pairs.getValue();
            System.out.println("\n Current patient: " + patientID);
            for(String resource : FhirUtil.RESOURCES) {
                Bundle response = searchResource(resource,patientID);
                addResourceToPatientDetails(response,patientID);
            }
            System.out.println("Populating DB. Transaction started..");
            performDBUpdate(patientDetails); // One time after all resource sets have been populated for a patient.
            System.out.println("Transaction committed!!");
        }
    }

    // ****** DB POPULATE OPERATIONS END ******


    // ----------------------------------------------------// ----------------------------------------------------


    // ****** SEARCH QUERIES START ******

    public PatientDetails getSinglePatientDetails(String searchString, EntityManagerFactory entityManagerFactory) {
        String ID = searchString.split(",")[0].trim();

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Query query_record = entityManager.createNamedQuery(PATIENT_PHR_QUERY);
        query_record.setParameter("id",ID);

        long start = System.currentTimeMillis();
        PatientDetails healthRecord = (PatientDetails) query_record.getSingleResult();
        System.out.println("\n** PHR generated in --> " + (System.currentTimeMillis()-start) + " msec");

        entityManager.close();
        return healthRecord;
    }

    public User getContextUser(String searchString, EntityManagerFactory entityManagerFactory) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        String userID = searchString.split(",")[1].trim();

        Query query = entityManager.createNamedQuery(USER_GET_QUERY);
        query.setParameter("id",userID);

        User profileUser = null;
        User user = (User) query.getSingleResult();
        if(user != null)
            profileUser = Role.GET_INSTANCE(user.getRole(),user);
        entityManager.close();
        return profileUser;
    }

    public String generatePHR_fromProfile(String searchString) {
        EntityManagerFactory entityManagerFactory = fhirUtil.getEntityManagerFactory();
        PatientDetails healthRecord = getSinglePatientDetails(searchString, entityManagerFactory);
        User profileUser = getContextUser(searchString,entityManagerFactory);
        return profileUser != null ? profileUser.displayPHR(healthRecord) : null;

    }

    public String getAllPatients() {
        EntityManagerFactory entityManagerFactory = fhirUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createNamedQuery(PATIENT_FINDALL_QUERY);
        List<PatientDetails> allPatients = query.getResultList();
        return allPatients.toString();
    }


    public String generatePHR_metaCount(String searchString) {
        EntityManagerFactory entityManagerFactory = fhirUtil.getEntityManagerFactory();
        PatientDetails healthRecord = getSinglePatientDetails(searchString, entityManagerFactory);
        return healthRecord.getMetaCount();

    }

    public String refinedSearch_Allergy(String ID, String criticality) {
        EntityManagerFactory entityManagerFactory = fhirUtil.getEntityManagerFactory();
        EntityManager em = entityManagerFactory.createEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<CustomAllergyIntolerance> ca = cb.createQuery(CustomAllergyIntolerance.class);
        Root<PatientDetails> patientDetailsRoot = ca.from(PatientDetails.class);
        long start = System.currentTimeMillis();
        Join<PatientDetails,CustomAllergyIntolerance> allergies = patientDetailsRoot.join("allergies");
        ca.select(allergies).where(cb.and(cb.equal(patientDetailsRoot.get("patientID"),ID),cb.equal(allergies.get("criticality"),criticality)));
        List<CustomAllergyIntolerance> allAllergies = em.createQuery(ca).getResultList();
        System.out.println("Allergies generated in: " + (System.currentTimeMillis() - start) + " msec");
        return allAllergies.toString();
    }

    public String refinedSearch_MedicationOrder(String ID, Date date_given) {
        EntityManagerFactory entityManagerFactory = fhirUtil.getEntityManagerFactory();
        EntityManager em = entityManagerFactory.createEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<CustomMedicationOrder> ca = cb.createQuery(CustomMedicationOrder.class);
        Root<PatientDetails> patientDetailsRoot = ca.from(PatientDetails.class);
        long start = System.currentTimeMillis();
        Join<PatientDetails,CustomMedicationOrder> medicationOrders = patientDetailsRoot.join("medicationOrders");
        Path<Date> datesFetched = medicationOrders.get("date_written");
        ca.select(medicationOrders).where(cb.and(cb.equal(patientDetailsRoot.get("patientID"),ID),cb.greaterThanOrEqualTo(datesFetched,date_given)));
        List<CustomMedicationOrder> allMedicationOrders = em.createQuery(ca).getResultList();
        System.out.println("MedicationOrders generated in: " + (System.currentTimeMillis() - start) + " msec");
        return allMedicationOrders.toString();
    }

    // ****** SEARCH QUERIES END ******
}

