package org.example.entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PictureTest {
    @Test
    void enumPictureTypePredefined() {
        Picture.PictureType type = Picture.PictureType.getEnum("Human");
        assertEquals(Picture.PictureType.HUMAN, type);
    }

    @Test
    void enumPictureTypeRandom() {
        Picture.PictureType type = Picture.PictureType.getEnum("Helu");
        assertNotNull(type);
    }
}