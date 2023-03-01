package vehicle;

import utilities.RandomGenerator;

/**
 * represents the motorcycle and is a subclass of vehicle
 * @author Ahmed.H.Biby
 */
public class MotorCycle extends Vehicle {
    private int engineSize;

    /**
     * Constructor to intialize vehicle with type and with a random cost
     */
    public MotorCycle() {
        super(VehicleType.MOTORCYCLE, 8000, 25000);
        engineSize = RandomGenerator.randomIntFromNormalDistributionWithMeanAndStdWithMinimum(700,300,50);
        System.out.printf("\nA %s and %s MOTORCYCLE %s is available in the inventory.\n",
                getCleanliness(),
                getVehicleCondition(), getName()
        );
//        System.out.println(this.getCleanliness());              // for logic testing
//        System.out.println(this.getName());                      // for logic testing
//        System.out.println(this.getVehicleCondition());          // for logic testing
//        System.out.println(this.getSalePrice());                // for logic testing
    }

    @Override
    public String getName() {
        return String.format("%s (%d CC engine)", super.getName(), engineSize);
    }

    public int getEngineSize() {
        return engineSize;
    }

    public void setEngineSize(int engineSize) {
        this.engineSize = engineSize;
    }
}
