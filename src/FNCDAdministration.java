public class FNCDAdministration {
    private boolean workingStatus = true;
    public static int day = 1;
    private int endDay = 30;

    // start instantiating the staff
    // start instantiating the cars
    // start the activities
    public void start() {

    }

    // add activities
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
    public void dailyReport(int day) {
    /* tabulate the registries of
    1) budget
    2) staff
    3) activities
    4) vehicles
    5) inventory
     */

    }

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
