package vehicle;

/**
 * @author Duy Duong
 *
 * Data class of a Vehicle
 */
public abstract class Vehicle {
    public enum VehicleCondition { //NEW
        LIKE_NEW, USED, BROKEN
    }

    public enum Cleanliness { //NEW
        DIRTY, CLEAN, SPARKING
    }

    private String name;
    private double initialCost;
    private double lowestCost;
    private double highestCost;
    private double salePrice;  //NEW
    private double repairingBonus;
    private Cleanliness cleanliness;
    private VehicleCondition vehicleCondition;

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
