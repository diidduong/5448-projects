package vehicle;

import utilities.RandomGenerator;
/**
 * represents the golfCart and is a subclass of vehicle
 * @author Ahmed.H.Biby
 */
public class GolfCart extends Vehicle{
    private int maximumNumberOfPassengers;
    private int requiredChargingTime;
    public GolfCart() {
        super(VehicleType.GOLF_CART, 3000, 20000);
        maximumNumberOfPassengers = RandomGenerator.randomIntGenerator(2, 8);
        requiredChargingTime = RandomGenerator.randomIntGenerator(5, 12);
        // the sale price is affected by the requiredCharging time (hours) and maximumNumberOfPassengers
        setSalePrice(getSalePrice()*(1+ (1/requiredChargingTime)+ ((double) 2*maximumNumberOfPassengers/800)));
        System.out.printf("\nA %s and %s GolfCart %s is available in the inventory.\n", getCleanliness(),
                getVehicleCondition(), getName());
    }
    /**
     * GolfCart name will include maximumNumberOfPassengers and requiredChargingTime (hours)
     * @return name with maximumNumberOfPassengers and requiredChargingTime
     */
    @Override
    public String getName() {

        return String.format("%s (%d Passengers & %d hours chargingTime)", super.getName(),
                maximumNumberOfPassengers, requiredChargingTime);
    }
}
