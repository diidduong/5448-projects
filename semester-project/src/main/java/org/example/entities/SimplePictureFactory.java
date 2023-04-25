package org.example.entities;

public class SimplePictureFactory {
    public static Picture createPicture(Picture.PictureType type) {
        switch (type) {
            case HUMAN: return new HumanPicture();
            case ANIMAL: return new AnimalPicture();
            case VEHICLE: return new VehiclePicture();
            case CAT: return new CatPicture();
            case DOG: return new DogPicture();
            default: return new OtherPicture();
        }
    }
}
