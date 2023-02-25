package utilities;

import vehicle.Vehicle;

import java.util.ArrayList;


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
        vehicle.setInStock(false);
        workingInventory.remove(vehicle);
        soldVehicles.add(vehicle);
    }
}
