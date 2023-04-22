package org.example.processors;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;
import org.example.entities.Picture;
import org.example.entities.SimplePictureFactory;
import org.example.utils.ImageUtils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Singleton Pattern
 */
public class Analyzer implements Runnable{
    private final String PATH = "src/main/resources/pictures";
    private static Analyzer instance = getInstance();
    int allCount;
    int humanCount;
    int animalCount;
    int vehicleCount;
    int otherCount;

    int notProcessedCount;

    private List<XYChart.Data<String, Integer>> chartData;
    private XYChart.Series<String, Integer> series;

    private ObservableList<XYChart.Data<String, Integer>> data;

    private List<BufferedImage> rawImages;
    ImageProcessor imageProcessor;

    private Analyzer() {
        rawImages = new ArrayList<>();
        imageProcessor = new ImageProcessor();

        humanCount = countFiles(PATH, String.format("^%s.*\\.txt", Picture.PictureType.HUMAN));
        animalCount = countFiles(PATH, String.format("^%s.*\\.txt", Picture.PictureType.ANIMAL));
        vehicleCount = countFiles(PATH, String.format("^%s.*\\.txt", Picture.PictureType.VEHICLE));
        otherCount = countFiles(PATH, String.format("^%s.*\\.txt", Picture.PictureType.OTHER));
        allCount = humanCount + animalCount + vehicleCount + otherCount;

        chartData = getChartData();
        data = FXCollections.observableList(chartData);
    }

    public static Analyzer getInstance() {
        if (instance == null) {
            instance = new Analyzer();
        }

        return instance;
    }

    public void addRawImg(BufferedImage img) {
        rawImages.add(img);
        notProcessedCount++;
        Platform.runLater(() -> {
            updatedChartData();
        });
    }

    public XYChart.Series<String, Integer> getSeries() {
        return series;
    }

    public ObservableList<XYChart.Data<String, Integer>> getObv() {
        return data;
    }

    /**
     * Run algorithms on rawImages to sort them into correct pictureType
     * - if rawImages list is empty, do nothing
     * - otherwise, iterate each image and run algorithms on it,
     *   after result in pictureType, serialize picture and store
     *   in local. Finally, remove image after done processing
     */
    public void analyzeRawImages() {
        System.out.println("Analyzing all raw images");
        for (BufferedImage img : rawImages) {

            try {
                Thread.sleep(1000);
            } catch (Exception e) {

            }

            Picture.PictureType type = imageProcessor.processPictureType(img);

            // Create Picture class to store pic
            Picture pic = SimplePictureFactory.createPicture(type);
            pic.setImage(img);

            // TODO: increase count of corresponding picture type, currently default to OTHER
            ImageUtils.savePicture(pic, String.format("src/main/resources/pictures/%s-%d.txt",type,otherCount));
            otherCount++;
            notProcessedCount--;
            allCount++;

            Platform.runLater(() -> {
                updatedChartData();
            });
        }

        // Clear the list when after pictures are processed
        rawImages.clear();
        System.out.println("Done");
    }

    /**
     * Print all counts for debug
     */
    public void printResult() {
        System.out.printf("Total picture count: %d\n", allCount);
        System.out.printf("Human picture count: %d\n", humanCount);
        System.out.printf("Animal picture count: %d\n", animalCount);
        System.out.printf("Vehicle picture count: %d\n", vehicleCount);
        System.out.printf("Other picture count: %d\n", otherCount);
        System.out.printf("Un-analyzed picture count: %d\n", notProcessedCount);
    }

    /**
     * Get a map of chart data (Label, value)
     * Eg. (humanCount, 50)
     *
     * @return map of chart data
     */
    public List<XYChart.Data<String, Integer>> getChartData() {

        List<XYChart.Data<String, Integer>> map = new ArrayList<>();
        map.add(new XYChart.Data<>("Human", humanCount));
        map.add(new XYChart.Data<>("Animal", animalCount));
        map.add(new XYChart.Data<>("Vehicle", vehicleCount));
        map.add(new XYChart.Data<>("Other", otherCount));
        map.add(new XYChart.Data<>("Not Processed", notProcessedCount));
        map.add(new XYChart.Data<>("Total", allCount));

        chartData = map;
        return map;
    }

    public void updatedChartData() {

        data.set(0, new XYChart.Data<>("Human", humanCount));
        data.set(1, new XYChart.Data<>("Animal", animalCount));
        data.set(2, new XYChart.Data<>("Vehicle", vehicleCount));
        data.set(3, new XYChart.Data<>("Other", otherCount));
        data.set(4, new XYChart.Data<>("Not Processed", notProcessedCount));
        data.set(5, new XYChart.Data<>("Total", allCount));
    }

    /**
     *
     * @param path directory path
     * @param regex pattern match, default to match anything if not specified
     * @return
     */
    int countFiles(String path, String regex) {
        if (regex == null || regex.isEmpty()) {
            regex = ".*";
        }
        int count = 0;
        File dir = new File(path);
        for (File f : dir.listFiles()) {
            if (f.isFile() && f.getName().matches(regex)) {
                count++;
            }
        }
        return count;
    }

    @Override
    public void run() {
        analyzeRawImages();
    }
}
