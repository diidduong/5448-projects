package vehicle;

import utilities.FNCDAdministration;
import utilities.Inventory;
import utilities.RandomGenerator;

import java.util.HashMap;

/**
 * @author Duy Duong
 *
 * Subclass of Vehicle
 */
public class Pickup extends Vehicle {
    private final double LOWEST_COST = 10000;
    private final double HIGHEST_COST = 40000;
    private final VehicleType VEHICLETYPE = VehicleType.PICKUP;

    public Pickup(int day) {
        setName(RandomGenerator.pickupCarNameGenerator());
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
        String formattedDay = String.format("Day_%d_pickup_%s", day, getName().replace(' ', '_'));
        Inventory.inventoryRegistry.add(formattedDay, registryAction);
        Inventory.workingInventory.add(this);
    }

}
