package activity;

import vehicle.Vehicle;

/**
 * one of the concrete classes for the SaleAddOn (decorator pattern).
 * @author Duy Duong, Ahmed.H.Biby
 */
public class UnderCoatingAddOn extends SaleAddOn{
    public UnderCoatingAddOn(Vehicle vehicle) {
        super(vehicle, AddOnType.UNDER_COATING);
        addAddOnPrice(vehicle, AddOnType.UNDER_COATING);
    }
}
