package org.example.controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import org.example.processors.Collector;

/**
 * @author Duy Duong & Ahmed Biby
 * Class to handle Collector tab
 */
public class CollectorController {
    @FXML
    private TextArea urlListInput;

    @FXML
    private ProgressBar progressBar = new ProgressBar();

    @FXML
    private Label progressLabel = new Label();

    Collector collector = Collector.getInstance();

    String urlListExample = "https://images.dog.ceo/breeds/briard/n02105251_7745.jpg\n" +
            "https://images.dog.ceo/breeds/borzoi/n02090622_431.jpg\n" +
            "https://images.dog.ceo/breeds/labrador/n02099712_4160.jpg";

    @FXML
    public void initialize() {
        progressBar.progressProperty().bind(collector.getProgress().valueProperty());
        collector.getProgress().valueProperty().addListener(((observable, oldValue, newValue) -> {
            Platform.runLater(() -> {
                if (newValue.intValue() == 1) {
                    progressLabel.setText("Done!");
                } else {
                    progressLabel.setText(String.format("%.2f%%",newValue.doubleValue() * 100));
                }
            });
        }));
    }

    /**
     * Collect pictures from the input field
     */
    public void collectPictures() {
        System.out.println("Collecting pictures...");
        System.out.println(urlListInput.getText());

        progressLabel.setText("");

        // Put urls to the working space
        collector.setUrls(urlListInput.getText());
        // Run the collecting
        new Thread(collector).start();
    }
}
