package vehicle;

import utilities.RandomGenerator;

/**
 * represents the dozer and is a subclass of vehicle
 * @author Ahmed.H.Biby
 */
public class Dozer extends Vehicle {
    private int horsePower;
    public Dozer() {
        super(VehicleType.DOZER, 75000, 12000);
        horsePower = RandomGenerator.randomIntGenerator(250, 1000);
        // the sale price is affected by horsePower
        setSalePrice(getSalePrice()*(1+ ((double) horsePower/8000)));
        System.out.printf("\nA %s and %s DOZER %s is available in the inventory.\n", getCleanliness(),
                getVehicleCondition(), getName());
    }

    /**
     * Dozer name will include horsePower
     * @return name with horsePower
     */
    @Override
    public String getName() {

        return String.format("%s (%d HorsePower)", super.getName(), horsePower);
    }
}
