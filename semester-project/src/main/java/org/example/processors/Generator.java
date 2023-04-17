package org.example.processors;

import org.example.entities.Picture;
import org.example.utils.ImageUtils;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Singleton Pattern
 */
public class Generator {
    private static Generator instance;

    private Generator() {

    }

    public static Generator getInstance() {
        if (instance == null) {
            instance = new Generator();
        }
        return instance;
    }

    public Picture generatePicture(Picture.PictureType type) {
        String picturePath = "src/main/resources/pictures";
        int count = Analyzer.getInstance().countFiles(picturePath, String.format("^%s.*\\.txt", type));
        int randNum = ThreadLocalRandom.current().nextInt(count);
        return ImageUtils.getPicture(String.format("%s/%s-%d.txt", picturePath, type, randNum));
    }

    public void savePictureToFile(Picture pic, File file) throws IOException {
        if (pic != null && file != null) {
            ImageUtils.savePictureToFile(pic, file, "png");
        }
    }
}
