package staff;

/**
 * @author Duy Duong, Ahmed.H.Biby
 *
 * Mechanic is subclass of Staff which has JobTitle and dailyRate
 */
public class Mechanic extends Staff {
    public Mechanic(int day) {
        super(day, JobTitle.MECHANIC, 120);
    }
}