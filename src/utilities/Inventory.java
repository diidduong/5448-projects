package utilities;

import staff.Salesperson;
import staff.Staff;
import vehicle.Vehicle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

/**
 * @author Duy Duong
 *
 * Data class for inventory which contains working inventory for available Vehicle and
 * Sold Vehicles for sold ones.
 */
public class Inventory {
    private ArrayList<Vehicle> workingInventory= new ArrayList<>();
    private ArrayList<Vehicle> soldVehicles = new ArrayList<>();

    public ArrayList<Vehicle> getWorkingInventory() {
        return workingInventory;
    }

    public ArrayList<Vehicle> getSoldVehicles() {
        return soldVehicles;
    }

    /**
     * Get most expensive vehicle for sale by type. Vehicle must not be Broken.
     *
     * @param type Vehicle Type
     * @return most expensive vehicle by type if exists, null otherwise
     */
    public Vehicle getMostExpensiveVehicleForSaleByType(Vehicle.VehicleType type) {
        if (workingInventory.size() <= 0) return null;
        Vehicle mostExpensive = null;
        for (int i = 0; i < workingInventory.size(); i++) {
            // ignore other vehicle type and Broken vehicle
            if (!(workingInventory.get(i).getVehicleType() == type) ||
                workingInventory.get(i).getVehicleCondition() == Vehicle.VehicleCondition.BROKEN) {
                continue;
            }

            if (mostExpensive == null || mostExpensive.getSalePrice() < workingInventory.get(i).getSalePrice()) {
                mostExpensive = workingInventory.get(i);
            }
        }

        return mostExpensive;
    }

    /**
     * Get most expensive vehicle for sale for any type. Vehicle must not be Broken.
     *
     * @return most expensive vehicle if exists, null otherwise
     */
    public Vehicle getMostExpensiveVehicleForSale() {
        if (workingInventory.size() <= 0) return null;
        Vehicle mostExpensive = workingInventory.get(0);
        for (int i = 1; i < workingInventory.size(); i++) {
            if (mostExpensive.getSalePrice() < workingInventory.get(i).getSalePrice()) {
                mostExpensive = workingInventory.get(i);
            }
        }
        return mostExpensive;
    }

    /**
     * Remove vehicle from working inventory and add to sold inventory
     *
     * Assumption: vehicle is not null
     *
     * @param vehicle moving Vehicle
     */
    public void moveVehicleToSoldVehicles(Vehicle vehicle) {
        workingInventory.remove(vehicle);
        soldVehicles.add(vehicle);
    }

    /**
     * Gets the three vehicles belonging to a randomly selected vehicle type if they are not broken,
     * they will participate in the race
     *
     * @param numVehiclesRequired: the max. number of vehicles required to participate in a given race
     * @return Arraylist of the qualified vehicles for a randomly selected vehicle type
     */
    public ArrayList<Vehicle> getVehiclesForRace(int numVehiclesRequired){
        // Only 4 types are for racing
        Vehicle.VehicleType[] availableVehicleTypesforRacing = {Vehicle.VehicleType.MONSTER_TRUCK,
                Vehicle.VehicleType.MOTORCYCLE, Vehicle.VehicleType.PERFORMANCE_CAR, Vehicle.VehicleType.PICKUP};
        // Select a random type for current racing
        int randomInt = RandomGenerator.randomIntGenerator(0,availableVehicleTypesforRacing.length-1);
        Vehicle.VehicleType randomlySelectedVehicleTypeforRacing = availableVehicleTypesforRacing[randomInt];

        // Get list of vehicle for selected type
        ArrayList<Vehicle> availableVehiclesByTypeArrayList =
                Vehicle.getVehicleListByType(workingInventory, randomlySelectedVehicleTypeforRacing);

        // Removing Broken vehicle from racing list
        ArrayList<Vehicle> dummyAvailableVehiclesByTypeArrayList = new ArrayList<>(availableVehiclesByTypeArrayList);
        for (Vehicle vehicle: dummyAvailableVehiclesByTypeArrayList) {
            if (vehicle.getVehicleCondition() == Vehicle.VehicleCondition.BROKEN){
                availableVehiclesByTypeArrayList.remove(vehicle);
            }
        }

        // Only select exact required number of vehicle, removing the rest from attendance
        while (availableVehiclesByTypeArrayList.size() > numVehiclesRequired){
            availableVehiclesByTypeArrayList.remove(availableVehiclesByTypeArrayList.get(0));
        }
        return availableVehiclesByTypeArrayList;
    }

    /**
     * get AvailableVehicles Types
     * @return vehicle Types Set
     */
    public HashSet<Vehicle.VehicleType> getAvailableVehiclesTypes() {
        HashSet<Vehicle.VehicleType> vehicleTypesSet = new HashSet<Vehicle.VehicleType>();
        for (Vehicle vehicle : getWorkingInventory()) {
            vehicleTypesSet.add(vehicle.getVehicleType());
            }
        return vehicleTypesSet ;
    }
    /**
     * let the buyer selects the vehicle and check its details for the available vehicles in the inventory to a
     * buyer via userInterface implementation
     */
    public Vehicle userInterfaceVehicleSelector(){
        HashSet<Vehicle.VehicleType> availableVehicleTypesSet = getAvailableVehiclesTypes();
        HashMap <Integer, Vehicle.VehicleType> availableVehicleTypes= new HashMap<Integer, Vehicle.VehicleType>();
        HashMap <Integer, Vehicle> availableVehiclesByType= new HashMap<Integer, Vehicle>();
        Scanner scanner = new Scanner(System.in);
        int userEntry;
        int counter = 1;
        System.out.println( "The available Vehicle types" );
        for (Vehicle.VehicleType type: availableVehicleTypesSet){
            availableVehicleTypes.put(counter, type);
            System.out.printf("%d) %s \n",counter, type);
            counter++;
        }
        System.out.println( "Enter the number corresponding to the Vehicle Type you want: " );
        userEntry = scanner.nextInt();
        Vehicle.VehicleType selectedVehicleType = availableVehicleTypes.get(userEntry);
        System.out.printf("You selected %s.\n", selectedVehicleType);

        ArrayList<Vehicle> availableVehiclesByTypeArrayList = Vehicle.getVehicleListByType(getWorkingInventory(),selectedVehicleType);
        counter = 1;
        System.out.printf( "The current available %s vehicles:\n", selectedVehicleType);
        for (Vehicle vehicle: availableVehiclesByTypeArrayList){
            availableVehiclesByType.put(counter, vehicle);
            System.out.printf("%d)  %s \n",counter, vehicle.getName());
            counter++;
        }
        System.out.println( "Enter the number corresponding to the Vehicle you want: " );
        userEntry = scanner.nextInt();
        Vehicle selectedVehicle = availableVehiclesByType.get(userEntry);
        System.out.printf("You selected (%s & %s) %s ($ %.0f).\n", selectedVehicle.getVehicleCondition(),
                selectedVehicle.getCleanliness(), selectedVehicle.getName(), selectedVehicle.getSalePrice());
        return selectedVehicle;
    }
    /**
     * shows the available vehicles in the inventory to a buyer via userInterface implementation
     */
    public void userInterfaceShowInventory(){
        int counter = 1;
        System.out.println( "The available Vehicles:" );
        for (Vehicle vehicle: getWorkingInventory()){
            System.out.printf("%d) (%s & %s) (%s) %s ($ %.0f).\n",counter, vehicle.getVehicleCondition(),
                    vehicle.getCleanliness(), vehicle.getVehicleType(), vehicle.getName(), vehicle.getSalePrice());
            counter++;
        }
    }


}
