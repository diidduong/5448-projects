package activity;

import vehicle.Vehicle;

/**
 * Interface class for all washing method implementations (strategy pattern)
 * @author Duy Duong, Ahmed.H.Biby
 */
public interface WashMethod {

    void washVehicle(Vehicle  vehicle);
}
