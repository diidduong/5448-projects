package vehicle;

import utilities.RandomGenerator;
/**
 * represents the electric car and is a subclass of vehicle
 * @author Ahmed.H.Biby
 */
public class ElectricCar extends Vehicle {
    private final int racesWon = getRacesWon();

    private double range;
    public double getRange() {
        return range;
    }

    public void setRange(double range) {
        this.range = range;
    }




    public ElectricCar(int day) {
        super(VehicleType.ELECTRIC_CAR, 20000, 40000, day);
        setRange(RandomGenerator.randomIntGenerator(60,400));
//        System.out.println(this.getRange());                    // for logic testing
//        System.out.println(this.getCleanliness());              // for logic testing
//        System.out.println(this.getName());                      // for logic testing
//        System.out.println(this.getVehicleCondition());          // for logic testing
//        System.out.println(this.getSalePrice());                // for logic testing

    }

    /**
     * extends the range of a given electric vehicle if its condition is LIKE_NEW
     */
    public void extendRangeIfLikeNew(){
        if (getVehicleCondition() == VehicleCondition.LIKE_NEW){
            setRange(getRange() + 100);
            System.out.printf("The range was extended for %s (electric car)\n\n", getName());
        }
    }
}
