package activity;

import vehicle.Vehicle;

/**
 * The abstract class for parent of decorator.
 * @author Duy Duong, Ahmed.H.Biby
 */
public abstract class Sale {
    public enum AddOnType{
        UNDER_COATING, EXTENDED_WARRANTY, ROAD_RESCUE_COVERAGE, SATELLITE_RADIO
    }

    private AddOnType addOn;

    public abstract void addAddOnPrice(Vehicle vehicle, Sale.AddOnType addOnType);
}
