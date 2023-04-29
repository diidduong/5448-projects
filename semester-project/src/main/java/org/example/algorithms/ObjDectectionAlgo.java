package org.example.algorithms;

import org.example.entities.Picture;

import java.awt.image.BufferedImage;

/**
 * @author Duy Duong & Ahmed Biby
 * Interface class for object detection ability
 */
public interface ObjDectectionAlgo {
    Picture.PictureType detect(BufferedImage bufferedImage);
}
