package staff;

import activity.ChemicalWashing;
import activity.DetailedWashing;
import activity.ElbowGreaseWashing;
import activity.WashMethod;
import tracking.EventPublisher;
import tracking.Message;
import utilities.RandomGenerator;
import vehicle.Vehicle;

import java.util.ArrayList;

/**
 * @author Duy Duong, Ahmed.H.Biby
 *
 * Intern is subclass of Staff with JobTitle and daily Rate
 */
public class Intern extends Staff {
    WashMethod washMethod;

    public Intern(){
        super(JobTitle.INTERN, 80);
        washMethod = randomWashingMethod(); // assign random wash method when initialize an intern
    }

    /**
     * randomly choose the washing method
     */
    public WashMethod randomWashingMethod(){
        int randomWashingMethodSelector = RandomGenerator.randomIntGenerator(0, 100);
        if (randomWashingMethodSelector <= 33) {
            return new ChemicalWashing();
        } else if (randomWashingMethodSelector <= 66) {
            return new ElbowGreaseWashing();
        } else {
            return  new DetailedWashing();
        }
    }

    /**
     * Method to perfom washing activity
     *
     * @param vehicles all vehicle in working inventory
     */
    public void washVehicles(ArrayList<Vehicle> vehicles, EventPublisher publisher) {
        for (int i = 0; i < 2; i++) {
            Vehicle selectedVehicle = getNextVehicle(vehicles);
            if (selectedVehicle == null) break; // no dirty/clean vehicle to wash
            washVehicle(selectedVehicle, publisher);
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
    public void washVehicle(Vehicle vehicle, EventPublisher publisher) {
        //TODO: remove comments when new code working

        washMethod.washVehicle(vehicle); // wash vehicle with given method
        if (vehicle.getCleanliness() == Vehicle.Cleanliness.SPARKLING) {
            double bonus = getBonusByType(vehicle);
            addBonus(bonus);
            String msg = String.format("%s %s wash (%s) %s %s and made it %s (earned %.2f bonus)\n",
                    getJobTitle(),
                    getName(),
                    washMethod.getClass().getSimpleName(),
                    vehicle.getVehicleType(),
                    vehicle.getName(),
                    vehicle.getCleanliness(),
                    bonus
            );
            publisher.notifySubscribers(new Message(msg, bonus, 0));
        } else {
            String msg = String.format("%s %s wash (%s) %s %s and made it %s\n",
                    getJobTitle(),
                    getName(),
                    washMethod.getClass().getSimpleName(),
                    vehicle.getVehicleType(),
                    vehicle.getName(),
                    vehicle.getCleanliness()
            );
            publisher.notifySubscribers(new Message(msg, 0, 0));
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
        if (selectedVehicle == null) {
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
            case MOTORCYCLE:
                return 25;
            case MONSTER_TRUCK:
                return 200;
            case ELECTRIC_CAR:
                return 120;
            default:
                return 0;
        }
    }
}