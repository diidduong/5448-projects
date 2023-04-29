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
public class HumanDetection implements ObjDectectionAlgo {
    @Override
    public Picture.PictureType detect(BufferedImage bufferedImage) {
        Picture.PictureType type = Picture.PictureType.OTHER;
        // Load OpenCV library
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        // Load image
        Mat image = ImageUtils.toMat(bufferedImage);

        // Load face detector
        CascadeClassifier faceDetector = new CascadeClassifier("src/main/resources/libs/haarcascade_frontalface_alt.xml");

        // Detect faces
        MatOfRect faceDetections = new MatOfRect();
        faceDetector.detectMultiScale(image, faceDetections, 1.1, 2, 0, new Size(30, 30), new Size());

        // Count number of faces detected
        int numFaces = faceDetections.toArray().length;

        // Return YES or NO based on number of faces detected
        if (numFaces > 0) {
            System.out.println("YES");
            type = Picture.PictureType.HUMAN;
        } else {
            System.out.println("NO");
        }

        // Draw bounding boxes on image
        for (Rect rect : faceDetections.toArray()) {
            Imgproc.rectangle(image, rect.tl(), rect.br(), new Scalar(0, 255, 0), 2);
        }

        // Save output image
        Imgcodecs.imwrite("src/main/resources/output.jpg", image);
        return type;
    }

    public static void main(String[] args) {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("src/main/resources/pictures/dog-1.jpg"));
        } catch (IOException e) {

        }
        ObjDectectionAlgo algo = new HumanDetection();
        Picture.PictureType type = algo.detect(img);
        System.out.println(type);
    }
}
