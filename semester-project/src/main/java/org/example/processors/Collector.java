package org.example.processors;

import org.example.utils.ImageUtils;

import java.awt.image.BufferedImage;

/**
 * Singleton Pattern
 */
public class Collector {
    private static Collector instance;

    private Collector() {

    }

    public static Collector getInstance() {
        if (instance == null) {
            instance = new Collector();
        }
        return instance;
    }

    /**
     * Collect all pictures from list of url and store as rawImages
     * @param urls
     */
    void collectPictures(String urls) {
        String[] url = null;
    }

    /**
     * Split string contains all url separated by next line \n into
     * list of url for picture collection
     *
     * @param urls
     * @return
     */
    String[] breakURLsIntoList(String urls) {
        String separator = "\n";

        // TODO: add code here

        return null;
    }

    /**
     * Collect picture from URL then add to Analyzer's list of raw images
     * @param url
     */
    void collectPicture(String url) {
        BufferedImage bufferedImage = ImageUtils.getImageFromURL(url);
        if (bufferedImage != null) {
            Analyzer.getInstance().rawImages.add(bufferedImage);
        }
    }
}
