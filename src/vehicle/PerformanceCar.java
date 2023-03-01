package vehicle;

/**
 * @author Duy Duong, Ahmed.H.Biby
 *
 * Subclass of Vehicle
 */
public class PerformanceCar extends Vehicle {
    public PerformanceCar() {
        super(VehicleType.PERFORMANCE_CAR, 20000, 40000);
        System.out.printf("\nA %s and %s PERFORMANCE_CAR (%s) is available in the inventory.\n",
                getCleanliness(),
                getVehicleCondition(),
                getName());
    }
}