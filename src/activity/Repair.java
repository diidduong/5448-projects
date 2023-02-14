package activity;

import staff.Staff;
import utilities.RandomGenerator;
import vehicle.Vehicle;

import java.util.ArrayList;

/**
 * @author Duy Duong
 *
 * Subclass of Activity which allows ability to perform Repair action
 */
public class Repair extends Activity {
    public Repair(Staff provider) {
        super(provider);
    }

    /**
     * Perform all repair action. Each can work on 2 Vehicles at max per day
     *
     * @param vehicles all vehicles
     */
    public void performRepair(ArrayList<Vehicle> vehicles) {
        for (int i = 0; i < 2; i++) {
            Vehicle selectedVehicle = getNextVehicle(vehicles);
            if (selectedVehicle == null) break; // no vehicle to repair
            repairVehicle(selectedVehicle);

        }
    }

    /**
     * Repair selected vehicle. Broken Vehicles that are fixed become Used. Used
     * Vehicles that are fixed become Like New. Employee has an 80% chance of fixing any Vehicle
     * worked on. Whether fixed or not, any Vehicle worked on will go down one class of cleanliness (if not
     * already Dirty). Mechanics receive a bonus from each successful repair by
     * Vehicle type.
     *
     * @param vehicle selected vehicle
     */
    public void repairVehicle(Vehicle vehicle) {
        boolean fixable = RandomGenerator.probabilisticOutcomeGenerator(0.8);
        if (fixable) {
            vehicle.upgradeVehicleCondition();
            double salePriceBonus = getSalePriceBonusByCondition(vehicle);
            vehicle.setSalePrice(vehicle.getSalePrice() + salePriceBonus); // increase sale price
        }
        vehicle.downgradeCleanliness();
    }

    /**
     * Get next vehicle to repair with status either Broken or Used
     *
     * @param vehicles vehicle list
     * @return Broken/Used vehicle if exists, null otherwise
     */
    private Vehicle getNextVehicle(ArrayList<Vehicle> vehicles) {
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getVehicleCondition() != Vehicle.VehicleCondition.LIKE_NEW) {
                return vehicle;
            }
        }
        return null;
    }

    /**
     * Get bonus for employee for each successful repair
     * $200 if vehicle becomes USED
     * $300 if vehicle becomes LIKE_NEW
     *
     * Assumption: this method is used for fixed vehicle
     *
     * @param condition vehicle type
     * @return bonus value
     */
    private double getBonusByType(String condition) {
        switch (condition) {
            case "Used":
                return 200;
            case "LIKE_NEW":
                return 300;
            default:
                return 0;
        }
    }

    /**
     * Get bonus for sale price for fixed vehicle by vehicle condition. Sale price increase by
     * 50% if vehicle becomes Used
     * 25% if vehicle becomes Like New
     *
     * Assumption: this method is used for fixed vehicle
     *
     * @param vehicle fixed vehicle
     * @return sale price bonus value
     */
    private double getSalePriceBonusByCondition(Vehicle vehicle) {
        double currentSalePrice = vehicle.getSalePrice();
        switch (vehicle.getVehicleCondition()) {
            case USED:
                return currentSalePrice * 0.5;
            case LIKE_NEW:
                return currentSalePrice * 0.25;
            default:
                return 0;
        }
    }
}
