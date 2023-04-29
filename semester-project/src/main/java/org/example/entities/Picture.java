package org.example.entities;

import org.example.utils.ImageUtils;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author Duy Duong & Ahmed Biby
 * abstract class for Picture, can be serialized
 */
public abstract class Picture implements Serializable {
    public enum PictureType {
        HUMAN, ANIMAL, VEHICLE, OTHER;

        public static PictureType getEnum(String text) {
            PictureType[] allEnums = PictureType.values();
            for (PictureType type : allEnums) {
                if (type.toString().equals(text.toUpperCase())) {
                    return type;
                }
            }
            return allEnums[ThreadLocalRandom.current().nextInt(allEnums.length)];
        }
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
        byte[] imageBytes = ImageUtils.toByteArray(image, "jpg");
        if (imageBytes != null || imageBytes.length > 0) {
            this.height = image.getHeight();
            this.width = image.getWidth();
            this.imageBytes = imageBytes;
        }
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }
}
