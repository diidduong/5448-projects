package org.example.processors;

import org.example.algorithms.*;
import org.example.entities.AnimalPicture;
import org.example.entities.Picture;
import org.example.entities.SimplePictureFactory;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Duy Duong & Ahmed Biby
 * Processor to process Image with different algorithms
 */
public class ImageProcessor {
    List<ObjDectectionAlgo> algoList;

    public ImageProcessor() {
        algoList = new ArrayList<>();
        algoList.add(new CatDetection());
        algoList.add(new DogDetection());
        algoList.add(new HumanDetection());
        algoList.add(new VehicleDetection());
    }

    /**
     * Run OpenCV algorithms (algoList) to find picture type
     * @param image BufferedImage
     * @return picture type for object detected
     */
    @Deprecated
    Picture.PictureType processPictureType(BufferedImage image) {
        for (ObjDectectionAlgo od: algoList) {
            Picture.PictureType type = od.detect(image);
            if (type != Picture.PictureType.OTHER) {
                return type;
            }
        }

        return Picture.PictureType.OTHER;
    }

    /**
     * Run OpenCV algorithms (algoList) to find picture type and create Picture
     * @param image raw input image
     * @return Picture with analyzed picture type
     */
    Picture processPicture(BufferedImage image) {
        Picture pic = null;

        // iterate through all algorithms
        for (ObjDectectionAlgo od: algoList) {
            Picture.PictureType type = od.detect(image);

            if (type != Picture.PictureType.OTHER) {
                pic = SimplePictureFactory.createPicture(type);

                if (type == Picture.PictureType.ANIMAL) {
                    if (od instanceof CatDetection) {
                        ((AnimalPicture) pic).setType(AnimalPicture.AnimalType.CAT);
                    } else {
                        ((AnimalPicture) pic).setType(AnimalPicture.AnimalType.DOG);
                    }
                }
                break;
            }
        }

        // If can't detect picture type, make it as Other
        if (pic == null) {
            pic = SimplePictureFactory.createPicture(Picture.PictureType.OTHER);
        }

        // set raw image data
        pic.setImage(image);

        System.out.println("Detect PictureType " + pic.getPictureType());
        return pic;
    }
}
