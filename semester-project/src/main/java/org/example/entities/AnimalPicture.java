package org.example.entities;

import org.example.algorithms.AnimalDetection;

/**
 * @author Duy Duong & Ahmed Biby
 * Child class of Picture
 */
public class AnimalPicture extends Picture {
    public enum AnimalType {
        DOG, CAT
    }
    private AnimalType type;
    static String rootPath = "main/resources/pictures/animal/";

    public AnimalType getType() {
        return type;
    }

    public void setType(AnimalType type) {
        this.type = type;
    }
}
