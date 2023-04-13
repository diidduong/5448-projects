package org.example.processors;

import org.example.entities.HumanPicture;
import org.example.entities.Picture;
import org.example.entities.SimplePictureFactory;
import org.example.utils.ImageUtils;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Singleton Pattern
 */
public class Analyzer {
    private static Analyzer instance;
    int allCount;
    int humanCount;
    int animalCount;
    int vehicleCount;
    int otherCount;
    int unknownCount; // track the un-analyzed pictures (rawImages.size)

    List<BufferedImage> rawImages;

    private Analyzer() {
        rawImages = new ArrayList<>();
    }

    public static Analyzer getInstance() {
        if (instance == null) {
            instance = new Analyzer();
        }
        return instance;
    }

    /**
     * Run algorithms on rawImages to sort them into correct pictureType
     * - if rawImages list is empty, do nothing
     * - otherwise, iterate each image and run algorithms on it,
     *   after result in pictureType, serialize picture and store
     *   in local. Finally, remove image after done processing
     */
    void analyzeRawImages() {
        System.out.println("Analyzing all raw images");
        for (BufferedImage img : rawImages) {
            Picture.PictureType type = Picture.PictureType.ANIMAL;
            Picture pic = SimplePictureFactory.createPicture(type);
            pic.setImage(img);
            ImageUtils.savePicture(pic, String.format("src/main/resources/pictures/%s-%d.txt",type,animalCount));
            animalCount++;
        }
        System.out.println("Done");
    }

    /**
     * Print all counts for debug
     */
    void printResult() {
        System.out.printf("Total picture count: %d\n", allCount);
        System.out.printf("Human picture count: %d\n", humanCount);
        System.out.printf("Animal picture count: %d\n", animalCount);
        System.out.printf("Vehicle picture count: %d\n", vehicleCount);
        System.out.printf("Other picture count: %d\n", otherCount);
        System.out.printf("Un-analyzed picture count: %d\n", unknownCount);
    }

    /**
     * Get a map of chart data (Label, value)
     * Eg. (humanCount, 50)
     *
     * @return map of chart data
     */
    HashMap<String, Integer> getChartData() {
        HashMap<String, Integer> map = new HashMap<>();

        // TODO: add code here

        return map;
    }

}
