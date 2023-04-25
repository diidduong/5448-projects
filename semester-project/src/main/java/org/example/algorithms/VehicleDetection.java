package org.example.algorithms;

import org.example.entities.Picture;
import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

import java.awt.image.BufferedImage;

public class VehicleDetection implements ObjDectectionAlgo{
    @Override
    public Picture.PictureType detect(BufferedImage bufferedImage) {
        // Load OpenCV library
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        // Load image
        Mat image = Imgcodecs.imread("src/main/resources/pictures/image.jpeg");

        // Load the Haar Cascade Classifier for vehicle detection
        CascadeClassifier vehicleClassifier = new CascadeClassifier("src/main/resources/libs/cars.xml");

        // Detect vehicles in the image
        MatOfRect vehicleDetections = new MatOfRect();
        vehicleClassifier.detectMultiScale(image, vehicleDetections,
                1.1, 2, 0, new Size(30, 30), new Size());

        // Count number of vehicles detected
        int numVehicles = vehicleDetections.toArray().length;

        // Return YES or NO based on number of vehicles detected
        if (numVehicles > 0) {
            System.out.println("YES");
            return Picture.PictureType.VEHICLE;
        } else {
            System.out.println("NO");
        }

        // Draw bounding boxes on image
        for (Rect rect : vehicleDetections.toArray()) {
            Imgproc.rectangle(image, rect.tl(), rect.br(), new Scalar(0, 255, 0), 2);
        }
        // Save output image
//        Imgcodecs.imwrite("resources/output.jpg", image);
        return null;
    }

}
