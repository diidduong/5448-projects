package activity;

import utilities.RandomGenerator;
import vehicle.Vehicle;

/**
 * one of the concrete classes for the washingMethod (strategy pattern)
 * @author Duy Duong, Ahmed.H.Biby
 */
public class DetailedWashing implements WashMethod{
    @Override
    public void washVehicle(Vehicle vehicle) {
        if (vehicle.getCleanliness() == Vehicle.Cleanliness.DIRTY) {
            vehicle.setCleanliness(RandomGenerator.getRandomCleanliness(new double[]{0.2, 0.6, 0.2}));
        } else if (vehicle.getCleanliness() == Vehicle.Cleanliness.CLEAN) {
            vehicle.setCleanliness(RandomGenerator.getRandomCleanliness(new double[]{0.15, 0.7, 0.15}));
        }
    }
}
