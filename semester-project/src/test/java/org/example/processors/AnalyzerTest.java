package org.example.processors;

import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AnalyzerTest {

    @Test
    void countFiles_all() {
        Analyzer analyzer = Analyzer.getInstance();
        int count = analyzer.countFiles("src/test/resources/pictures", null);
        assertTrue(count > 0);
    }

    @Test
    void countFiles_patternMatched() {
        Analyzer analyzer = Analyzer.getInstance();
        int count = analyzer.countFiles("src/test/resources/pictures", "^HUMAN.*\\.txt");
        assertEquals(2, count);
    }

    @Test
    void getChartData() {
        Analyzer analyzer = Analyzer.getInstance();
        List<XYChart.Data<String, Integer>> map = analyzer.getChartData();
        assertEquals(6, map.size());
    }

    @Test
    void updateChartData() {
        Analyzer analyzer = Analyzer.getInstance();
        List<XYChart.Data<String, Integer>> map = analyzer.getChartData();
        int count = analyzer.humanCount;
        analyzer.humanCount++;
        analyzer.updatedChartData();
        ObservableList<XYChart.Data<String, Integer>> data = analyzer.getObv();
        assertEquals(count + 1, data.get(0).YValueProperty().get());
    }
}