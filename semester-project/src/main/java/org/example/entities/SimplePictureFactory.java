package org.example.entities;

/**
 * @author Duy Duong & Ahmed Biby
 * Factory pattern, create picture based on type
 */
public class SimplePictureFactory {
    public static Picture createPicture(Picture.PictureType type) {
        switch (type) {
            case HUMAN: return new HumanPicture();
            case ANIMAL: return new AnimalPicture();
            case VEHICLE: return new VehiclePicture();
            default: return new OtherPicture();
        }
    }
}
