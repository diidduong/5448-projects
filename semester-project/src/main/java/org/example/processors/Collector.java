package org.example.processors;

import org.example.entities.Progress;
import org.example.utils.ImageUtils;

import java.awt.image.BufferedImage;

/**
 * Singleton Pattern
 */
public class Collector implements Runnable {
    private static Collector instance;

    private Progress progress;

    private Collector() {
        progress = new Progress(0);
    }

    public static Collector getInstance() {
        if (instance == null) {
            instance = new Collector();
        }
        return instance;
    }

    public Progress getProgress() {
        return progress;
    }

    private String urls = "";

    public String getUrls() {
        return urls;
    }

    public void setUrls(String urls) {
        this.urls = urls;
    }

    /**
     * Collect all pictures from list of url and store as rawImages
     * @param urls
     */
    public void collectPictures(String urls) {
        String[] urlList = breakURLsIntoList(urls);
        int numURL = urlList.length;

        progress.reset();

        for (int i = 0; i < numURL; i++) {
            collectPicture(urlList[i]);

            // Simulate delay of 1 second
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            // Calculate % of work done
            double progressVal = (double) (i+1) / numURL;
            System.out.printf("%.2f %%\n", progressVal);
            progress.setValue(progressVal);

        }
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

        String[] urlList = urls.split("\n");

        return urlList;
    }

    /**
     * Collect picture from URL then add to Analyzer's list of raw images
     * @param url
     */
    void collectPicture(String url) {
        BufferedImage bufferedImage = ImageUtils.getImageFromURL(url);
        if (bufferedImage != null) {
            Analyzer.getInstance().addRawImg(bufferedImage);
        }
    }

    @Override
    public void run() {
        collectPictures(urls);
        // clear urlList when finished
        urls = "";
    }
}
