package staff;

import utilities.Budget;
import utilities.FNCDAdministration;
import utilities.RandomGenerator;
import utilities.Registry;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Duy Duong
 *
 *
 */
public class Mechanic extends Staff {
    private int repairingCapacity = 2;

    @Override
    public String getJobTitle() {
        return jobTitle;
    }

    private String jobTitle = "Mechanic";
    private double dailyRate = 120;

    public Mechanic(int day) {
        setName(RandomGenerator.nameGenerator());
        HashMap<String, String> registryAction = new HashMap<String, String>();
        System.out.printf("\n%s is hired as a mechanic.\n", getName());
        registryAction.put("name", getName());
        String formattedDay = String.format("Day_%d_mechanic_%s", day, getName().replace(' ', '_'));
        FNCDAdministration.mechanicRegistry.add(formattedDay, registryAction);
        FNCDAdministration.mechanicArrayList.add(this);
    }
    //overloaded constructor for the promotion of an intern to mechanic
    public Mechanic(int day,Intern intern) {
        setName(intern.getName());
        setSalary(intern.getSalary());
        setDaysOfWork(intern.getDaysOfWork());
        setBonus(intern.getBonus());
        HashMap<String, String> registryAction = new HashMap<String, String>();
        System.out.printf("\n%s is promoted from intern to mechanic.\n", intern.getName());
        registryAction.put("name", getName());
        String formattedDay = String.format("Day_%d_mechanic_%s", day, intern.getName().replace(' ', '_'));
        FNCDAdministration.mechanicRegistry.add(formattedDay, registryAction);
        FNCDAdministration.mechanicArrayList.add(this);
        FNCDAdministration.internArrayList.remove(intern);
    }

    public int getRepairingCapacity() {
        return repairingCapacity;
    }

    public void setRepairingCapacity(int repairingCapacity) {
        this.repairingCapacity = repairingCapacity;
    }
    /**
     * method that check if the staff member will continue to work or will quit and add Salaries/workdays
     * @param day: a given day by the administration
     */
    public void workOrQuit(int day) {
        ArrayList<Mechanic> disposableArrayList = new ArrayList<Mechanic>();
        for(Mechanic mechanic:FNCDAdministration.mechanicArrayList)
        {
            disposableArrayList.add(mechanic);
        }
        for (Mechanic mechanic : disposableArrayList) {
            boolean quitDecision = RandomGenerator.probabilisticOutcomeGenerator(getTurnOverProbability());
            if (quitDecision) {
                System.out.printf("\n%s (mechanic) quited.\n", mechanic.getName());
                HashMap<String, String> registryAction = new HashMap<String, String>();
                String formattedDay = String.format("Day_%d_mechanic_%s_quited", day, mechanic.getName().replace(' ', '_'));
                FNCDAdministration.mechanicRegistry.add(formattedDay, registryAction);
                FNCDAdministration.mechanicArrayList.remove(mechanic);
            } else {
                setRepairingCapacity(2);
                startWorkingDay(day);
            }
        }
    }
    /**
     * method that decrements the repairingCapacity of the intern after each repairing activity
     */
    //TODO: add the bonus
    public void repair() {
        if (getRepairingCapacity() > 0) {
            setRepairingCapacity(getRepairingCapacity() - 1);
        } else {
            System.out.printf("\n%s will not be available for any repairing activity today.\n", getName());
        }

    }
}