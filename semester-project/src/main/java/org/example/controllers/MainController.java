package org.example.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

/**
 * @author Duy Duong & Ahmed Biby
 * Main controller class
 */
public class MainController {
    @FXML
    private Label welcomeText;

    @FXML
    private ComboBox comboBox;

    @FXML
    protected void onHelloButtonClick() {
        System.out.println("Welcome to JavaFX Application!");
    }
}