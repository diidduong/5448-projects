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

    public Vehicle getMostExpensiveVehicleByType(String type) {
        return null;
    }

    public Vehicle getMostExpensiveVehicle() {
        return null;
    }
}
