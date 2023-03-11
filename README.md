# FNCD Simulation
**CU CSCI 5448 Project 4**\
**Author: Duy Duong, Ahmed.H.Biby**

# Implementation
We use IntelliJ IDE with Java 8 for our development and Junit 5 for some initial testing. Our source code are inside /src folder. Our test code are inside */test* folder. Our external library jars are inside */lib* folder. 

There are dependencies required to run this project. You need to include **Junit5** in external libraries. Also, we now use **XChart** library to display cumulative data day by day for whole simulation. You need to include jar files in the project by go to File -> Project Structure, inside Modules tab, hit (+) sign to add JARS or Directories which is */lib* folder. 

When everything is installed, set /src folder as root source then you can build and run the Main.
# Testing
All test files are store in */test* folder. There are now 7 test files with 28 assertions. To run all tests, you need to mark the folder as Test Sources Root, then right-click on the */test* folder, select option "Run All Tests" or use shortcut Ctrl+Shift+F10. *test-output.png* shows how the output should look like in IDE.

## UML Link
This includes updated version of class diagram and sequence diagram. https://drive.google.com/file/d/1kVFRK03SwYbyzQGtL8oDZ8TrG8Y_7kPm/view?usp=sharing. 

New class diagram includes new patterns (Factory, Command, Singleton) along with new 3 vehicles (Dozer, ParaMotor, GolfCart). All patterns are highlighted by blue dashed-line boxes.

## Assumptions
- If there is no Dirty/Clean Vehicle in inventory, Intern can take a break that day
- If there is no Broken/Used Vehicle, Mechanic can take a break that day
- If all vehicles are Broken, Salesperson can take a break that day
- If no vehicles for Racing, Driver can take a break that day
- Buyers are first come, first served
- Initial balance is increased to 3 million instead of 500,000
## Output
- 30-day simulation outputs includes 2 FNCDs running in parallel (with new vehicle types) and it is stored in *SlimResults.txt* at root level.
- 31st day output for user interface simulation
- All log events for each day are stored in *output/Logger-\*.txt*. There will be 31 log files in total.
- Two PNG images (*chart-North.png* and *chart-South.png*) contains chart of cumulative data for each FNCD 
