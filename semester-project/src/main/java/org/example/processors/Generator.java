package org.example.processors;

import org.example.entities.Picture;
import org.example.utils.ImageUtils;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author Duy Duog & Ahmed Biby
 * Singleton Pattern
 * Class to handle picture generation
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

    /**
     * Generate picture from selected picture type
     * @param type picture type
     * @return picture from gallery
     */
    public Picture generatePicture(Picture.PictureType type) {
        String picturePath = "src/main/resources/pictures";
        int count = Analyzer.getInstance().countFiles(picturePath, String.format("^%s.*\\.txt", type));
        int randNum = ThreadLocalRandom.current().nextInt(count);
        return ImageUtils.getPicture(String.format("%s/%s-%d.txt", picturePath, type, randNum));
    }

    /**
     * Write generate picture to given file object. Do nothing if picture/file is null
     * @param pic generated picture
     * @param file destination file
     * @throws IOException default exception
     */
    public void savePictureToFile(Picture pic, File file) throws IOException {
        if (pic != null && file != null) {
            ImageUtils.savePictureToFile(pic, file, "jpg");
        }
    }
}
