# Project 6

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
User can collect picture of formats (.jpg, .jpeg, .png) from website URLs all at once and store them in their computer.
### Analyze Picture Type
Collected pictures are automatically sorted into categories such as Human, Animal, Vehicle, Other.


