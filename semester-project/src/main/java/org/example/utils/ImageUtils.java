package org.example.utils;

import org.example.entities.Picture;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author Duy Duong & Biby Ahmed
 * Util class to handle picture and image operations
 */
public class ImageUtils {
    /**
     * Save picture bytes to a file with selected format
     * @param pic source Picture
     * @param file destination file
     * @param format picture format
     * @throws IOException default exception for ImageIO
     */
    public static void savePictureToFile(Picture pic, File file, String format) throws IOException {
        ImageIO.write(pic.getImage(), format, file);
    }

    /**
     * Save picture as serializable object to given path
     * Assumption: picture is not null
     * @param pic serializable Picture
     * @param path destination path
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
     * Get serializable Picture object from path
     * @param path source  path
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

    /**
     * Read Image from URL and convert to BufferedImage object
     * Source: http://www.java2s.com/example/java-utility-method/bufferedimage-from-url-index-0.html
     * @param url image url
     * @return BufferedImage or null if exception
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

    /**
     * convert BufferedImage to byte[]
     * Source: https://mkyong.com/java/how-to-convert-bufferedimage-to-byte-in-java/
     * @param bi BufferedImage
     * @param format image format
     * @return bytes of image
     */
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

    /**
     * convert byte[] to BufferedImage
     * Source: https://mkyong.com/java/how-to-convert-bufferedimage-to-byte-in-java/
     * @param bytes bytes of image
     * @return BufferedImage
     */
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
