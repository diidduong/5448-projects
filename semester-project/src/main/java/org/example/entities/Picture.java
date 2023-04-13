package org.example.entities;

import org.example.utils.ImageUtils;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;

public abstract class Picture implements Serializable {
    public enum PictureType {
        HUMAN, ANIMAL, VEHICLE, OTHER
    }

    PictureType pictureType;
    int width;
    int height;
    private byte[] imageBytes;
    String src;

    public PictureType getPictureType() {
        return pictureType;
    }

    public void setPictureType(PictureType pictureType) {
        this.pictureType = pictureType;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * return null if failed to convert
     * @return
     * @throws IOException
     */
    public BufferedImage getImage() {
        return ImageUtils.toBufferedImage(imageBytes);
    }

    /**
     * set to null if failed to convert
     * @param image
     */
    public void setImage(BufferedImage image) {
        this.imageBytes = ImageUtils.toByteArray(image, "png");
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }
}
