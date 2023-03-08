package utilities;

import activity.Sale;
import command.FNCDLocationSelectorCommand;
import staff.Staff;
import tracking.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * simulates the daily operation of single thread of multiple branches of FNCD
 * @author Ahmed.H.Biby
 */
public class Simulator {


    private int day = 1;

    private int endDay = 1; // 30-day simulation

    private ArrayList<FNCDAdministration> fncdAdministrationArrayList = new ArrayList<FNCDAdministration>();

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public ArrayList<FNCDAdministration> getFncdAdministrationArrayList() {
        return fncdAdministrationArrayList;
    }

    private FNCDAdministration selectedFNCD;

    public FNCDAdministration getSelectedFNCD() {
        return selectedFNCD;
    }

    public void setSelectedFNCD(FNCDAdministration selectedFNCD) {
        this.selectedFNCD = selectedFNCD;
    }


    public void setEND_DAY(int endDay) {
        this.endDay = endDay;
    }

    public int getEND_DAY() {
        return endDay;
    }

    public Simulator(int endDay) {
        setEND_DAY(endDay);
    }

    public void addFNCD(FNCDAdministration fncd) {
        fncdAdministrationArrayList.add(fncd);
    }


    /**
     * Represents the method that is implemented in the FNCDLocationSelector command within the command pattern
     * Simulator is one of the receivers in the command pattern
     */
    public void userInterfaceFNCDSelector() {
        ArrayList<FNCDAdministration> availableFncds = getFncdAdministrationArrayList();
        HashMap<Integer, FNCDAdministration> availableFncdHashMap = new HashMap<Integer, FNCDAdministration>();
        FNCDAdministration selectedFNCD;
        Scanner scanner = new Scanner(System.in);
        int userEntry;
        int counter = 1;
        System.out.println("Enter the number corresponding to the FNCD branch you want:");
        for (FNCDAdministration fncd : availableFncds) {
            System.out.printf("%d) %s\n", counter, fncd.getName());
            availableFncdHashMap.put(counter, fncd);
            counter++;
        }
        userEntry = scanner.nextInt();
        selectedFNCD = availableFncdHashMap.get(userEntry);
        System.out.printf("You selected %s.\n", selectedFNCD.getName());
        setSelectedFNCD(selectedFNCD);
    }

    /**
     * simulation method for the simulator class
     */
    public void simulate() {
        for (FNCDAdministration fncd : fncdAdministrationArrayList) {
            fncd.inaugurate();
        }
        for (int i = 1; i <= getEND_DAY(); i++) {
            if (i == getEND_DAY() + 1) {
                break;
            } else {
                for (FNCDAdministration fncd : fncdAdministrationArrayList) {
                    // initialization of daily logger
                    fncd.logger = new Logger(fncd.getDay());
                    fncd.publisher.addSubscriber(fncd.logger);
                    // Set day for logger and tracker
                    fncd.logger.setDay(fncd.getDay());
                    fncd.tracker.setDay(fncd.getDay());

                    // Increase num work day of all staffs
                    for (Staff staff : fncd.staffs) {
                        staff.addWorkDay();
                    }
                    // Sunday and Wednesday is for both working and racing activity, other day is working
                    if (fncd.getDay() % 7 == 1 || fncd.getDay() % 7 == 4) {
                        System.out.printf("\n\n********** day %d ************\n", fncd.getDay());
                        System.out.printf("********** Working & Racing day (%s) FNCD ************", fncd.getName());
                        fncd.opening();
                        setDay(fncd.getDay());

                    } else {
                        System.out.printf("\n********** day %d ************\n", fncd.getDay());
                        System.out.printf("********** Working day (%s) FNCD ************", fncd.getName());
                        fncd.opening();
                        setDay(fncd.getDay());
                    }
                }for (FNCDAdministration fncd : fncdAdministrationArrayList) {
                    fncd.washing();
                }for (FNCDAdministration fncd : fncdAdministrationArrayList) {
                    fncd.repairing();
                }for (FNCDAdministration fncd : fncdAdministrationArrayList) {
                    fncd.selling();
                }
                if (day % 7 == 1 || day % 7 == 4) {
                for (FNCDAdministration fncd : fncdAdministrationArrayList) {
                    fncd.racing();
                }
                }for (FNCDAdministration fncd : fncdAdministrationArrayList) {
                    fncd.ending();
                }for (FNCDAdministration fncd : fncdAdministrationArrayList) {
                    fncd.dailyReport(); // tabular report at the end of the day
                    fncd.publisher.removeSubscriber(fncd.logger); // remove logger at the end of each day
                    fncd.tracker.printDailySummary(); // tracker summary
                    fncd.setDay(fncd.getDay()+1); // next day work
                }
            }
        }for (FNCDAdministration fncd : fncdAdministrationArrayList) {
            // initialization of daily logger
            fncd.logger = new Logger(fncd.getDay());
            fncd.publisher.addSubscriber(fncd.logger);
            // Set day for logger and tracker
            fncd.logger.setDay(fncd.getDay());
            fncd.tracker.setDay(fncd.getDay());
            for (Staff staff : fncd.staffs) {
                staff.addWorkDay();
            }
            System.out.println("\n##############################");
            System.out.println("USER INTERFACE DAY");
            System.out.println("##############################");
            System.out.printf("********** day %d ************\n", fncd.getDay());
            System.out.printf("********** Working day (%s) FNCD ************", fncd.getName());
        }
        for (FNCDAdministration fncd : fncdAdministrationArrayList) {
            fncd.opening();
        }
            for (FNCDAdministration fncd : fncdAdministrationArrayList) {
                fncd.washing();
            }for (FNCDAdministration fncd : fncdAdministrationArrayList) {
                fncd.repairing();
        }
        UserInterface userInterface = new UserInterface(this);
        userInterface.start();
    }
}