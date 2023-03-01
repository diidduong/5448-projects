package activity;

import vehicle.Vehicle;

/**
 * one of the concrete classes for the SaleAddOn (decorator pattern).
 * @author Duy Duong, Ahmed.H.Biby
 */
public class ExtendedWarrantyAddOn extends SaleAddOn {
    public ExtendedWarrantyAddOn(Vehicle vehicle) {
        super(vehicle, AddOnType.EXTENDED_WARRANTY);
        addAddOnPrice(vehicle, AddOnType.EXTENDED_WARRANTY);
    }
}
