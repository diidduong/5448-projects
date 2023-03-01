package activity;

import vehicle.Vehicle;

/**
 * one of the concrete classes for the SaleAddOn (decorator pattern).
 * @author Duy Duong, Ahmed.H.Biby
 */
public class SatelliteRadioAddOn extends SaleAddOn {
    public SatelliteRadioAddOn(Vehicle vehicle) {
        super(vehicle,AddOnType.SATELLITE_RADIO);
        addAddOnPrice(vehicle, AddOnType.SATELLITE_RADIO);
    }
}
