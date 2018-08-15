package com.RRH.View;

import com.RRH.AppMain;
import javafx.fxml.FXML;

public class MainViewController {

    @FXML
    public void performLogin() throws Exception {
        AppMain.loadHomeItems();
    }

}
