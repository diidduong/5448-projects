package com.example.semesterproject;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

public class GeneratorController {
    @FXML
    private ComboBox<String> comboBox;

    @FXML
    private Label selectedImgType = new Label();

    @FXML
    public void initialize() {
        comboBox.getItems().addAll("Human", "Animal", "Vehicle");
        selectedImgType.textProperty().bind(comboBox.getSelectionModel().selectedItemProperty());
    }

    @FXML
    protected void generatePicture() {
        System.out.println("Generating Picture...");
        // TODO: add code here
    }

    @FXML
    protected void downloadPicture() {
        System.out.println("Downloading Picture...");
        // TODO: add code here
    }
}
