package vehicle;

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
        PERFORMANCE_CAR, CAR, PICKUP, ELECTRIC_CAR, MOTORCYCLE, MONSTER_TRUCK
    }

    private String name;
    private double initialCost;
    private double salePrice;  //NEW
    private double repairingBonus;
    private boolean isInStock;
    private Cleanliness cleanliness;
    private VehicleCondition vehicleCondition;
    private VehicleType vehicleType;
    private int racesWon;

    /**
     * Constructor to initialize vehicle with type and with a random cost
     *
     * @param type Vehicle Type
     * @param lowestCost lowest cost
     * @param highestCost highest cost
     */
    public Vehicle(VehicleType type, double lowestCost, double highestCost) {
        this.vehicleType = type;
        this.name = RandomGenerator.vehicleNameGenerator(type);
        this.cleanliness = RandomGenerator.RandomCleanlinessGenerator();
        this.vehicleCondition = RandomGenerator.RandomConditionGenerator();
        this.isInStock = true;
        // initialCost from cost range
        double initialCost = RandomGenerator.randomIntGenerator(lowestCost,highestCost);
        // discounted initialCost after condition evaluation
        this.initialCost = calculateCost(vehicleCondition, initialCost);
        this.salePrice = calculatePrice(initialCost);
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

    public int getRacesWon() {
        return racesWon;
    }

    public void setRacesWon(int racesWon) {
        this.racesWon = racesWon;
    }

    /**
     * Util to create vehicle instance by type
     * Reference: Project 2 example solution
     *
     * @param type vehicle type
     * @return vehicle instance
     */
    public static Vehicle createVehicleByType(VehicleType type) {
        switch (type) {
            case PERFORMANCE_CAR:
                return new PerformanceCar();
            case CAR:
                return new Car();
            case PICKUP:
                return new Pickup();
            case ELECTRIC_CAR:
                return new ElectricCar();
            case MOTORCYCLE:
                return new MotorCycle();
            case MONSTER_TRUCK:
                return new MonsterTruck();
            default:
                throw new IllegalArgumentException("Unknown vehicle type [" + type + "]");
        }
    }

    /**
     * Util to count a given vehicle type in the list
     * Reference: Project 2 example solution class Vehicle.howManyVehiclesByType
     *
     * @param vehicles list of vehicle
     * @param type vehicle type
     * @return count of vehicle that matches given type
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
     * Get vehicle list by given type
     * Reference: Project 2 example solution class Vehicle.getVehiclesByType
     *
     * @param vehicles list of all vehicles
     * @param type vehicle type
     * @return array list for given vehicle type
     */
    public static ArrayList<Vehicle> getVehicleListByType(ArrayList<Vehicle> vehicles, VehicleType type) {
        ArrayList<Vehicle> vehicleList = new ArrayList<>();
        for (Vehicle v : vehicles) {
            if (v.vehicleType == type) {
                vehicleList.add(v);
            }
        }
        return vehicleList;
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

    /**
     * Calculate vehicle initial cost based on vehicle condition.
     * - Reduced by 20% if vehicle is Used
     * - Reduced by 50% if vehicle is Broken
     *
     * @param condition current Vehicle Condition
     * @return actual initialCost with appropriate discount
     */
    public static double calculateCost(Vehicle.VehicleCondition condition, double initialCost) {
        switch (condition) {
            case USED:
                return initialCost * 0.8; //reduced 20%
            case BROKEN:
                return initialCost * 0.5; //reduced 50%
            default:
                return initialCost;
        }
    }

    /**
     * Calculate price which equals 2x initial cost
     *
     * @param initialCost vehicle initial cost, positive
     * @return sale price
     */
    public static double calculatePrice(double initialCost) {
        return initialCost * 2;
    }
}
