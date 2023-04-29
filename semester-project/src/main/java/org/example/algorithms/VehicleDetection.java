package org.example.algorithms;

import org.example.entities.Picture;
import org.example.utils.ImageUtils;
import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author Duy Duong & Ahmed Biby
 * Concrete abstract class of ObjDectectionAlgo
 */
public class VehicleDetection implements ObjDectectionAlgo{
    @Override
    public Picture.PictureType detect(BufferedImage bufferedImage) {
        Picture.PictureType type = Picture.PictureType.OTHER;
        // Load OpenCV library
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        // Load image
        Mat image = ImageUtils.toMat(bufferedImage);
//        Mat image = Imgcodecs.imread("src/test/resources/pictures/00002.jpg");
//
        Mat grayImage = new Mat();
        Imgproc.cvtColor(image, grayImage, Imgproc.COLOR_BGR2GRAY);
//        Imgproc.equalizeHist(grayImage, grayImage);

        // Load vehicle detector
//        CascadeClassifier vehicleDetector = new CascadeClassifier("src/main/resources/libs/haarcascade_frontalface_alt.xml");
        CascadeClassifier vehicleDetector = new CascadeClassifier("src/main/resources/libs/cars.xml");

        // Detect vehicles
        MatOfRect vehicleDetections = new MatOfRect();
//        vehicleDetector.detectMultiScale(grayImage, vehicleDetections, 1.1, 3, 0, new Size(50, 50), new Size());
        vehicleDetector.detectMultiScale(grayImage, vehicleDetections, 1.02, 1, 0, new Size(30, 30), new Size());

        // Count number of vehicles detected
        int numvehicles = vehicleDetections.toArray().length;

        // Return YES or NO based on number of vehicles detected
        if (numvehicles > 0) {
            System.out.println("YES");
            type = Picture.PictureType.VEHICLE;
        } else {
            System.out.println("NO");
        }

        // Testing
        // Draw bounding boxes on image
        Rect[] carDetectionsArray = vehicleDetections.toArray();
        for (Rect rect : carDetectionsArray) {
            Imgproc.rectangle(image, rect.tl(), rect.br(), new Scalar(0, 255, 0), 2);
        }

        // Save output image
        Imgcodecs.imwrite("src/test/resources/pictures/output.jpg", image);
        // End Testing

        return type;
    }

    public static void main(String[] args) {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("src/test/resources/pictures/car-0.jpg"));
        } catch (IOException e) {

        }
        ObjDectectionAlgo algo = new VehicleDetection();
        Picture.PictureType type = algo.detect(img);
        System.out.println(type);
    }
}
