package utilities;

import activity.Sale;
import command.FNCDLocationSelectorCommand;

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

    public ArrayList<FNCDAdministration> getFncdAdministrationArrayList() {
        return fncdAdministrationArrayList;
    }


    FNCDAdministration selectedFNCD;

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

    public Simulator (int endDay){
        setEND_DAY(endDay);
    }

    public void addFNCD(FNCDAdministration fncd){
        fncdAdministrationArrayList.add(fncd);
    }


    /**
     * Represents the method that is implemented in the FNCDLocationSelector command within the command pattern
     * Simulator is one of the receivers in the command pattern
     */
    public void userInterfaceFNCDSelector(){
        ArrayList<FNCDAdministration> availableFncds = getFncdAdministrationArrayList();
        HashMap<Integer, FNCDAdministration> availableFncdHashMap = new HashMap<Integer, FNCDAdministration>();
        FNCDAdministration selectedFNCD;
        Scanner scanner = new Scanner(System.in);
        int userEntry;
        int counter = 1;
        System.out.println("Enter the number corresponding to the FNCD branch you want:");
        for (FNCDAdministration fncd: availableFncds){
            System.out.printf("%d) %s\n", counter, fncd.getName());
            availableFncdHashMap.put(counter, fncd);
            counter++;
        }
        userEntry = scanner.nextInt();
        selectedFNCD = availableFncdHashMap.get(userEntry);
        System.out.printf("You selected %s.\n",selectedFNCD.getName());
        setSelectedFNCD(selectedFNCD);
    }

    /**
     * simulation method for the simulator class
     */
    public void simulate(){
        for (FNCDAdministration fncd: fncdAdministrationArrayList){
            fncd.inaugurate();
        }
        for (int i = 1; i <=getEND_DAY(); i++) {
            for (FNCDAdministration fncd : fncdAdministrationArrayList) {
                if (i == getEND_DAY()+1){
                    break;
                }else {
                    fncd.operateByDay();
                }
            }
        }
        for (FNCDAdministration fncd : fncdAdministrationArrayList) {
            fncd.operateBeforeUserInterfaceSelling();
        }
    }
}
