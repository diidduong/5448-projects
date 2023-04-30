# Project 7

## Demo
Here is a brief demonstration video about our project,
- Link: https://cuboulder.zoom.us/rec/share/t4x9VxquXl1OT-2usgbYkUAv8uAVbwmzROvRZm3fkEhsHYnxQslo3V4fGWJhNlKU.ojDzw6_IeAQdBM-S?startTime=1682874709000
- Passcode: H55$K=ei

## Requirements
There are some setup steps before running the application.

1. Open project as Gradle project 
2. Install JavaFX JDK
3. Install OpenCV
### Open project as Gradle project
1. Clone the project
2. In IntelliJ, File -> New -> Project From Existing Source...
3. **Select** semester-project (5448-projects/semester-project). **Do not select** 5448-projects folder.
4. In the window, select Import Project From External Model -> Select Gradle
5. Hit Create. It will automatically install basic dependencies and build the project. 
### Install JavaFX JDK
1. Download JavaFX JDK at [Azul JDK FX](https://www.azul.com/downloads/?package=jdk-fx#zulu). We currently use **Azul Zulu Java 11 (LTS)**. 
2. After download, unzip the folder.
3. In IntelliJ, go to File -> Project Structure -> SDK -> Add SDK -> JDK... and select the unzipped jdk folder.
4. Hit Apply then hit OK.
### Install OpenCV Java
1. Download OpenCV at [OpenCV Release](https://opencv.org/releases/). If you're using Window, hit Windows download button.
2. After download, open the .exe file to extract opencv folder for Java.
3. In the file <em>build.gradle</em>. In dependencies, update file path to point to your opencv jar file. For example,
```
implementation files('src/main/resources/libs/opencv/build/java/opencv-470.jar')
```
4. In IntelliJ, right-click at file <em>MainApplication</em> -> More Run/Config -> Modify Run Configuration... -> Modify Options -> Add VM options. Then paste this command (update the path to point to your correct x64 folder)
```
-Djava.library.path=src\main\resources\libs\opencv\build\java\x64
```
### And you're good to go!

## Features
### Generate Picture
User can select a picture type and get a corresponding picture. User can also donwload the picture into their computer at any location.
### Collect Picture From URLs
User can collect picture of formats (.jpg, .jpeg, .png) from website URLs all at once and store them in their computer. We have some example url for you to test out. It is located at src/main/resources/org/example/url-example.txt. They are 2 dogs, 1 cat, 1 human, 1 vehicle, and 1 other picture.
```
https://images.dog.ceo/breeds/puggle/IMG_114654.jpg
https://images.dog.ceo/breeds/spitz-japanese/tofu.jpg
https://imagesvc.meredithcorp.io/v3/mm/image?url=https%3A%2F%2Fcf-images.us-east-1.prod.boltdns.net%2Fv1%2Fstatic%2F6157254766001%2F961ad642-68ec-4156-ab18-5fb26db7e85c%2Fa7fd44aa-762e-4be4-a99f-bdebcce91235%2F1280x720%2Fmatch%2Fimage.jpg
https://www.whichfaceisreal.com/fakeimages/image-2019-02-17_022008.jpeg
https://generatorfun.com/code/uploads/Random-Car-image-7.jpg
https://generatorfun.com/code/uploads/Random-Car-image-2.jpg
https://loremflickr.com/cache/resized/65535_52714618565_70da752185_320_240_nofilter.jpg
```
### Analyze Picture Type
Collected pictures are automatically sorted into categories such as Human, Animal, Vehicle, Other. They are all serialized as Picture object and stored at src/main/resources/pictures. Analyzed pictures are display as a chart on the UI which count each picture type and the sum.


