package com.RRH.View;

import com.RRH.AppMain;
import com.RRH.DAC.FhirPersistDAC;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class HomeController {

    private static final String ROLE_ADMIN = "1";

    private String userProfile;

    @FXML
    private TextField searchQuery;

    @FXML
    private Tab resultTab;

    @FXML
    private Tab resultMetaPHRTab;


    public String getUserProfile() {
        return this.userProfile;
    }

    public void setUserProfile(String profile) {
        this.userProfile = profile;
    }

    public static TextArea getTextArea(String text){
        TextArea textArea = new TextArea(text);
        textArea.setScrollTop(Double.MAX_VALUE);
        textArea.setScrollLeft(Double.MAX_VALUE);
        textArea.setEditable(false);
        return textArea;
    }

    @FXML
    public void takeToAdvancedSearch() throws Exception {
        if(userProfile == null || userProfile.equals(ROLE_ADMIN))
            AppMain.loadAdvancedSearchItems();
        else
            AppMain.displayErrorPopUp("ERROR:\nYOU DON'T HAVE PERMISSION!");
    }


    @FXML
    public void getPatientPHR() {
        // Initiating a new client by instantiating "FhirPersistDAC".
        FhirPersistDAC fhirPersistDAC = new FhirPersistDAC();
        String searchString = searchQuery.getText();
        setUserProfile(searchString.split(",")[1]);
        String PHR = fhirPersistDAC.generatePHR_fromProfile(searchString);
        TextArea textArea = getTextArea(PHR);
        resultTab.setContent(textArea);

    }

    @FXML
    public void getPatientMetaCount() {
        // Initiating a new client by instantiating "FhirPersistDAC".
        FhirPersistDAC fhirPersistDAC = new FhirPersistDAC();
        String searchString = searchQuery.getText();
        String PHR = fhirPersistDAC.generatePHR_metaCount(searchString);
        TextArea textArea = getTextArea(PHR);
        resultMetaPHRTab.setContent(textArea);

    }

    @FXML
    public void getAllPatients() {
        FhirPersistDAC fhirPersistDAC = new FhirPersistDAC();
        String allPatients = fhirPersistDAC.getAllPatients();
        TextArea textArea = getTextArea(allPatients);
        resultTab.setContent(textArea);
    }

    @FXML
    public void makePHREditable() {
        if(resultTab.isSelected() && userProfile.equals(ROLE_ADMIN)) {
            TextArea textArea = (TextArea) resultTab.getContent();
            textArea.setEditable(true);
        } else {
            AppMain.displayErrorPopUp("ERROR:\nYOU DON'T HAVE PERMISSION!");
        }
    }


    @FXML
    public void goBackToMainView() throws Exception {
        AppMain.loadMainItems();
    }

}
