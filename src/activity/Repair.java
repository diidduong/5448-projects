package activity;

import staff.Staff;
import utilities.Inventory;
import utilities.RandomGenerator;
import vehicle.Vehicle;

import java.util.ArrayList;

/**
 * @author Duy Duong, Ahmed.H.Biby
 *
 * Subclass of Activity which allows ability to perform Repair action
 */
public class Repair extends Activity {

    private double servicePrice;

    private double successProbability = 0.8;

    public Repair(Staff provider, Inventory inventory) {
        super(provider, inventory, null, null);
    }


    /**
     * Perform all repair action. Each can work on 2 Vehicles at max per day
     */
    @Override
    public void performWork() {
        ArrayList<Vehicle> vehicles = getInventory().getWorkingInventory();
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
        boolean fixable = RandomGenerator.probabilisticOutcomeGenerator(successProbability);
        Vehicle.VehicleCondition prevCondition = vehicle.getVehicleCondition();
        if (fixable) {
            vehicle.upgradeVehicleCondition();
            double salePriceBonus = getSalePriceBonusByCondition(vehicle);
            vehicle.setSalePrice(vehicle.getSalePrice() + salePriceBonus); // increase sale price
            double bonus = getBonusByType(vehicle);
            getProvider().addBonus(bonus);
            System.out.printf("%s %s repaired %s %s %s and made it %s (earned $%.2f bounus)\n",
                    getProvider().getJobTitle(),
                    getProvider().getName(),
                    prevCondition,
                    vehicle.getVehicleType(),
                    vehicle.getName(),
                    vehicle.getVehicleCondition(),
                    bonus
            );
        } else {
            System.out.printf("%s %s repaired %s %s %s unsuccessfully\n",
                    getProvider().getJobTitle(),
                    getProvider().getName(),
                    prevCondition,
                    vehicle.getVehicleType(),
                    vehicle.getName()
            );
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
     * @param vehicle
     * @return bonus value
     */
    private double getBonusByType(Vehicle vehicle) {
        switch (vehicle.getVehicleType()) {
            case PERFORMANCE_CAR:
                return 200;
            case CAR:
                return 100;
            case PICKUP:
                return 150;
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
