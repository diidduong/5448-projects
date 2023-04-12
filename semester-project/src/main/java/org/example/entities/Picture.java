package org.example.entities;

import java.awt.image.BufferedImage;

public abstract class Picture {
    public enum PictureType {
        HUMAN, ANIMAL, VEHICLE, OTHER
    }

    PictureType pictureType;
    int width;
    int height;
    BufferedImage image;
    String src;

}
