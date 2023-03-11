package staff;

/**
 * @author Duy Duong, Ahmed.H.Biby
 *
 * Factory class to create staff products
 */
public class StaffFactory {
    /**
     * Factory method to create staff by given title
     *
     * @param title wanted title
     * @return a staff object
     */
    public static Staff createStaff(Staff.JobTitle title) {
        switch (title) {
            case MECHANIC:
                return new Mechanic();
            case SALESPERSON:
                return new Salesperson();
            case INTERN:
                return new Intern();
            case DRIVER:
                return new Driver();
            default:
                throw new IllegalArgumentException("Unknown staff title + [" + title + "]");
        }
    }
}
