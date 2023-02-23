package vehicle;

import utilities.RandomGenerator;
/**
 * represents the motorcycle and is a subclass of vehicle
 * @author Ahmed.H.Biby
 */
public class MotorCycle extends Vehicle{
    private int engineSize;
    public int getEngineSize() {
        return engineSize;
    }

    public void setEngineSize(int engineSize) {
        this.engineSize = engineSize;
    }
    public MotorCycle(int day) {
        super(VehicleType.MOTORCYCLE, 8000, 25000, day);
        setEngineSize(RandomGenerator.randomIntFromNormalDistributionWithMeanAndStdWithMinimum(700,300,50));
//        System.out.println(this.getCleanliness());              // for logic testing
//        System.out.println(this.getName());                      // for logic testing
//        System.out.println(this.getVehicleCondition());          // for logic testing
//        System.out.println(this.getSalePrice());                // for logic testing

    }
}
