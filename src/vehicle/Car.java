package vehicle;

import utilities.Inventory;
import utilities.RandomGenerator;

import java.util.HashMap;

/**
 * @author Duy Duong
 *
 * Subclass of Vehicle
 */
public class Car extends Vehicle {
    public double getLOWEST_COST() {
        return LOWEST_COST;
    }

    public double getHIGHEST_COST() {
        return HIGHEST_COST;
    }

    private final double LOWEST_COST = 10000;
    private final double HIGHEST_COST = 20000;

    @Override
    public double getInitialCost() {
        return initialCost;
    }

    @Override
    public void setInitialCost(double initialCost) {
        this.initialCost = initialCost;
    }

    private double initialCost;

    private final VehicleType VEHICLETYPE = VehicleType.CAR;

    public Car(int day) {
        setName(RandomGenerator.normalCarNameGenerator());
        setCleanliness(RandomGenerator.RandomCleanlinessGenerator());
        setVehicleCondition(RandomGenerator.RandomConditionGenerator());
        setInitialCost(RandomGenerator.randomDoubleGenerator(getLowestCost(),getHighestCost()));
        setInitialCost(VehicleInspector.calculateCost(this, getInitialCost()));
        setSalePrice(VehicleInspector.calculatePrice(this));
        HashMap<String, String> registryAction = new HashMap<String, String>();
        System.out.printf("\nA %s and %s %s is available in the inventory.\n", getCleanliness(), getVehicleCondition(), getName());
        registryAction.put("name", getName());
        registryAction.put("condition", String.valueOf(getVehicleCondition()));
        registryAction.put("cleanliness", String.valueOf(getCleanliness()));
        String formattedDay = String.format("Day_%d_car_%s", day, getName().replace(' ', '_'));
        Inventory.inventoryRegistry.add(formattedDay, registryAction);
        Inventory.workingInventory.add(this);
    }

}
