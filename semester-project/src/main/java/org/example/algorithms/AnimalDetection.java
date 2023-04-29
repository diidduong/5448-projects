package org.example.algorithms;

import org.example.entities.Picture;

import java.awt.image.BufferedImage;

/**
 * @author Duy Duong & Ahmed Biby
 * Concrete abstract class of ObjDectectionAlgo
 */
public abstract class AnimalDetection implements ObjDectectionAlgo{
    @Override
    public abstract Picture.PictureType detect(BufferedImage bufferedImage);
}
