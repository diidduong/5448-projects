package org.example.utils;

import org.example.entities.HumanPicture;
import org.example.entities.Picture;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ImageUtilsTest {
    /**
     * https://www.baeldung.com/java-serialization
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @Test
    public void whenSerializingAndDeserializing_ThenObjectIsTheSame()
            throws IOException, ClassNotFoundException {
        // Create picture
        Picture pic = new HumanPicture();
        pic.setHeight(400);
        pic.setWidth(300);
        pic.setSrc("src/test/resources/pictures/image.txt");

        // Save serialized picture
        FileOutputStream fileOutputStream
                = new FileOutputStream(pic.getSrc());
        ObjectOutputStream objectOutputStream
                = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(pic);
        objectOutputStream.flush();
        objectOutputStream.close();

        // Read serialized picture from saved
        FileInputStream fileInputStream
                = new FileInputStream("src/test/resources/pictures/image.txt");
        ObjectInputStream objectInputStream
                = new ObjectInputStream(fileInputStream);
        Picture pic2 = (Picture) objectInputStream.readObject();
        objectInputStream.close();

        // Test if same object
        assertTrue(pic2 instanceof HumanPicture);
        assertTrue(pic.getHeight() == pic2.getHeight());
        assertTrue(pic.getWidth() == pic2.getWidth());
    }
}