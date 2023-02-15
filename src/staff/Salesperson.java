package staff;

import staff.Staff;
import utilities.FNCDAdministration;
import utilities.RandomGenerator;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Duy Duong
 *
 */
public class Salesperson extends Staff {
    private double dailyRate = 150;

    private String jobTitle = "Salesperson";

    public Salesperson(int day){
        setName(RandomGenerator.nameGenerator());
        HashMap<String, String> registryAction = new HashMap<String,String>();
        System.out.printf("\n%s is hired as a salesperson.\n", getName());
        registryAction.put("name", getName());
        String formattedDay = String.format("Day_%d_salesPerson_%s", day, getName().replace(' ', '_'));
        FNCDAdministration.salesPersonRegistry.add(formattedDay, registryAction);
        FNCDAdministration.salesPersonArrayList.add(this);
    }
    @Override
    public String getJobTitle() {
        return jobTitle;
    }
    /**
     * method that check if the staff member will continue to work or will quit and add Salaries/workdays
     * @param day: a given day by the administration
     */
    public void workOrQuit(int day) {
        ArrayList<Salesperson> disposableArrayList = new ArrayList<Salesperson>();
        for(Salesperson salesperson:FNCDAdministration.salesPersonArrayList)
        {
            disposableArrayList.add(salesperson);
        }
        for (Salesperson salesperson : disposableArrayList) {
            boolean quitDecision = RandomGenerator.probabilisticOutcomeGenerator(getTurnOverProbability());
            if (quitDecision) {
                System.out.printf("\n%s (salesperson) quited.\n", salesperson.getName());
                HashMap<String, String> registryAction = new HashMap<String, String>();
                String formattedDay = String.format("Day_%d_salesperson_%s_quited", day, salesperson.getName().replace(' ', '_'));
                FNCDAdministration.salesPersonRegistry.add(formattedDay, registryAction);
                FNCDAdministration.salesPersonArrayList.remove(salesperson);
            } else {
                startWorkingDay(day);
            }
        }
    }

}