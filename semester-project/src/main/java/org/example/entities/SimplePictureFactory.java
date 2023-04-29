package org.example.entities;

/**
 * @author Duy Duong & Ahmed Biby
 * Factory pattern, create picture based on type
 */
public class SimplePictureFactory {
    public static Picture createPicture(Picture.PictureType type) {
        Picture picture = null;
        switch (type) {
            case HUMAN:
                picture = new HumanPicture();
                break;
            case ANIMAL:
                picture = new AnimalPicture();
                break;
            case VEHICLE:
                picture = new VehiclePicture();
                break;
            default:
                picture = new OtherPicture();
                break;
        }
        picture.setPictureType(type);
        return picture;
    }
}
