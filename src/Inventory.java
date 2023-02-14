import vehicle.Vehicle;

import java.util.ArrayList;

public class Inventory {
    private final ArrayList<Vehicle> vehicleList;

    public Inventory() {
        vehicleList = new ArrayList<>();
    }

    public ArrayList<Vehicle> getVehicleList() {
        return vehicleList;
    }

    /**
     * Get most expensive vehicle for sale by type. Vehicle must not be Broken.
     *
     * @param type vehicle type
     * @return most expensive vehicle by type if exists, null otherwise
     */
    public Vehicle getMostExpensiveVehicleForSaleByType(String type) {
        if (vehicleList.size() <= 0) return null;
        Vehicle mostExpensive = null;
        for (int i = 1; i < vehicleList.size(); i++) {
            // ignore other vehicle type and Broken vehicle
            if (!vehicleList.get(i).getClass().getSimpleName().equals(type) ||
                vehicleList.get(i).getVehicleCondition() == Vehicle.VehicleCondition.BROKEN) {
                continue;
            }

            if (mostExpensive == null || mostExpensive.getSalePrice() < vehicleList.get(i).getSalePrice()) {
                mostExpensive = vehicleList.get(i);
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
        if (vehicleList.size() <= 0) return null;
        Vehicle mostExpensive = vehicleList.get(0);
        for (int i = 1; i < vehicleList.size(); i++) {
            if (mostExpensive.getSalePrice() < vehicleList.get(i).getSalePrice()) {
                mostExpensive = vehicleList.get(i);
            }
        }
        return mostExpensive;
    }
}
