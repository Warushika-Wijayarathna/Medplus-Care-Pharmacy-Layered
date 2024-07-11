package lk.ijse.medpluscarepharmacylayered.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import lk.ijse.medpluscarepharmacylayered.bo.BOFactory;
import lk.ijse.medpluscarepharmacylayered.bo.custom.CustomerBO;
import lk.ijse.medpluscarepharmacylayered.bo.custom.PrescriptionBO;
import lk.ijse.medpluscarepharmacylayered.bo.custom.TestBO;
import lk.ijse.medpluscarepharmacylayered.dto.TestDTO;
import lk.ijse.medpluscarepharmacylayered.util.Regex;
import lk.ijse.medpluscarepharmacylayered.util.TextField;
import lk.ijse.medpluscarepharmacylayered.view.tm.PrescTm;
import lk.ijse.medpluscarepharmacylayered.view.tm.SmallSupplierTm;
import lk.ijse.medpluscarepharmacylayered.view.tm.SmallTestTm;
import lk.ijse.medpluscarepharmacylayered.dto.PrescriptionDTO;


import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PrescFormController {

    public JFXComboBox testComBox;
    public TableColumn<?,?> colPrescId;
    public TableColumn<?,?> colCustomer;
    public TableColumn<?,?> colPatient;
    public TableColumn<?,?> colAge;
    public TableColumn<?,?> colMedicalOfficer;
    public TableColumn<?,?> colContext;
    public TableColumn<?,?> colDuration;
    public TableColumn<?,?> colDate;
    public TableColumn<?,?> colUpdate;
    public TableColumn<?,?> colDelete;
    public TableView<PrescTm> prescTable;
    public JFXTextField patientTxt;
    public JFXTextField ageTxt;
    public JFXTextField medicalOfficerTxt;
    public JFXButton addBtn;
    public JFXTextField searchBar;
    public TableView<SmallTestTm> testCart;
    public TableColumn<?,?> colRemove;
    public JFXComboBox custCombox;
    public JFXTextField durationTxt;
    public DatePicker datePicker;
    public JFXTextArea contextText;
    public TableColumn<?,?> colTestId;
    public TableColumn<?,?> colDescName;
    ObservableList<PrescTm> observableList = FXCollections.observableArrayList();
    public PrescTm selectedPresc;
    private ObservableList<String> allTestNames;
    private ObservableList<String> allCustNames;
    ObservableList<SmallTestTm> obList = FXCollections.observableArrayList();
    public TableColumn<PrescTm, List<JFXButton>> colAction;
    CustomerBO customerBO = (CustomerBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.CUSTOMER);
    TestBO testBO = (TestBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.TEST);
    PrescriptionBO prescriptionBO = (PrescriptionBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.PRESCRIPTION);
    public String findCustomerId;


    public void initialize() {

        setCellValueFactory();
        loadAllPrescriptions();
        loadTestComBox();
        loadCustComBox();

        Platform.runLater(() -> {
            custCombox.requestFocus();

            custCombox.setOnKeyPressed(event -> {
                if (event.getCode() == KeyCode.ENTER) {
                    patientTxt.requestFocus();
                }
            });

            patientTxt.setOnKeyPressed(event -> {
                if (event.getCode() == KeyCode.ENTER) {
                    ageTxt.requestFocus();
                }
            });

            ageTxt.setOnKeyPressed(event -> {
                if (event.getCode() == KeyCode.ENTER) {
                    medicalOfficerTxt.requestFocus();
                }
            });

            medicalOfficerTxt.setOnKeyPressed(event -> {
                if (event.getCode() == KeyCode.ENTER) {
                    contextText.requestFocus();
                }
            });

            durationTxt.setOnKeyPressed(event -> {
                if (event.getCode() == KeyCode.ENTER) {
                    datePicker.requestFocus();
                }
            });

            datePicker.setOnKeyPressed(event -> {
                if (event.getCode() == KeyCode.ENTER) {
                    testComBox.requestFocus();
                }
            });

            testComBox.setOnKeyPressed(event -> {
                if (event.getCode() == KeyCode.ENTER) {
                    addBtn.requestFocus();
                }
            });

        });

    }

    private void loadCustComBox() {
        try {
            allCustNames = FXCollections.observableArrayList(customerBO.getAllCustNames());
            custCombox.setItems(allCustNames);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void loadTestComBox() {
        try {
            allTestNames = FXCollections.observableArrayList(testBO.getAllTestNames());
            testComBox.setItems(allTestNames);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    private void loadAllPrescriptions() {
        try {
            List<PrescriptionDTO> prescriptions = prescriptionBO.getAllPrescriptions();
            observableList.clear();

            for(PrescriptionDTO prescription : prescriptions){
                ImageView updateIcon = new ImageView(new Image(getClass().getResourceAsStream("/icon/Untitled design (44).png")));
                updateIcon.setFitWidth(20);
                updateIcon.setFitHeight(20);

                JFXButton updateButton = new JFXButton();
                updateButton.setGraphic(updateIcon);
                updateButton.setOnAction(event -> handleUpdatePresc(prescription));

                ImageView deleteIcon = new ImageView(new Image(getClass().getResourceAsStream("/icon/Untitled design (43).png")));
                deleteIcon.setFitWidth(20);
                deleteIcon.setFitHeight(20);

                JFXButton deleteButton = new JFXButton();
                deleteButton.setGraphic(deleteIcon);
                deleteButton.setOnAction(event -> handleDeletePresc(prescription));

                List<JFXButton> actionBtns = new ArrayList<>();
                actionBtns.add(updateButton);
                actionBtns.add(deleteButton);

                PrescTm prescTm = new PrescTm(
                        prescription.getPrescriptionId(),
                        prescription.getCustomerId(),
                        prescription.getPatientName(),
                        prescription.getAge(),
                        prescription.getMedicalOfficerName(),
                        prescription.getContext(),
                        prescription.getDuration(),
                        prescription.getDate(),
                        actionBtns
                );

                observableList.add(prescTm);
            }
            prescTable.setItems(observableList);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private void handleDeletePresc(PrescriptionDTO prescription) {
        if (prescription != null) {
            try {
                boolean isDeleted = false;

                if (testCart.getItems().isEmpty()) {
                    isDeleted = prescriptionBO.deletePrescEmpty(prescription);
                } else{
                    isDeleted = prescriptionBO.deletePresc(prescription);
                }

                if (isDeleted) {
                    testCart.getItems().clear();
                    clear();
                    loadAllPrescriptions();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Failed to delete prescription.").showAndWait();
                    return;
                }
                new Alert(Alert.AlertType.INFORMATION, "Prescription deleted successfully.").showAndWait();
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, "Failed to delete prescription.").showAndWait();
                throw new RuntimeException(e);
            }
        }else {
            new Alert(Alert.AlertType.ERROR, "Please select a prescription to delete.").showAndWait();
        }
    }

    private void handleUpdatePresc(PrescriptionDTO prescription) {
        if (selectedPresc != null) {
            String selectedCust = custCombox.getEditor().getText().toLowerCase();
            System.out.println("Selected Customer : "+selectedCust);
            try {
                findCustomerId = customerBO.searchCustomerByName(selectedCust);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            String prescId = selectedPresc.getPrescriptionId();
            String custId = findCustomerId;
            System.out.println("Customer ID : "+findCustomerId);
            String patient = selectedPresc.getPatientName();
            String age = String.valueOf(selectedPresc.getAge());
            String medicalOfficer = selectedPresc.getMedicalOfficerName();
            String context = selectedPresc.getContext();
            String duration = selectedPresc.getDuration();
            String date = selectedPresc.getDate().toString();

            if (!Regex.isTextFieldValid(TextField.NAME,patient)) {
                new Alert(Alert.AlertType.ERROR, "Invalid patient name.").showAndWait();
                patientTxt.requestFocus();
                return;
            }

            if (!Regex.isTextFieldValid(TextField.AGE,ageTxt.getText())) {
                new Alert(Alert.AlertType.ERROR, "Invalid age.").showAndWait();
                ageTxt.requestFocus();
                return;
            }

            if (!Regex.isTextFieldValid(TextField.NAME,medicalOfficer)) {
                new Alert(Alert.AlertType.ERROR, "Invalid medical officer name.").showAndWait();
                medicalOfficerTxt.requestFocus();
                return;
            }

            if (!Regex.isTextFieldValid(TextField.DESCRIPTION,context)) {
                new Alert(Alert.AlertType.ERROR, "Invalid context.").showAndWait();
                contextText.requestFocus();
                return;
            }

            if (!Regex.isTextFieldValid(TextField.DESCRIPTION,duration)) {
                new Alert(Alert.AlertType.ERROR, "Invalid duration.").showAndWait();
                durationTxt.requestFocus();
                return;
            }

            PrescriptionDTO updatedPresc = new PrescriptionDTO(
                    prescId,
                    custId,
                    patientTxt.getText(),
                    Integer.parseInt(ageTxt.getText()),
                    medicalOfficerTxt.getText(),
                    contextText.getText(),
                    durationTxt.getText(),
                    datePicker.getValue()
            );

            List<String> allTestIds = new ArrayList<>();
            for (SmallTestTm smallTestTm : testCart.getItems()) {
                allTestIds.add(smallTestTm.getTestId());
            }

            boolean isUpdated = false;
            try {
                isUpdated = prescriptionBO.updatePrescription(updatedPresc, allTestIds);
                System.out.println(isUpdated);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            if(isUpdated){
                testCart.getItems().clear();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to update prescription.").showAndWait();
                return;
            }

            try {
                prescriptionBO.update(updatedPresc);

                selectedPresc.setCustomerId(custId);
                selectedPresc.setPatientName(patient);
                selectedPresc.setAge(Integer.parseInt(age));
                selectedPresc.setMedicalOfficerName(medicalOfficer);
                selectedPresc.setContext(context);
                selectedPresc.setDuration(duration);
                selectedPresc.setDate(LocalDate.parse(date));

                clear();

                new Alert(Alert.AlertType.INFORMATION, "Prescription updated successfully.").showAndWait();

                loadAllPrescriptions();
            } catch (Exception e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "Failed to update prescription.").showAndWait();
                throw new RuntimeException(e);
            }
        } else {
            new Alert(Alert.AlertType.ERROR, "Please select a prescription to update.").showAndWait();
        }
    }

    private void loadTestsToCart(String prescId) throws SQLException, ClassNotFoundException {
        testCart.getItems().clear();

        List<TestDTO> tests = prescriptionBO.getTestsByPrescriptionId(prescId);

        for (TestDTO test : tests) {

            ImageView removeIcon = new ImageView(new Image(getClass().getResourceAsStream("/icon/Untitled design (47).png")));
            removeIcon.setFitWidth(20);
            removeIcon.setFitHeight(20);

            JFXButton remove = new JFXButton();
            remove.setGraphic(removeIcon);
            remove.setOnAction(e -> {
                testCart.getItems().remove(new SmallTestTm(test.getTestId(),test.getDescription(),remove));
            });

            SmallTestTm smallTestTm = new SmallTestTm(test.getTestId(),test.getDescription(),remove);
            System.out.println("Test :"+test.getTestId()+" "+test.getDescription()+" "+test.getTestType());


            testCart.getItems().add(smallTestTm);
        }
    }

    private void setCellValueFactory() {
        colPrescId.setCellValueFactory(new PropertyValueFactory<>("prescriptionId"));
        colCustomer.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        colPatient.setCellValueFactory(new PropertyValueFactory<>("patientName"));
        colAge.setCellValueFactory(new PropertyValueFactory<>("age"));
        colMedicalOfficer.setCellValueFactory(new PropertyValueFactory<>("medicalOfficerName"));
        colContext.setCellValueFactory(new PropertyValueFactory<>("context"));
        colDuration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("action"));

        colAction.setCellFactory((TableColumn<PrescTm, List<JFXButton>> column) -> {
            return new TableCell<PrescTm, List<JFXButton>>() {
                @Override
                protected void updateItem(List<JFXButton> buttons, boolean empty) {
                    super.updateItem(buttons, empty);
                    if (empty || buttons == null || buttons.isEmpty()) {
                        setGraphic(null);
                    } else {
                        HBox hbox = new HBox();
                        for (JFXButton button : buttons) {
                            hbox.getChildren().add(button);
                        }
                        setGraphic(hbox);
                    }
                }

            };
        });

        colTestId.setCellValueFactory(new PropertyValueFactory<>("testId"));
        colDescName.setCellValueFactory(new PropertyValueFactory<>("description"));
        colRemove.setCellValueFactory(new PropertyValueFactory<>("remove"));
    }

    public void onKeyPressedAction(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            prescTable.requestFocus();
        }
        searchBar.requestFocus();
        searchPresc();
    }

    private void searchPresc() {
            FilteredList<PrescTm> filteredList = new FilteredList<>(observableList, e -> true);
            searchBar.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredList.setPredicate(prescTm -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    if (prescTm.getPrescriptionId().contains(newValue)) {
                        return true;
                    } else if (prescTm.getCustomerId().contains(newValue)) {
                        return true;
                    } else if (prescTm.getPatientName().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else if (String.valueOf(prescTm.getAge()).contains(newValue)) {
                        return true;
                    } else if (prescTm.getMedicalOfficerName().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else if (prescTm.getContext().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else if (prescTm.getDuration().contains(newValue)) {
                        return true;
                    } else if (prescTm.getDate().toString().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    }
                    return false;
                });
            });


        SortedList<PrescTm> sortedList = new SortedList<>(filteredList);
        sortedList.comparatorProperty().bind(prescTable.comparatorProperty());
        prescTable.setItems(sortedList);

    }

    public void onTestSelectOption(ActionEvent actionEvent) {
        String selectedTest = (String) testComBox.getSelectionModel().getSelectedItem();

        if (selectedTest != null) {
            try {
                TestDTO test = testBO.getTestByDescription(selectedTest);
                if (test != null) {
                    ImageView removeIcon = new ImageView(new Image(getClass().getResourceAsStream("/icon/Untitled design (47).png")));
                    removeIcon.setFitWidth(20);
                    removeIcon.setFitHeight(20);

                    JFXButton remove = new JFXButton();
                    remove.setGraphic(removeIcon);
                    remove.setOnAction(e -> {
                        testCart.getItems().remove(new SmallTestTm(test.getTestId(), test.getDescription(), remove));
                    });

                    SmallTestTm smallTestTm = new SmallTestTm(test.getTestId(), test.getDescription(), remove);
                    testCart.getItems().add(smallTestTm);
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, "Failed to add test to cart.").showAndWait();
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

    }

    public void onMouseClickAction(MouseEvent mouseEvent) throws SQLException, ClassNotFoundException {
        if (mouseEvent.getClickCount() == 1) {
            int selectedIndex = prescTable.getSelectionModel().getSelectedIndex();

            if (selectedIndex >= 0) {
                selectedPresc = prescTable.getItems().get(selectedIndex);

                String prescId = selectedPresc.getPrescriptionId();
                String custId = selectedPresc.getCustomerId();
                String patient = selectedPresc.getPatientName();
                String age = String.valueOf(selectedPresc.getAge());
                String medicalOfficer = selectedPresc.getMedicalOfficerName();
                String context = selectedPresc.getContext();
                String duration = selectedPresc.getDuration();
                String date = selectedPresc.getDate().toString();

                String custName = customerBO.searchCustomerByCustId(custId).getName();
                patientTxt.setText(patient);
                ageTxt.setText(age);
                medicalOfficerTxt.setText(medicalOfficer);
                contextText.setText(context);
                durationTxt.setText(duration);
                datePicker.setValue(LocalDate.parse(date));
                custCombox.setValue(custName);
                durationTxt.setText(duration);
                datePicker.setValue(LocalDate.parse(date));

                loadTestsToCart(prescId);
            }
        }
    }

    public void addBtnOnAction(ActionEvent actionEvent) {
        String prescId = null;
        try {
            prescId = prescriptionBO.generatePrescriptionId();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to generate prescription ID.").showAndWait();
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String custId = (String) custCombox.getSelectionModel().getSelectedItem();
        String patient = patientTxt.getText();
        int age = Integer.parseInt(ageTxt.getText());
        String medicalOfficer = medicalOfficerTxt.getText();
        String context = contextText.getText();
        String duration = durationTxt.getText();
        LocalDate date = datePicker.getValue();

        if (!Regex.isTextFieldValid(TextField.NAME,patient)) {
            new Alert(Alert.AlertType.ERROR, "Invalid patient name.").showAndWait();
            patientTxt.requestFocus();
            return;
        }

        if (!Regex.isTextFieldValid(TextField.AGE,ageTxt.getText())) {
            new Alert(Alert.AlertType.ERROR, "Invalid age.").showAndWait();
            ageTxt.requestFocus();
            return;
        }

        if (!Regex.isTextFieldValid(TextField.NAME,medicalOfficer)) {
            new Alert(Alert.AlertType.ERROR, "Invalid medical officer name.").showAndWait();
            medicalOfficerTxt.requestFocus();
            return;
        }

        if (!Regex.isTextFieldValid(TextField.DESCRIPTION,context)) {
            new Alert(Alert.AlertType.ERROR, "Invalid context.").showAndWait();
            contextText.requestFocus();
            return;
        }

        if (!Regex.isTextFieldValid(TextField.DESCRIPTION,duration)) {
            new Alert(Alert.AlertType.ERROR, "Invalid duration.").showAndWait();
            durationTxt.requestFocus();
            return;
        }

        custId = findCustomerId;

        PrescriptionDTO newPresc = new PrescriptionDTO(prescId, custId, patient, age, medicalOfficer, context, duration, date);

        List<String> testNames = testCart.getItems().stream()
                .map(SmallTestTm::getTestId)
                .collect(Collectors.toList());

        try {
            boolean isPrescSaved = prescriptionBO.addPrescriptionWithTestIds(newPresc, testNames);

            new Alert(Alert.AlertType.INFORMATION, "Prescription added successfully.").showAndWait();

            loadAllPrescriptions();
            clear();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to add prescription.").showAndWait();
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void custComboxKeyPressed(KeyEvent keyEvent) {
        String userInput = custCombox.getEditor().getText().toLowerCase();

        if (userInput.isEmpty()) {
            custCombox.setItems(allCustNames);
        } else {
            ObservableList<String> filteredList = FXCollections.observableArrayList();
            for (String name : allCustNames) {
                if (name.toLowerCase().contains(userInput)) {
                    filteredList.add(name);
                }
            }
            custCombox.setItems(filteredList);
        }
        custCombox.show();
    }

    @FXML
    public void testComboxKeyPressed(KeyEvent keyEvent) {
        String userInput = testComBox.getEditor().getText().toLowerCase();

        if (userInput.isEmpty()) {
            testComBox.setItems(allTestNames);
        } else {
            ObservableList<String> filteredList = FXCollections.observableArrayList();
            for (String name : allTestNames) {
                if (name.toLowerCase().contains(userInput)) {
                    filteredList.add(name);
                }
            }
            testComBox.setItems(filteredList);
        }
        testComBox.show();
    }

    public void clear(){
        custCombox.setValue(null);
        patientTxt.clear();
        ageTxt.clear();
        medicalOfficerTxt.clear();
        contextText.clear();
        durationTxt.clear();
        datePicker.setValue(null);
    }

    public void onPatientName(KeyEvent keyEvent) {
        Regex.setTextColor(TextField.NAME, patientTxt);
    }

    public void onAge(KeyEvent keyEvent) {
        Regex.setTextColor(TextField.AGE, ageTxt);
    }

    public void onMedicalOfficer(KeyEvent keyEvent) {
        Regex.setTextColor(TextField.NAME, medicalOfficerTxt);
    }

    public void onDuration(KeyEvent keyEvent) {
        Regex.setTextColor(TextField.DESCRIPTION, durationTxt);
    }

    public void onContext(KeyEvent keyEvent) {
        Regex.setTextColor(TextField.DESCRIPTION, contextText);
    }

    public void onCustSelectOption(ActionEvent actionEvent) {
        String selectedCust = custCombox.getEditor().getText().toLowerCase();
        System.out.println("Selected Customer : "+selectedCust);
        try {
            findCustomerId = customerBO.searchCustomerByName(selectedCust);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Customer ID : "+findCustomerId);
    }
}
