package org.example.controllers;

import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import org.example.entities.Picture;
import org.example.processors.Generator;

import java.io.File;
import java.io.IOException;

/**
 * @author Duy Duong & Ahmed Biby
 * Class to handle Generator tab
 */
public class GeneratorController {
    @FXML
    private ComboBox<String> comboBox;

    @FXML
    private ImageView imageView;

    @FXML
    private Label selectedImgType = new Label();
    private Picture selectedPicture;

    FileChooser fileChooser = new FileChooser();

    @FXML
    public void initialize() {
        comboBox.getItems().addAll("Human", "Animal", "Vehicle", "Other", "Random");
        comboBox.getSelectionModel().selectFirst();
        selectedImgType.textProperty().bind(comboBox.getSelectionModel().selectedItemProperty());

        // Initialize
        fileChooser.setTitle("Save Picture");
        fileChooser.setInitialFileName("picture");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PNG Files", "*.png"));
    }

    /**
     * Generate picture from selected type
     */
    @FXML
    protected void generatePicture() {
        System.out.printf("Generating %s Picture...\n", selectedImgType.textProperty().getValue());
        selectedPicture = generator.generatePicture(Picture.PictureType.getEnum(selectedImgType.getText()));
        imageView.setImage(SwingFXUtils.toFXImage(selectedPicture.getImage(), null));
    }

    /**
     * Show save dialog to save generated picture
     * Default exception
     */
    @FXML
    protected void downloadPicture() throws IOException {
        System.out.println("Downloading Picture...");

        // Do nothing if no picture is generated
        if (imageView.getImage() == null) return;

        // Show save dialog
        File file = fileChooser.showSaveDialog(null);
        generator.savePictureToFile(selectedPicture, file);
    }

    Generator generator = Generator.getInstance();
}
