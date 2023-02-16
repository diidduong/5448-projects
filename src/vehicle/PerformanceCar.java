package vehicle;

/**
 * @author Duy Duong, Ahmed.H.Biby
 *
 * Subclass of Vehicle
 */
public class PerformanceCar extends Vehicle {
    public PerformanceCar(int day) {
        super(VehicleType.PERFORMANCE_CAR, 20000, 40000, day);
    }
}