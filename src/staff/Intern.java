package staff;

/**
 * @author Duy Duong, Ahmed.H.Biby
 *
 * Intern is subclass of Staff with JobTitle and daily Rate
 */
public class Intern extends Staff {
    public Intern(int day){
        super(day, JobTitle.INTERN, 80);
    }
}