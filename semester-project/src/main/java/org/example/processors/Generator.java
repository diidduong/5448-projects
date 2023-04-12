package org.example.processors;

import org.example.entities.Picture;

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

    void downloadPicture(Picture pic, String destinationPath) {

    }
}
