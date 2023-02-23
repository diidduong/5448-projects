package activity;

import utilities.RandomGenerator;
import vehicle.Vehicle;

/**
 * one of the concrete methods for the mashingMethod (strategic pattern)
 * @author Ahmed.H.Biby
 */
public class ChemicalWashing extends WashingMethod {
    public void wash(Vehicle vehicle) {
        if (vehicle.getCleanliness() == Vehicle.Cleanliness.DIRTY) {
            vehicle.setCleanliness(RandomGenerator.getRandomCleanliness(new double[]{0.1, 0.8, 0.1}));
        } else if (vehicle.getCleanliness() == Vehicle.Cleanliness.CLEAN) {
            vehicle.setCleanliness(RandomGenerator.getRandomCleanliness(new double[]{0.2, 0.7, 0.1}));
        }
        if (RandomGenerator.probabilisticOutcomeGenerator(0.1)) {
            vehicle.setVehicleCondition(Vehicle.VehicleCondition.BROKEN);
        }
    }
}

