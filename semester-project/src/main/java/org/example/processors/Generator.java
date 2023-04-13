package org.example.processors;

import javafx.embed.swing.SwingFXUtils;
import org.example.entities.Picture;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

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
    Picture generatePicture(Picture.PictureType type) {
        return null;
    }

    public void savePictureToFile(Picture pic, File file) throws IOException {
        if (pic != null && file != null) {
            ImageIO.write(pic.getImage(), "png", file);
        }
    }
}
