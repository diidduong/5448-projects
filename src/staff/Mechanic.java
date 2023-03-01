package staff;

import tracking.EventPublisher;
import tracking.Message;
import utilities.RandomGenerator;
import vehicle.ElectricCar;
import vehicle.Vehicle;

import java.util.ArrayList;

/**
 * @author Duy Duong, Ahmed.H.Biby
 *
 * Mechanic is subclass of Staff which has JobTitle and dailyRate
 */
public class Mechanic extends Staff {
    public Mechanic() {
        super(JobTitle.MECHANIC, 120);
    }

    public void repairVehicles(ArrayList<Vehicle> vehicles, EventPublisher publisher) {
        for (int i = 0; i < 2; i++) {
            Vehicle selectedVehicle = getNextVehicle(vehicles);
            if (selectedVehicle == null) break; // no vehicle to repair
            repairVehicle(selectedVehicle, publisher);
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
    public void repairVehicle(Vehicle vehicle, EventPublisher publisher) {
        boolean fixable = RandomGenerator.probabilisticOutcomeGenerator(0.8);
        Vehicle.VehicleCondition prevCondition = vehicle.getVehicleCondition();
        if (fixable) {
            vehicle.upgradeVehicleCondition();
            double salePriceBonus = getSalePriceBonusByCondition(vehicle);
            vehicle.setSalePrice(vehicle.getSalePrice() + salePriceBonus); // increase sale price

            // Save bonus record
            double bonus = getBonusByType(vehicle);
            addBonus(bonus);

            // output repair result
            String msg = String.format("%s %s repaired %s %s %s and made it %s (earned $%.2f bonus)\n",
                    getJobTitle(),
                    getName(),
                    prevCondition,
                    vehicle.getVehicleType(),
                    vehicle.getName(),
                    vehicle.getVehicleCondition(),
                    bonus
            );
            publisher.notifySubscribers(new Message(msg, bonus, 0));

            // Increase range for Like New Electric Car
            if (vehicle.getVehicleType() == Vehicle.VehicleType.ELECTRIC_CAR){
                ((ElectricCar) vehicle).extendRangeIfLikeNew();
            }
        } else {
            String msg = String.format("%s %s repaired %s %s %s unsuccessfully\n",
                    getJobTitle(),
                    getName(),
                    prevCondition,
                    vehicle.getVehicleType(),
                    vehicle.getName()
            );
            publisher.notifySubscribers(new Message(msg, 0, 0));
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
     * @param vehicle current vehicle
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
            case ELECTRIC_CAR:
                return 300;
            case MOTORCYCLE:
                return 80;
            case MONSTER_TRUCK:
                return 500;
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