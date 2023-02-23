package vehicle;

/**
 * @author Duy Duong, AHmed.H.Biby
 *
 * Subclass of Vehicle
 */
public class Car extends Vehicle {
    private final int racesWon = getRacesWon();
    public Car(int day) {
        super(VehicleType.CAR, 10000, 20000, day);
    }
}
