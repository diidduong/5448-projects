package org.example.processors;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AnalyzerTest {

    @Test
    void countFiles_all() {
        Analyzer analyzer = Analyzer.getInstance();
        int count = analyzer.countFiles("src/test/resources/pictures", null);
        assertEquals(4, count);
    }

    @Test
    void countFiles_patternMatched() {
        Analyzer analyzer = Analyzer.getInstance();
        int count = analyzer.countFiles("src/test/resources/pictures", "^HUMAN.*\\.txt");
        assertEquals(2, count);
    }
}