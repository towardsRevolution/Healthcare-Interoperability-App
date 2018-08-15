package com.RRH.EntityRO;

import ca.uhn.fhir.model.dstu2.resource.*;
import com.RRH.CustomHAPIClasses;
import com.RRH.Util.FhirUtil;
import org.hl7.fhir.instance.model.api.IBaseResource;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "patient_details")
@NamedNativeQueries({
        @NamedNativeQuery(
                name = "PatientDetails.count",
                query = "SELECT COUNT(*) FROM patient_details pd WHERE pd.patient_id = :id"
        ),
        @NamedNativeQuery(
                name = "PatientDetails.getPHR",
                query = "SELECT * FROM patient_details pd WHERE pd.patient_id = :id", resultClass = PatientDetails.class
        ),
        @NamedNativeQuery(
                name = "PatientDetails.findAll",
                query = "SELECT * FROM patient_details pd", resultClass = PatientDetails.class
        )
})
/*NOTE: (fetch = FetchType.EAGER) helps me cache the element collections when PatientDetails class is loaded. It helps in
        advanced search as it requires joining patient details with the respective element collections. In-memory joins
        are very effective then.
        However, if not for such a case, (fetch = FetchType.LAZY) is deemed as a better choice.
*/
public class PatientDetails implements Serializable {

    @Id
    @Column(name = "patient_id")
    private String patientID;

    @Embedded
    private CustomPatient patient;

    // NOTE: "FetchType.EAGER" could cause performance lag if the collections were to become too big.
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "patient_allergies", joinColumns = @JoinColumn(name = "p_id", referencedColumnName = "patient_id"))
    private Set<CustomAllergyIntolerance> allergies;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "patient_medication_orders", joinColumns = @JoinColumn(name = "p_id", referencedColumnName = "patient_id"))
    private Set<CustomMedicationOrder> medicationOrders;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "patient_conditions", joinColumns = @JoinColumn(name = "p_id", referencedColumnName = "patient_id"))
    private Set<CustomCondition> conditions;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "patient_family_history", joinColumns = @JoinColumn(name = "p_id", referencedColumnName = "patient_id"))
    private Set<CustomFamilyMemberHistory> familyHistory;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "patient_devices", joinColumns = @JoinColumn(name = "p_id", referencedColumnName = "patient_id"))
    private Set<CustomDevice> devices;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "patient_care_plans", joinColumns = @JoinColumn(name = "p_id", referencedColumnName = "patient_id"))
    private Set<com.RRH.EntityRO.CustomCarePlan> plans;


    public String getPatientID() {
        return patientID;
    }

    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }

    public CustomPatient getPatient() {
        return patient;
    }

    public void setPatient(CustomPatient patient) {
        this.patient = patient;
    }

    public PatientDetails(String patientID, CustomPatient patient) {
        this.patientID = patientID;
        this.patient = patient;
        allergies = new HashSet<>();
        medicationOrders = new HashSet<>();
        conditions = new HashSet<>();
        familyHistory = new HashSet<>();
        devices = new HashSet<>();
        plans = new HashSet<>();
    }

    public PatientDetails() {

    }

    public Set<CustomAllergyIntolerance> getAllergies() {
        return allergies;
    }

    public void setAllergies(Set<CustomAllergyIntolerance> allergies) {
        this.allergies = allergies;
    }

    public Set<CustomMedicationOrder> getMedicationOrders() {
        return medicationOrders;
    }

    public void setMedicationOrders(Set<CustomMedicationOrder> medicationOrders) {
        this.medicationOrders = medicationOrders;
    }

    public Set<CustomCondition> getConditions() {
        return conditions;
    }

    public void setConditions(Set<CustomCondition> conditions) {
        this.conditions = conditions;
    }

    public Set<CustomFamilyMemberHistory> getFamilyHistory() {
        return familyHistory;
    }

    public void setFamilyHistory(Set<CustomFamilyMemberHistory> familyHistory) {
        this.familyHistory = familyHistory;
    }

    public Set<CustomDevice> getDevices() {
        return devices;
    }

    public void setDevices(Set<CustomDevice> devices) {
        this.devices = devices;
    }

    public Set<com.RRH.EntityRO.CustomCarePlan> getPlans() {
        return plans;
    }

    public void setPlans(Set<com.RRH.EntityRO.CustomCarePlan> plans) {
        this.plans = plans;
    }


    public void add(IBaseResource resource) throws Exception {
        String resourceClass = resource.getClass().getName();
        System.out.println("ResourceClass: " + resourceClass);
        switch (resourceClass) {
            case FhirUtil.SERVER_PACKAGE_PATH + ".AllergyIntolerance" : {
                CustomAllergyIntolerance customAllergyIntolerance = CustomHAPIClasses.getCustomAllergyIntolerance((AllergyIntolerance) resource);
                allergies.add(customAllergyIntolerance);
                break;
            }

            case FhirUtil.SERVER_PACKAGE_PATH + ".MedicationOrder" : {
                CustomMedicationOrder customMedicationOrder = CustomHAPIClasses.getCustomMedicationOrder((MedicationOrder) resource);
                medicationOrders.add(customMedicationOrder);
                break;
            }

            case FhirUtil.SERVER_PACKAGE_PATH + ".Condition" : {
                CustomCondition customCondition = CustomHAPIClasses.getCustomCondition((Condition) resource);
                conditions.add(customCondition);
                break;
            }

            case FhirUtil.SERVER_PACKAGE_PATH + ".FamilyMemberHistory" : {
                CustomFamilyMemberHistory customFamilyMemberHistory = CustomHAPIClasses.getCustomFamilyMemberHistory((FamilyMemberHistory) resource);
                familyHistory.add(customFamilyMemberHistory);
                break;
            }

            case FhirUtil.SERVER_PACKAGE_PATH + ".Device": {
                CustomDevice customDevice = CustomHAPIClasses.getCustomDevice((Device) resource);
                devices.add(customDevice);
                break;
            }

            case FhirUtil.SERVER_PACKAGE_PATH + ".CarePlan": {
                com.RRH.EntityRO.CustomCarePlan customCarePlan = CustomHAPIClasses.getCustomCarePlan((CarePlan) resource);
                plans.add(customCarePlan);
                break;
            }

            default: {
                // ca.uhn.fhir.Entity.dstu2.resource.OperationOutcome (i.e. Empty response)
                break;
            }
        }
    }

    public String toString() {

        String result = "\n\n PatientID : " + patientID;
        result += "\n\n Patient: " + patient;
        result += "\n\n AllergyIntolerance: " + allergies;
        result += "\n\n MedicationOrder : " + medicationOrders;
        result += "\n\n Condition : " + conditions;
        result += "\n\n FamilyMemberHistory : " + familyHistory;
        result += "\n\n Device : " + devices;
        result += "\n\n Care Plan : " + plans;
        return result;
    }

    public String getMetaCount() {
        String result = "\n\n PatientID : " + patientID;
        result += "\n\n Patient: " + patient;
        result += "\n\n # of allergies: " + allergies.size();
        result += "\n\n # of medication orders: " + medicationOrders.size();
        result += "\n\n # of conditions : " + conditions.size();
        result += "\n\n # of family history cases : " + familyHistory.size();
        result += "\n\n # of devices : " + devices.size();
        result += "\n\n # of care plans : " + plans.size();
        return result;
    }
}