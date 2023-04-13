package org.example.controllers;

import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import org.example.entities.Picture;
import org.example.processors.Generator;
import org.example.utils.ImageUtils;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

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

    @FXML
    protected void generatePicture() {
        System.out.printf("Generating %s Picture...\n", selectedImgType.textProperty().getValue());
        // TODO: add code here
        int randNum = ThreadLocalRandom.current().nextInt(3);
        selectedPicture = ImageUtils.getPicture(String.format("src/main/resources/pictures/%s-%d.txt", Picture.PictureType.getEnum(selectedImgType.getText()), randNum));
        imageView.setImage(SwingFXUtils.toFXImage(selectedPicture.getImage(), null));
    }

    /**
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
