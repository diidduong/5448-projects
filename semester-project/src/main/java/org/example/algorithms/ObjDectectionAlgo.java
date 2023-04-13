package org.example.algorithms;

import org.example.entities.Picture;

import java.awt.image.BufferedImage;

public interface ObjDectectionAlgo {
    Picture.PictureType detect(BufferedImage bufferedImage);
}
