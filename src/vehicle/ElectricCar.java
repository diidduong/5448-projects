package vehicle;

import utilities.RandomGenerator;

/**
 * represents the electric car and is a subclass of vehicle
 * @author Duy Duong, Ahmed.H.Biby
 */
public class ElectricCar extends Vehicle {
    private int range;

    /**
     * Constructor to initialize electric car with cost range and random range
     */
    public ElectricCar() {
        super(VehicleType.ELECTRIC_CAR, 20000, 40000);
        range = RandomGenerator.randomIntGenerator(60,400);
        extendRangeIfLikeNew(); // extend range if arrives Like New
        System.out.printf("\nA %s and %s ELECTRIC_CAR %s is available in the inventory.\n", getCleanliness(),
                getVehicleCondition(), getName());
        //        System.out.println(this.getRange());                    // for logic testing
        //        System.out.println(this.getCleanliness());              // for logic testing
        //        System.out.println(this.getName());                      // for logic testing
        //        System.out.println(this.getVehicleCondition());          // for logic testing
        //        System.out.println(this.getSalePrice());                // for logic testing
    }

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }

    /**
     * Electric car name will include range
     * @return name with range
     */
    @Override
    public String getName() {
        // TODO: add to UML, use toString instead
        return String.format("%s (%d miles)", super.getName(), range);
    }

    /**
     * Method to update the range of electric car by 100 miles if like new
     */
    public void extendRangeIfLikeNew() {
        //TODO: update UML
        if (getVehicleCondition() == VehicleCondition.LIKE_NEW){
            range += 100;
            System.out.printf("The range was extended for %s (electric car) by 100 miles.\n", getName());
        }
    }
}
