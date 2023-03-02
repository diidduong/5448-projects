# FNCD Simulation
**CU CSCI 5448 Project 3**\
**Author: Duy Duong, Ahmed.H.Biby**

# Implementation
We use IntelliJ IDE with Java 8 for our development and Junit 5 for some initial testing. Our source code are inside /src folder. Our test code are inside */test* folder. To run this project, you need to include Junit5 in external libraries, and set /src as root source in order to build and run.
# Testing
All test files are store in */test* folder. There are current 5 test files with 22 assertions. To run all tests, you need to mark the folder as Test Sources Root, then right-click on the */test* folder, select option "Run All Tests" or use shortcut Ctrl+Shift+F10. *test-output.png* shows how the output should look like in IDE.

## UML Link
This includes updated version of class diagram (New Class Diagram), old class diagram, and activity diagram. https://drive.google.com/file/d/1zm3C6x2kGjLA6jJz4KAAFQIG-kwMj9kh/view?usp=sharing. 

New class diagram combines *Registry* class into Observer pattern since they are doing same purpose of logging and tracking. We also separate *Activity* class into sub-functions inside each *Staff* class to reduce complexity of abstracting work method (wash, repair, sale, and race). This refactoring increases cohesion for each *Staff* class. 

## Assumptions
- If there is no Dirty/Clean Vehicle in inventory, Intern can take a break that day
- If there is no Broken/Used Vehicle, Mechanic can take a break that day
- If all vehicles are Broken, Salesperson can take a break that day
- If no vehicles for Racing, Driver can take a break that day
- Buyers are first come, first served
## Output
- 30-day simulation output including new events (ie. washing methods used, racing events, sale bundle result, and daily Tracker summary) is stored in *SlimResults.txt* at root level.
- All log events for each day are stored in *output/Logger-\*.txt*. There will be 30 log files in total.

