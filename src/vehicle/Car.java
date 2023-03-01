package vehicle;

/**
 * @author Duy Duong, Ahmed.H.Biby
 *
 * Subclass of Vehicle
 */
public class Car extends Vehicle {
    public Car() {
        super(VehicleType.CAR, 10000, 20000);
        System.out.printf("\nA %s and %s CAR (%s) is available in the inventory.\n",
                getCleanliness(),
                getVehicleCondition(),
                getName());
    }
}
