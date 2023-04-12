package org.example.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

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