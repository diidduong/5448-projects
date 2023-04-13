package org.example.processors;

import org.example.algorithms.AnimalDetection;
import org.example.algorithms.HumanDetection;
import org.example.algorithms.ObjDectectionAlgo;
import org.example.algorithms.VehicleDetection;
import org.example.entities.Picture;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class ImageProcessor {
    List<ObjDectectionAlgo> algoList;

    public ImageProcessor() {
        algoList = new ArrayList<>();
        algoList.add(new HumanDetection());
        algoList.add(new AnimalDetection());
        algoList.add(new VehicleDetection());
    }

    /**
     * Run OpenCV algorithms (algoList) to find picture type
     * @param image
     * @return
     */
    Picture.PictureType processPictureType(BufferedImage image) {
        // TODO:
        //  1. Convert BufferedImage to Mat
        //  2. loop thru OpenCV algorithms on the image, if can't detect, return type OTHER


        return Picture.PictureType.OTHER;
    }
}
