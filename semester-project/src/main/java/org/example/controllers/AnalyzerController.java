package org.example.controllers;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import org.example.processors.Analyzer;

public class AnalyzerController {
    @FXML
    private BarChart<String, Integer> barChart;

    @FXML
    void initialize() {
        barChart.setLegendVisible(false);
        barChart.setAnimated(false);

        ObservableList<XYChart.Data<String, Integer>> data = analyzer.getObv();
        XYChart.Series<String, Integer> series = new XYChart.Series<>(data);
//        series.dataProperty().set(data);
        barChart.getData().setAll(series);

    }
    Analyzer analyzer = Analyzer.getInstance();

    public void updateChart() {
        new Thread(analyzer).start();
//        Platform.runLater(() -> {
//            analyzer.analyzeRawImages();
//        });

//        analyzer.analyzeRawImages();
        analyzer.printResult();
    }
}
