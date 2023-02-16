package vehicle;

import utilities.RandomGenerator;

import java.util.HashMap;

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
        DIRTY, CLEAN, SPARKING
    }

    public enum VehicleType {
        PERFORMANCE_CAR, CAR, PICKUP
    }

    private String name;
    private double initialCost;
    private double lowestCost;
    private double highestCost;
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
     * @param day current day
     */
    public Vehicle(VehicleType type, double lowestCost, double highestCost, int day) {
        this.vehicleType = type;
        this.name = RandomGenerator.pickupCarNameGenerator();
        this.cleanliness = RandomGenerator.RandomCleanlinessGenerator();
        this.vehicleCondition = RandomGenerator.RandomConditionGenerator();
        this.isInStock = true;
        this.lowestCost = lowestCost;
        this.highestCost = highestCost;
        // initialCost from cost range
        this.initialCost = RandomGenerator.randomIntGenerator(getLowestCost(),getHighestCost());
        // discounted initialCost after condition evaluation
        this.initialCost = VehicleInspector.calculateCost(getVehicleCondition(), getInitialCost());
        this.salePrice = VehicleInspector.calculatePrice(getInitialCost());

        HashMap<String, String> registryAction = new HashMap<>();
        System.out.printf("\nA %s and %s %s (%s) is available in the inventory.\n", getCleanliness(), getVehicleCondition(), type, getName());
        registryAction.put("name", getName());
        registryAction.put("condition", String.valueOf(getVehicleCondition()));
        registryAction.put("cleanliness", String.valueOf(getCleanliness()));
        String formattedDay = String.format("Day_%d_%s_%s", day, getVehicleType(), getName().replace(' ', '_'));

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

    public double getLowestCost() {
        return lowestCost;
    }

    public void setLowestCost(double lowestCost) {
        this.lowestCost = lowestCost;
    }

    public double getHighestCost() {
        return highestCost;
    }

    public void setHighestCost(double highestCost) {
        this.highestCost = highestCost;
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
        if (cleanliness == Cleanliness.SPARKING) {
            cleanliness = Cleanliness.CLEAN;
        } else if (cleanliness == Cleanliness.CLEAN) {
            cleanliness = Cleanliness.DIRTY;
        }
    }
}
