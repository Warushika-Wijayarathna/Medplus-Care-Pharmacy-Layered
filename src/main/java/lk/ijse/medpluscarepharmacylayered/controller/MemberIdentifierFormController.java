package lk.ijse.medpluscarepharmacylayered.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.medpluscarepharmacylayered.bo.BOFactory;
import lk.ijse.medpluscarepharmacylayered.bo.custom.UserBO;

import java.sql.SQLException;

public class MemberIdentifierFormController {
    public JFXTextField userNameTxt;
    public Label userNameLabel;
    public Label passwordLabel;
    public JFXButton logInBtn;
    public JFXTextField showPassword;
    public JFXPasswordField hidePassword;
    public JFXToggleButton showToggleBtn;
    public AnchorPane rootNode;

    UserBO userBO = (UserBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.USER);
    public void initialize(){
        showToggleBtn.setOnAction(this::showToggleBtnClickOnAction);

        showPassword.setVisible(false);

        showPassword.textProperty().addListener((observable, oldValue, newValue) -> {
            hidePassword.setText(newValue);
        });

        Platform.runLater(()->{
            userNameTxt.requestFocus();
        });

    }

    @FXML
    public void onUserNameOnKeyPressed(KeyEvent keyEvent) {
        if ((keyEvent.getCode()== KeyCode.ENTER)&&!showToggleBtn.isSelected()) {
            hidePassword.requestFocus();
        } else if ((keyEvent.getCode()== KeyCode.ENTER)&&showToggleBtn.isSelected()) {
            showPassword.requestFocus();
        }
    }

    @FXML
    public void onPasswordKeyPressed(KeyEvent keyEvent) {
        if (keyEvent.getCode()== KeyCode.ENTER) {
            LogInBtnClickOnAction(new ActionEvent());

        }
    }
    @FXML
    public void LogInBtnClickOnAction(ActionEvent actionEvent) {
        String username=userNameTxt.getText();
        String password=hidePassword.getText();


        boolean isUserVerified = false;
        try {
            isUserVerified = userBO.checkUser(username, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        if (isUserVerified) {
            navigateToDashboard();
        } else {
            new Alert(Alert.AlertType.ERROR,"Username or password is incorrect").showAndWait()
                    .ifPresent(response -> {
                        if (response == ButtonType.OK) {
                            userNameTxt.clear();
                            hidePassword.clear();
                            showPassword.clear();
                            showToggleBtn.setSelected(false);
                            hidePassword.setVisible(true);
                            userNameTxt.requestFocus();
                        }
                    });
        }
    }


    private void navigateToDashboard()  {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Dashboard.fxml"));
            Parent rootNode = loader.load();
            Scene scene = new Scene(rootNode);

            DashBoardController controller = loader.getController();
            controller.setUsername(userNameTxt.getText());

            Stage stage = (Stage)this.rootNode.getScene().getWindow();
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.setTitle("Dashboard Form");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void showToggleBtnClickOnAction(ActionEvent actionEvent) {
        if (showToggleBtn.isSelected()) {
            showPassword.requestFocus();
            showPassword.setText(hidePassword.getText());
            showPassword.setVisible(true);
            hidePassword.setVisible(false);
            return;
        }
        hidePassword.setText(showPassword.getText());
        hidePassword.setVisible(true);
        showPassword.setVisible(false);
    }

    public void hidePasswordOnAction(KeyEvent keyEvent) {
        showPassword.setVisible(false);
    }

    public void onShowPasswordKeyPressed(KeyEvent keyEvent) {
        if (keyEvent.getCode()== KeyCode.ENTER) {
            LogInBtnClickOnAction(new ActionEvent());

        }
    }
}
