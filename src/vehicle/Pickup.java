package vehicle;

/**
 * @author Duy Duong, Ahmed.H.Biby
 *
 * Subclass of Vehicle
 */
public class Pickup extends Vehicle {
    public Pickup(int day) {
        super(VehicleType.PICKUP, 10000, 40000, day);
    }

}
