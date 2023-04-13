package org.example.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import org.example.processors.Collector;


public class CollectorController {
    @FXML
    private TextArea urlListInput;

    Collector collector = Collector.getInstance();

    String urlListExample = "https://images.dog.ceo/breeds/briard/n02105251_7745.jpg\n" +
            "https://images.dog.ceo/breeds/borzoi/n02090622_431.jpg\n" +
            "https://images.dog.ceo/breeds/labrador/n02099712_4160.jpg";

    @FXML
    protected void collectPictures() {
        System.out.println("Collecting pictures...");
        System.out.println(urlListInput.getText());
        collector.collectPictures(urlListInput.getText());
    }
}
