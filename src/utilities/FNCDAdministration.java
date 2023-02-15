package utilities;

import staff.Intern;
import staff.Mechanic;
import staff.Salesperson;
import utilities.RandomGenerator;
import utilities.Registry;
import vehicle.Car;
import vehicle.PerformanceCar;
import vehicle.Pickup;

import java.util.ArrayList;

/**
 * @author Ahmed.H.Biby
 * This class acts as the simulator engine and operarations summary generator for the FNCD operation.
 * This class is to be instantiated only once in the main.
 */
public class FNCDAdministration {
    static private boolean workingStatus = true;
    static private int day = 1;
    static private final int endDay = 30;



    static public Registry internRegistry= new Registry();

    static public ArrayList<Intern> internArrayList = new ArrayList<Intern>();

    static public Registry mechanicRegistry= new Registry();
    static public ArrayList<Mechanic> mechanicArrayList = new ArrayList<Mechanic>();
    static public Registry salesPersonRegistry= new Registry();
    static public ArrayList<Salesperson> salesPersonArrayList = new ArrayList<Salesperson>();



    // start instantiating the staff
    // start instantiating the cars
    // start the activities
    static public void start() {
        System.out.println();
        System.out.println("****************************************");
        System.out.println("    Welcome to the FNCD simulation");
        System.out.println("********** Inauguration day ************");
        for (int i=0; i <=2; i++){
            Intern intern           = new Intern(1);
            Mechanic mechanic       = new Mechanic(1);
            Salesperson salesperson = new Salesperson(1);
        }

        for (int i=0; i <=4; i++){
            Pickup pickup = new Pickup(1);
            Car car = new Car(1);
            PerformanceCar performanceCar = new PerformanceCar(1);
        }

        Budget.start();
        System.out.println("********** End of the Inauguration day ************");

    }

    /**
     * This method is the main simulator engine that has all the hiring, promoting, quiting, selling, washing, repairing,
     * and the financial operations.
     *
     * ADD ACTIVITIES
     */
    //TODO: add activities
    //TODO: add a method that makes sure that the inventory has the minimum number of vehicles for each type (like workForceMaintenance)
    //TODO: let the activities print the updates (who sold/washed/repaired which vehicle)
    static public void operate() {
        for (int i = 1; day <= endDay; i++) {
            if (day % 7 != 0 && day%7 !=1) {

                workingStatus = true;
                System.out.printf("\n********** day %d ************\n", day);
                System.out.println("********** Working day ************");
                mechanicArrayList.get(0).workOrQuit(day);
                internArrayList.get(0).workOrQuit(day);
                salesPersonArrayList.get(0).workOrQuit(day);
                workForceMaintenance(day);
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
        System.out.println("********** End of FNCD simulation  ************");
    }

    /**
     * method that checks if there is any shortage in any of the workforce and hire if shortage exists
     * @param day: provided by the administration
     */
    public static void workForceMaintenance(int day) {
        if (mechanicArrayList.size() < 3) {
            int mechanicShortage = 3-mechanicArrayList.size();
            for (int i=0; i<mechanicShortage; i++) {
                promoteInternToMechanic(day);
            }
        }
        if (salesPersonArrayList.size() < 3) {
            int salesPersonShortage = 3-salesPersonArrayList.size();
            for (int i=0; i<salesPersonShortage; i++) {
                hireSalesPerson(day);
            }
        }
        if (internArrayList.size() < 3) {
            int interShortage = 3-internArrayList.size();
            for (int i=0; i< interShortage; i++) {
                hireIntern(day);
            }
        }
    }

    /**
     * method for hiring new intern
     * @param day: provided by the administration
     */
    public static void hireIntern(int day){
        Intern intern = new Intern(day);
    }

    /**
     * method for hiring new salesperson
     * @param day: provided by the administration
     */
    public static void hireSalesPerson(int day){
        Salesperson salesPerson = new Salesperson(day);
    }

    /**
     * method that promotes an intern in case of a given mechanic quited
     * @param day: provided by the administration
     */
    public static void promoteInternToMechanic(int day){
        Intern interTobePromoted = internArrayList.get(0);
        Mechanic mechanic = new Mechanic(day, interTobePromoted);
    }

    /**
     * This method is the daily report generator that summarizes all the necessary operational details.
     * @param day: the day that is provided by the FNCDadministration for the FNCD operation simulation.
     *
     * ADD ACTIVITIES
     */
    static public void dailyReport(int day) {
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
    static public void monthlyReport() {
    /* tabulate the registries of
    1) budget
    2) staff
    3) activities
    4) vehicles
    5) inventory
     */
    }

}


