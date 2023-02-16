# FNCD Simulation
**CU CSCI 5448 Project 2**\
**Author: Duy Duong, Ahmed.H.Biby**

#Implementation
We use Java 8 for our development and Junit 5 for some initial testing. Our source code are inside /src folder. Our test code are inside /test folder. To run this project, you need to include Junit5 in external libraries, and set /src as root source in order to build and run.

##UML link
https://drive.google.com/file/d/111r0qxDVuEO2GZqkcgL9lRLjeBJPh3bv/view?usp=sharing
##Assumptions
- If there is no Dirty/Clean Vehicle in inventory, Intern can take a break that day
- If there is no Broken/Used Vehicle, Mechanic can take a break that day
- If all vehicles are Broken, Salesperson can take a break that day
- Buyers are first come, first served
##Output
30-day simulation output is stored in *SlimResults.txt* at root level.