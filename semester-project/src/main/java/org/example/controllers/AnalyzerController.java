package org.example.controllers;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import org.example.processors.Analyzer;

/**
 * @author Duy Duong & Ahmed Biby
 * Class for handing Analyzer tab
 */
public class AnalyzerController {
    @FXML
    private BarChart<String, Integer> barChart;

    @FXML
    void initialize() {
        barChart.setLegendVisible(false);
        barChart.setAnimated(false);

        ObservableList<XYChart.Data<String, Integer>> data = analyzer.getObv();
        XYChart.Series<String, Integer> series = new XYChart.Series<>(data);
        barChart.getData().setAll(series);

    }
    Analyzer analyzer = Analyzer.getInstance();

    /**
     * Analyze unprocessed image and update chart
     */
    public void updateChart() {
        new Thread(analyzer).start();
        analyzer.printResult();
    }
}
