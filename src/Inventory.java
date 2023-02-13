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
        if (vehicleList.size() <= 0) return null;
        Vehicle mostExpensive = null;
        for (int i = 1; i < vehicleList.size(); i++) {
            if (!vehicleList.get(i).getClass().getSimpleName().equals(type)) continue;

            if (mostExpensive == null || mostExpensive.getSalePrice() < vehicleList.get(i).getSalePrice()) {
                mostExpensive = vehicleList.get(i);
            }
        }

        return mostExpensive;
    }

    public Vehicle getMostExpensiveVehicle() {
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
