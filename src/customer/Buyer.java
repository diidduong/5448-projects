package customer;

import utilities.RandomGenerator;
import vehicle.Vehicle;

/**
 * @author Duy Duong, Ahmed.H.Biby
 *
 * Buyer class that can be served at FNCD with sale Vehicles
 */
public class Buyer {
    public enum BuyingType {
        JUST_LOOKING(0.1), WANTS_ONE(0.4), NEEDS_ONE(0.7);

        private final double probability;

        BuyingType(double probability) {
            this.probability = probability;
        }

        public double getProbability() {
            return probability;
        }
    }

    private BuyingType buyingType;
    private Vehicle.VehicleType wantedType;

    public Buyer() {
        buyingType = RandomGenerator.getRandomBuyingType();
        wantedType = RandomGenerator.getRandomVehicleType();
    }

    public BuyingType getBuyingType() {
        return buyingType;
    }

    public void setBuyingType(BuyingType buyingType) {
        this.buyingType = buyingType;
    }

    public Vehicle.VehicleType getWantedType() {
        return wantedType;
    }

    public void setWantedType(Vehicle.VehicleType wantedType) {
        this.wantedType = wantedType;
    }
}
