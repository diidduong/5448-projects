package vehicle;

/**
 * represents the monster truck and is a subclass of vehicle
 * @author Ahmed.H.Biby
 */
public class MonsterTruck extends Vehicle {
    public MonsterTruck(int day) {
        super(VehicleType.MONSTER_TRUCK, 30000, 50000, day);
//        System.out.println(getCleanliness());              // for logic testing
//        System.out.println(getName());                      // for logic testing
//        System.out.println(getVehicleCondition());          // for logic testing
//        System.out.println(getSalePrice());                // for logic testing
    }
}
