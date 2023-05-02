package org.example.processors;

import org.example.entities.AnimalPicture;
import org.example.entities.Picture;
import org.junit.jupiter.api.Test;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class GeneratorTest {
    @Test
    void savePictureToFile() throws IOException {
        File f = new File("src/test/resources/pictures/save-pic.jpg");
        f.delete();
        f.createNewFile();
        Generator generator = Generator.getInstance();
        Picture pic = new AnimalPicture();
        pic.setSrc("source");
        pic.setImage(new BufferedImage(100, 100, 1));
        generator.savePictureToFile(pic, f);
    }
}