package activity;

import staff.Staff;
import utilities.Inventory;
import utilities.RandomGenerator;
import vehicle.Vehicle;

import java.util.ArrayList;

/**
 * @author Duy Duong
 *
 * Subclass of Activity which allow ability to perform Wash action
 */
public class Wash extends Activity {

    /**
     * Constructor that requires inventory
     *
     * @param provider assigned staff
     * @param inventory current inventory
     */
    public Wash(Staff provider, Inventory inventory) {
        super(provider, inventory, null, null);
    }

    /**
     * Daily wash that serves two vehicles
     */
    @Override
    public void performWork() {
        ArrayList<Vehicle> workingInventory = getInventory().getWorkingInventory();
        for (int i = 0; i < 2; i++) {
            Vehicle selectedVehicle = getNextVehicle(workingInventory);
            if (selectedVehicle == null) break; // no dirty/clean vehicle to wash
            washVehicle(selectedVehicle);
        }
    }

    /**
     * Wash a vehicle. Vehicles that are Sparkling will not be rewashed. A
     * Dirty Vehicle that is washed has an 80% chance of becoming Clean and a 10% chance of becoming
     * Sparkling. A Clean Vehicle has a 5% chance of becoming Dirty and a 30% chance of becoming Sparkling.
     * If an intern makes a Vehicle Sparkling, they earn a bonus by type of vehicle
     *
     * @param vehicle selected vehicle
     */
    public void washVehicle(Vehicle vehicle) {
        if (vehicle.getCleanliness() == Vehicle.Cleanliness.DIRTY) {
            vehicle.setCleanliness(RandomGenerator.getRandomCleanliness(new double[]{0.1, 0.8, 0.1}));
        } else if (vehicle.getCleanliness() == Vehicle.Cleanliness.CLEAN) {
            vehicle.setCleanliness(RandomGenerator.getRandomCleanliness(new double[]{0.3, 0.65, 0.05}));
        }
        if (vehicle.getCleanliness() == Vehicle.Cleanliness.SPARKING) {
            double bonus = getBonusByType(vehicle);
            getProvider().addBonus(bonus);
            System.out.printf("%s %s wash %s %s and made it %s (earned %.2f bonus)\n",
                    getProvider().getJobTitle(),
                    getProvider().getName(),
                    vehicle.getVehicleType(),
                    vehicle.getName(),
                    vehicle.getCleanliness(),
                    bonus
            );
        } else {
            System.out.printf("%s %s wash %s %s and made it %s\n",
                    getProvider().getJobTitle(),
                    getProvider().getName(),
                    vehicle.getVehicleType(),
                    vehicle.getName(),
                    vehicle.getCleanliness()
            );
        }
    }

    /**
     * Find next Dirty Vehicle to work on. If there isn't, find Clean Vehicle
     * <p>
     * Assumption: allows repeated selection
     *
     * @param vehicles all available vehicle in inventory
     * @return Dirty Vehicle if there is, then move on to Clean Vehicle, else null
     */
    private Vehicle getNextVehicle(ArrayList<Vehicle> vehicles) {
        Vehicle selectedVehicle = getFirstVehicleByCleanliness(vehicles, Vehicle.Cleanliness.DIRTY);
        if (selectedVehicle != null) {
            selectedVehicle = getFirstVehicleByCleanliness(vehicles, Vehicle.Cleanliness.CLEAN);
        }
        return selectedVehicle;
    }

    /**
     * Get first vehicle from the list that matches given cleanliness status
     *
     * @param vehicles    vehicle list
     * @param cleanliness cleanliness
     * @return first matched vehicle if exists, else null
     */
    private Vehicle getFirstVehicleByCleanliness(ArrayList<Vehicle> vehicles, Vehicle.Cleanliness cleanliness) {
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getCleanliness() == cleanliness) {
                return vehicle;
            }
        }
        return null;
    }

    /**
     * Return bonus for Sparkling washed vehicle by type
     * $75 for Performance Car
     * $50 for Car
     * $100 for Pickup
     *
     * @param vehicle selected vehicle
     * @return bonus value
     */
    private double getBonusByType(Vehicle vehicle) {
        switch (vehicle.getVehicleType()) {
            case PERFORMANCE_CAR:
                return 75;
            case CAR:
                return 50;
            case PICKUP:
                return 100;
            default:
                return 0;
        }
    }
}