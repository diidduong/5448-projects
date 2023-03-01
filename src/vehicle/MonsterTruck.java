package vehicle;

/**
 * represents the monster truck and is a subclass of vehicle
 * @author Duy Duong, Ahmed.H.Biby
 */
public class MonsterTruck extends Vehicle {
    /**
     * Constructor to initialize vehicle with type and with a random cost
     */
    public MonsterTruck() {
        super(VehicleType.MONSTER_TRUCK, 30000, 50000);
        System.out.printf("\nA %s and %s MONSTER_TRUCK (%s) is available in the inventory.\n",
                getCleanliness(),
                getVehicleCondition(),
                getName());
//        System.out.println(getCleanliness());              // for logic testing
//        System.out.println(getName());                      // for logic testing
//        System.out.println(getVehicleCondition());          // for logic testing
//        System.out.println(getSalePrice());                // for logic testing
    }
}
