package staff;

/**
 * @author Duy Duong, Ahmed.H.Biby
 *
 * Salesperson is subclass of Staff which has JobTitle and dailyRate
 */
public class Salesperson extends Staff {
    public Salesperson(int day){
        super(day, JobTitle.SALESPERSON, 150);
    }
}