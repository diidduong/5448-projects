package org.example.utils;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import org.example.entities.Picture;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class ImageUtils {
    public static void savePictureToFile(Picture pic, File file) {
        // TODO: save picture
    }

    /**
     *
     * Assumption: picture is not null
     * @param pic
     * @param path
     */
    public static void savePicture(Picture pic, String path) {
        // Set picture location
        pic.setSrc(path);

        try {
            FileOutputStream fileOutputStream
                    = new FileOutputStream(path);
            ObjectOutputStream objectOutputStream
                    = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(pic);
            objectOutputStream.flush();
            objectOutputStream.close();
        } catch (IOException e) {
            System.err.printf("Failed to save picture to path %s", path);
            e.printStackTrace();
        }
    }

    /**
     *
     * @param path
     * @return picture if exists, null if not
     */
    public static Picture getPicture(String path) {
        System.out.printf("Getting picture from path %s\n", path);
        Picture pic = null;
        try {
            // Read serialized picture from saved
            FileInputStream fileInputStream
                    = new FileInputStream(path);
            ObjectInputStream objectInputStream
                    = new ObjectInputStream(fileInputStream);
            pic = (Picture) objectInputStream.readObject();
            objectInputStream.close();
            System.out.println("Done");
        } catch (IOException | ClassNotFoundException e) {
            System.err.printf("Can't get picture from path %s\n", path);
            e.printStackTrace();
        }
        return pic;
    }

    public static Image getFxImageFromURL(String url) {
        url = "https://images.dog.ceo/breeds/komondor/n02105505_3132.jpg";

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
        System.out.printf("Getting image from url %s\n", url);
        HttpURLConnection connection = null;
        try {
            connection = (HttpURLConnection) new URL(url).openConnection();
            connection.connect();
            BufferedImage image = ImageIO.read(connection.getInputStream());
            connection.disconnect();
            System.out.println("Done");
            return image;
        } catch (IOException e) {
            System.err.printf("Failed to get image from url %s", url);
            if (connection != null) {
                connection.disconnect();
            }
            return null;
        }
    }

    // https://mkyong.com/java/how-to-convert-bufferedimage-to-byte-in-java/
    // convert BufferedImage to byte[]
    public static byte[] toByteArray(BufferedImage bi, String format) {
        byte[] bytes = null;
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(bi, format, baos);
            bytes = baos.toByteArray();
        } catch (IOException e) {
            System.err.println("Failed to convert bufferedImage to byteArray");
            e.printStackTrace();
        }
        return bytes;
    }

    // https://mkyong.com/java/how-to-convert-bufferedimage-to-byte-in-java/
    // convert byte[] to BufferedImage
    public static BufferedImage toBufferedImage(byte[] bytes) {
        BufferedImage bi = null;
        try {
            InputStream is = new ByteArrayInputStream(bytes);
            bi = ImageIO.read(is);
        } catch (IOException e) {
            System.err.println("Failed to convert byteArray to bufferedImage");
            e.printStackTrace();
        }
        return bi;
    }
}
