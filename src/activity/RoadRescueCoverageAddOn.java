package activity;

import vehicle.Vehicle;

/**
 * one of the concrete classes for the SaleAddOn (decorator pattern).
 * @author Duy Duong, Ahmed.H.Biby
 */
public class RoadRescueCoverageAddOn extends SaleAddOn {
    public RoadRescueCoverageAddOn(Vehicle vehicle) {
        super(vehicle, AddOnType.ROAD_RESCUE_COVERAGE);
        addAddOnPrice(vehicle, AddOnType.ROAD_RESCUE_COVERAGE);
    }
}
