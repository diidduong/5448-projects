package org.example.processors;

import org.example.entities.Progress;
import org.example.utils.ImageUtils;

import java.awt.image.BufferedImage;

/**
 * @author Duy Duong && Ahmed Biby
 * Singleton Pattern
 * Class to handle picture collection which can be run using Thread
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
     * @param urls string contains all image urls
     */
    public void collectPictures(String urls) {
        String[] urlList = breakURLsIntoList(urls);
        int numURL = urlList.length;

        // reset progress value to 0
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

        // Automatically analyzing data after collected
        new Thread(Analyzer.getInstance()).start();
    }

    /**
     * Split string contains all url separated by separator into
     * list of url for picture collection
     *
     * @param urls string of all image urls
     * @return array of image url string
     */
    String[] breakURLsIntoList(String urls) {
        String separator = "\n";

        String[] urlList = urls.split("\n");

        return urlList;
    }

    /**
     * Collect picture from URL then add to Analyzer's list of raw images
     * @param url image url
     */
    void collectPicture(String url) {
        BufferedImage bufferedImage = ImageUtils.getImageFromURL(url);
        if (bufferedImage != null) {
            Analyzer.getInstance().addRawImg(bufferedImage);
        }
    }

    @Override
    public void run() {
        // Collect all images from url array
        collectPictures(urls);
        // clear urlList when finished
        urls = "";
    }
}
