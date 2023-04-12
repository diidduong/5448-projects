package org.example.utils;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import org.example.entities.Picture;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class ImageUtils {
    public static void savePicture(Picture pic, String path) {

    }

    public static Picture getPicture(String path) {

        return null;
    }

    public static Image getFxImageFromURL(String url) {
//        url = "https://images.dog.ceo/breeds/komondor/n02105505_3132.jpg";

        BufferedImage bufferedImage = getImageFromURL(url);
        Image image = SwingFXUtils.toFXImage(bufferedImage, null);
        return image;
    }


    /**
     * Source: http://www.java2s.com/example/java-utility-method/bufferedimage-from-url-index-0.html
     * @param url
     * @return
     */
    public static BufferedImage getImageFromURL(String url) {
        HttpURLConnection connection = null;
        try {
            connection = (HttpURLConnection) new URL(url).openConnection();
            connection.connect();
            BufferedImage image = ImageIO.read(connection.getInputStream());
            connection.disconnect();
            return image;
        } catch (IOException e) {
            System.err.printf("Failed to get image from url %s", url);
            if (connection != null) {
                connection.disconnect();
            }
            return null;
        }
    }
}
