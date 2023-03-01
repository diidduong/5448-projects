package vehicle;

/**
 * @author Duy Duong, Ahmed.H.Biby
 *
 * Subclass of Vehicle
 */
public class Pickup extends Vehicle {
    public Pickup() {
        super(VehicleType.PICKUP, 10000, 40000);
        System.out.printf("\nA %s and %s PICKUP (%s) is available in the inventory.\n",
                getCleanliness(),
                getVehicleCondition(),
                getName());
    }
}
