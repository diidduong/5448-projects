package tracking;

public class Tracker implements Subscriber {
    int day;
    double totalMoneyEarnedByAllStaff;
    double totalMoneyEarnedByFNCD;

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

    public void printDailySummary() {
        System.out.printf("\nTracker: Day %d\n", day);
        System.out.printf("Total money earned by all Staff: $%.2f\n", totalMoneyEarnedByAllStaff);
        System.out.printf("Total money earned by the FNCD: $%.2f\n", totalMoneyEarnedByFNCD);
    }
}
