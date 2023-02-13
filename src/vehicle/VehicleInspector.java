package vehicle;

public class VehicleInspector {
    /**
     * Calculate vehicle initial cost based on vehicle condition.
     * - Reduced by 20% if vehicle is Used
     * - Reduced by 50% if vehicle is Broken
     *
     * @param vehicle current vehicle
     * @return actual initialCost with appropriate discount
     */
    public static double calculateCost(Vehicle vehicle, double initialCost) {
        Vehicle.VehicleCondition condition = vehicle.getVehicleCondition();

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
     * @param vehicle current vehicle
     * @return sale price
     */
    public static double calculatePrice(Vehicle vehicle) {
        return vehicle.getInitialCost() * 2;
    }

    /**
     * Update vehicle condition to given status
     *
     * Assumption: if condition is null, don't update vehicle condition
     *
     * @param vehicle current vehicle
     * @param condition wanted condition
     */
    public static void updateVehicleCondition(Vehicle vehicle, Vehicle.VehicleCondition condition) {
        if (condition != null) {
            vehicle.setVehicleCondition(condition);
        }
    }

    /**
     * Update vehicle cleanliness to given status
     * Assumption: if cleanliness is null, don't update vehicle cleanliness
     *
     * @param vehicle current vehicle
     * @param cleanliness wanted cleanliness
     */
    public static void updateVehicleCleanliness(Vehicle vehicle, Vehicle.Cleanliness cleanliness) {
        if (cleanliness != null) {
            vehicle.setCleanliness(cleanliness);
        }
    }
}
