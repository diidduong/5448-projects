package org.example.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import org.example.processors.Generator;
import org.example.utils.ImageUtils;

public class GeneratorController {
    @FXML
    private ComboBox<String> comboBox;

    @FXML
    private ImageView imageView;

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
//        imageView.setImage(ImageUtils.downloadPictureFromURL(""));
    }

    @FXML
    protected void downloadPicture() {
        System.out.println("Downloading Picture...");
        // TODO: add code here
    }

    Generator generator = Generator.getInstance();

}
