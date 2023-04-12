package org.example.algorithms;

import org.example.entities.Picture;

public interface ObjDectectionAlgo {
    Picture.PictureType detect(Picture pic);
}
