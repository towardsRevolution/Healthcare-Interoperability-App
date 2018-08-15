package com.RRH;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class AppMain extends Application {

    private static Stage mainStage;
    private static AnchorPane mainLayout;


    public AppMain() {
        super();
    }

    @Override
    public void init() throws Exception {
        super.init();
    }

    public static void loadAdvancedSearchItems() throws Exception {
        try {
            Parent newWindow = FXMLLoader.load(AppMain.class.getResource("/fxml/AdvancedSearchView.fxml"));
            Scene newWindowScene = new Scene(newWindow);
            mainStage.setScene(newWindowScene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void loadMainItems() throws Exception {
        try {
            Parent newWindow = FXMLLoader.load(AppMain.class.getResource("/fxml/MainView.fxml"));
            Scene newWindowScene = new Scene(newWindow);
            mainStage.setScene(newWindowScene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start(Stage ms) throws Exception {
        mainStage = ms;
        mainStage.setTitle("Interoperability App");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/fxml/MainView.fxml"));
        mainLayout = fxmlLoader.load();
        Scene scene = new Scene(mainLayout);
        mainStage.setScene(scene);
        mainStage.show();
    }

    public static void displayErrorPopUp(String message) {
        Stage popup = new Stage();
        popup.initModality(Modality.APPLICATION_MODAL);
        popup.initOwner(mainStage);
        VBox popupBox = new VBox(15);
        popupBox.getChildren().add(new Text(message));
        Scene popupScene = new Scene(popupBox,200,100);
        popup.setScene(popupScene);
        popup.show();
    }

    public static void loadHomeItems() throws Exception {
        try {
            Parent newWindow = FXMLLoader.load(AppMain.class.getResource("/fxml/HomeView.fxml"));
            Scene newWindowScene = new Scene(newWindow);
            mainStage.setScene(newWindowScene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void stop() throws Exception {
        super.stop();
    }


    public static void main(String[] args) {
        launch(args);
    }
}