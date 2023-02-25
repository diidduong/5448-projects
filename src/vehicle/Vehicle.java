package vehicle;

import staff.Staff;
import utilities.RandomGenerator;

import java.util.ArrayList;

/**
 * @author Duy Duong, Ahmed.H.Biby
 *
 * Data class of a Vehicle
 */
public abstract class Vehicle {
    public enum VehicleCondition {
        LIKE_NEW, USED, BROKEN
    }

    public enum Cleanliness {
        DIRTY, CLEAN, SPARKLING
    }

    public enum VehicleType {
        PERFORMANCE_CAR, CAR, PICKUP
    }

    private String name;
    private double initialCost;
    private double salePrice;  //NEW
    private double repairingBonus;
    private boolean isInStock;
    private Cleanliness cleanliness;
    private VehicleCondition vehicleCondition;
    private VehicleType vehicleType;

    /**
     * Constructor to intialize vehicle with type and with a random cost
     *
     * @param type Vehicle Type
     * @param lowestCost lowest cost
     * @param highestCost highest cost
     */
    public Vehicle(VehicleType type, double lowestCost, double highestCost) {
        this.vehicleType = type;
        this.name = RandomGenerator.pickupCarNameGenerator();
        this.cleanliness = RandomGenerator.RandomCleanlinessGenerator();
        this.vehicleCondition = RandomGenerator.RandomConditionGenerator();
        this.isInStock = true;
        // initialCost from cost range
        this.initialCost = RandomGenerator.randomIntGenerator(lowestCost,highestCost);
        // discounted initialCost after condition evaluation
        this.initialCost = VehicleInspector.calculateCost(vehicleCondition, initialCost);
        this.salePrice = VehicleInspector.calculatePrice(initialCost);

        System.out.printf("\nA %s and %s %s (%s) is available in the inventory.\n", getCleanliness(), getVehicleCondition(), type, getName());
        System.out.println(initialCost);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getInitialCost() {
        return initialCost;
    }

    public void setInitialCost(double initialCost) {
        this.initialCost = initialCost;
    }

    public double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
    }

    public double getRepairingBonus() {
        return repairingBonus;
    }

    public void setRepairingBonus(double repairingBonus) {
        this.repairingBonus = repairingBonus;
    }

    public boolean isInStock() {
        return isInStock;
    }

    public void setInStock(boolean inStock) {
        isInStock = inStock;
    }

    public Cleanliness getCleanliness() {
        return cleanliness;
    }

    public void setCleanliness(Cleanliness cleanliness) {
        this.cleanliness = cleanliness;
    }

    public VehicleCondition getVehicleCondition() {
        return vehicleCondition;
    }

    public void setVehicleCondition(VehicleCondition vehicleCondition) {
        this.vehicleCondition = vehicleCondition;
    }
    public VehicleType getVehicleType() {
        return vehicleType;
    }
    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    /**
     *
     * @param type
     * @return
     */
    public static Vehicle createVehicleByType(VehicleType type) {
        switch (type) {
            case PERFORMANCE_CAR:
                return new PerformanceCar();
            case CAR:
                return new Car();
            case PICKUP:
                return new Pickup();
            default:
                throw new IllegalArgumentException("Unknown vehicle type [" + type + "]");
        }
    }

    /**
     *
     * @param vehicles
     * @param type
     * @return
     */
    public static int countVehicleByType(ArrayList<Vehicle> vehicles, VehicleType type) {
        int count = 0;
        for (Vehicle vehicle : vehicles) {
            if (vehicle.vehicleType == type) {
                count++;
            }
        }
        return count;
    }

    /**
     * Upgrade Vehicle Condition to the next class
     * Broken to Used
     * Used to Like New
     */
    public void upgradeVehicleCondition() {
        if (vehicleCondition == VehicleCondition.BROKEN) {
            vehicleCondition = VehicleCondition.USED;
        } else if (vehicleCondition == VehicleCondition.USED) {
            vehicleCondition = VehicleCondition.LIKE_NEW;
        }
    }

    /**
     * Downgrade Vehicle Cleanliness by one class
     * Sparkling to Clean
     * Clean to Dirty
     */
    public void downgradeCleanliness() {
        if (cleanliness == Cleanliness.SPARKLING) {
            cleanliness = Cleanliness.CLEAN;
        } else if (cleanliness == Cleanliness.CLEAN) {
            cleanliness = Cleanliness.DIRTY;
        }
    }
}
