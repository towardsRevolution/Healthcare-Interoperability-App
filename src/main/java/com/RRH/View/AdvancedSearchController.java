package com.RRH.View;

import com.RRH.AppMain;
import com.RRH.DAC.CategoricalSearchDAC;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class AdvancedSearchController {

    @FXML
    private TextField patientID;

    @FXML
    private TextField category;

    @FXML
    private TextField parameters;

    @FXML
    private TextArea resultTextArea;


    @FXML
    public void getRefinedResults() throws Exception {
        String ID = this.patientID.getText();
        String category = this.category.getText();
        String parameter = this.parameters.getText();
        String refinedRecord = CategoricalSearchDAC.performRefinedSearch(ID, category,parameter);
        resultTextArea.setEditable(true);
        resultTextArea.setText(refinedRecord);
    }

    @FXML
    public void goBackToSearch() throws Exception {
        AppMain.loadHomeItems();
    }

    @FXML
    public void goBackToMainView() throws Exception {
        AppMain.loadMainItems();
    }



}
