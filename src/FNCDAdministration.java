/**
 * @author Ahmed.H.Biby
 * This class acts as the simulator engine and operarations summary generator for the FNCD operation.
 * This class is to be instantiated only once in the main.
 */
public class FNCDAdministration {
    private boolean workingStatus = true;
    public static int day = 1;
    private int endDay = 30;

    // start instantiating the staff
    // start instantiating the cars
    // start the activities
    public void start() {

    }

    /**
     * This method is the main simulator engine that has all the hiring, promoting, quiting, selling, washing, repairing,
     * and the financial operations.
     *
     * ADD ACTIVITIES
     */
    public void operate() {
        for (int i = 1; day <= 30; i++) {
            if (day % 7 != 0 && day%7 !=1) {

                workingStatus = true;
                System.out.printf("\n********** day %d ************\n", day);
                System.out.println("********** Working day ************");
                dailyReport(day);

                day++;

            } else if (day % 7 == 0 || day%7 ==1) {

                workingStatus = false;
                System.out.printf("\n********** day %d ************\n", day);
                System.out.println("********** Weekend ************");
                day++;
            }

        }
        System.out.println("********** End of FNCD operation simulation  ************");
        monthlyReport();
        System.out.println("********** End of simulation  ************");
    }

    /**
     * This method is the daily report generator that summarizes all the necessary operational details.
     * @param day: the day that is provided by the FNCDadministration for the FNCD operation simulation.
     *
     * ADD ACTIVITIES
     */
    public void dailyReport(int day) {
    /* tabulate the registries of
    1) budget
    2) staff
    3) activities
    4) vehicles
    5) inventory
     */

    }

    /**
     * This method is the monthly report generator that summarizes all the necessary operational details.
     *
     * ADD ACTIVITIES
     */
    public void monthlyReport() {
    /* tabulate the registries of
    1) budget
    2) staff
    3) activities
    4) vehicles
    5) inventory
     */

    }

}
