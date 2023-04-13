package org.example.processors;

import org.example.utils.ImageUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CollectorTest {

    @Test
    void breakURLsIntoList() {
        String urls = "URL_1\nURL_2";
        Collector collector = Collector.getInstance();
        String[] urlList = collector.breakURLsIntoList(urls);
        assertEquals(2, urlList.length);
    }
}