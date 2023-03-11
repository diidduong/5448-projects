package tracking;

/**
 * @author Duy Duong, Ahmed.H.Biby
 *
 * Concrete subcriber class for tracking total earning by staff and FNCD, Singleton pattern
 */
public class Tracker implements Subscriber {
    int day;
    double totalMoneyEarnedByAllStaff;
    double totalMoneyEarnedByFNCD;
    private static Tracker instance = new Tracker(); // eager instantiation

    private Tracker() {}

    /**
     * Singleton get instance method to return single instance
     * @return single instance of Logger
     */
    public static Tracker getInstance() {
        if (instance == null) {
            instance = new Tracker();
        }
        return instance;
    }

    /**
     * For each update, add earnings for all staff and FNCD
     * @param message event message
     */
    @Override
    public void update(Message message) {
        totalMoneyEarnedByAllStaff += message.moneyEarnedByStaff;
        totalMoneyEarnedByFNCD += message.moneyEarnedByFNCD;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    /**
     * To print total earnings since day 1
     */
    public void printDailySummary() {
        System.out.printf("\nTracker: Day %d\n", day);
        System.out.printf("Total money earned by all Staff: $%.2f\n", totalMoneyEarnedByAllStaff);
        System.out.printf("Total money earned by the FNCD: $%.2f\n", totalMoneyEarnedByFNCD);
    }
}
