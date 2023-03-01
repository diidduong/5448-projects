package activity;

import vehicle.Vehicle;

/**
 * The abstract class for the decorator pattern.
 * @author Duy Duong, Ahmed.H.Biby
 */
public class SaleAddOn extends Sale {
    private Sale.AddOnType addOn;

    public double getAddOnPrice() {
        return addOnPrice;
    }

    public void setAddOnPrice(double addOnPrice) {
        this.addOnPrice = addOnPrice;
    }

    private double addOnPrice;

    public Sale.AddOnType getAddOn() {
        return addOn;
    }

    public void setAddOn(Sale.AddOnType addOn) {
        this.addOn = addOn;
    }

    public SaleAddOn(Vehicle vehicle, Sale.AddOnType addOnType){
        addAddOnPrice(vehicle, addOnType);
    }

    /**
     * Get addOn price for each type of addon
     * @param vehicle current vehicle for sale
     * @param addOnType addon type
     */
    @Override
    public void addAddOnPrice(Vehicle vehicle, Sale.AddOnType addOnType) {
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
