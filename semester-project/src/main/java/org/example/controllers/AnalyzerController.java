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

        updateChart();
    }
    Analyzer analyzer = Analyzer.getInstance();

    public void updateChart() {
        analyzer.analyzeRawImages();

        XYChart.Series<String, Integer> series = new XYChart.Series<>();
        series.getData().addAll(analyzer.getChartData());
        barChart.getData().setAll(series);
    }
}
