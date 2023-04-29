package org.example.algorithms;

import org.example.entities.Picture;
import org.example.utils.ImageUtils;
import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

import java.awt.image.BufferedImage;

/**
 * @author Duy Duong & Ahmed Biby
 * Subclass for abstract class of AnimalDetection
 */
public class DogDetection extends AnimalDetection {
    @Override
    public Picture.PictureType detect(BufferedImage bufferedImage) {
        Picture.PictureType type = Picture.PictureType.OTHER;
        // Load OpenCV library
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        // Load image
        Mat originalImage = ImageUtils.toMat(bufferedImage);
        Mat image = ImageUtils.resizeMat(originalImage, 200, 200);

        // Load face detector
        CascadeClassifier faceDetector = new CascadeClassifier("src/main/resources/libs/haarcascade_frontaldogface.xml");

        // Detect faces
        MatOfRect faceDetections = new MatOfRect();
//        faceDetector.detectMultiScale(image, faceDetections, 1.1, 4, 0, new Size(30, 30), new Size());
        faceDetector.detectMultiScale(image, faceDetections, 1.1, 4);

        // Count number of faces detected
        int numFaces = faceDetections.toArray().length;

        // Return YES or NO based on number of faces detected
        if (numFaces > 0) {
            System.out.println("Found a Dog");
            type = Picture.PictureType.ANIMAL;
        } else {
            System.out.println("No Dog Found");
        }

        // Draw bounding boxes on image
        for (Rect rect : faceDetections.toArray()) {
            Imgproc.rectangle(image, rect.tl(), rect.br(), new Scalar(0, 255, 0), 2);
        }

        // Save output image
        Imgcodecs.imwrite("src/main/resources/output.jpg", image);
        return type;
    }
}
