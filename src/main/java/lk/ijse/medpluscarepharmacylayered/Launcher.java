package lk.ijse.medpluscarepharmacylayered;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.medpluscarepharmacylayered.controller.TempFormController;


public class Launcher extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        AnchorPane rootPane = FXMLLoader.load(this.getClass().getResource("/view/member_identifier_form.fxml"));


        Scene scene = new Scene(rootPane);
        scene.getStylesheets().add(getClass().getResource("/css/styles.css").toExternalForm());

        stage.setScene(scene);
        stage.setTitle("Member Identifier");
        stage.centerOnScreen();

        TempFormController tempFormController = new TempFormController();

        stage.setOnCloseRequest(event -> {
            tempFormController.stopReading();
        });

        stage.show();    }
}
