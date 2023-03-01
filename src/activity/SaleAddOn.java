package activity;

import vehicle.Vehicle;

/**
 * The abstract class for the decorator pattern.
 * @author Ahmed.H.Biby
 */
public class SaleAddOn {
    public enum AddOnType{
        UNDER_COATING, EXTENDED_WARRANTY, ROAD_RESCUE_COVERAGE, SATELLITE_RADIO
    }

    private AddOnType addOn;

    public double getAddOnPrice() {
        return addOnPrice;
    }

    public void setAddOnPrice(double addOnPrice) {
        this.addOnPrice = addOnPrice;
    }

    private double addOnPrice;

    public AddOnType getAddOn() {
        return addOn;
    }

    public void setAddOn(AddOnType addOn) {
        this.addOn = addOn;
    }

    public SaleAddOn(Vehicle vehicle, AddOnType addOnType){
        addAddOnPrice(vehicle, addOnType);
    }

    public void addAddOnPrice(Vehicle vehicle, AddOnType addOnType) {
        switch (addOnType){
            case UNDER_COATING:
                setAddOnPrice(vehicle.getSalePrice() * 0.05);
                break;
            case EXTENDED_WARRANTY:
                setAddOnPrice(vehicle.getSalePrice() * 0.2);
                break;
            case ROAD_RESCUE_COVERAGE:
                setAddOnPrice(vehicle.getSalePrice() * 0.02);
                break;
            case SATELLITE_RADIO:
                setAddOnPrice(vehicle.getSalePrice() * 0.05);
                break;
        }
    }
}
