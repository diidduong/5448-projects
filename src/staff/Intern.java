package staff;
import utilities.FNCDAdministration;

import activity.Activity;
import activity.Wash;
import staff.Staff;
import java.util.ArrayList;
import utilities.Registry;
import utilities.RandomGenerator;

import java.util.HashMap;

public class Intern extends Staff {

    private double dailyRate = 80;

    private double bonus;
    private int daysOfWork;

    private String jobTitle = "Intern";



    private int washingCapacity = 2;


    public ArrayList<Intern> internList;



    public Intern(int day){
        setName(RandomGenerator.nameGenerator());
        HashMap<String, String> registryAction = new HashMap<String,String>();
        System.out.printf("\n%s is hired as an intern.\n", getName());
        registryAction.put("name", getName());
        String formattedDay = String.format("Day_%d_intern_%s", day, getName().replace(' ', '_'));
        FNCDAdministration.internRegistry.add(formattedDay, registryAction);
        FNCDAdministration.internArrayList.add(this);

    }

    /**
     * method that check if the staff member will continue to work or will quit and add Salaries/workdays
     * @param day: a given day by the administration
     */
    public void workOrQuit(int day) {
        ArrayList<Intern> disposableArrayList = new ArrayList<Intern>();
        for(Intern intern:FNCDAdministration.internArrayList)
        {
            disposableArrayList.add(intern);
        }
        for (Intern intern : disposableArrayList) {
            boolean quitDecision = RandomGenerator.probabilisticOutcomeGenerator(getTurnOverProbability());
            if (quitDecision) {
                System.out.printf("\n%s (intern) quited.\n", intern.getName());
                HashMap<String, String> registryAction = new HashMap<String, String>();
                String formattedDay = String.format("Day_%d_intern_%s_quited", day, intern.getName().replace(' ', '_'));
                FNCDAdministration.internRegistry.add(formattedDay, registryAction);
                FNCDAdministration.internArrayList.remove(intern);
            } else {
                setWashingCapacity(2);
                startWorkingDay(day);
            }
        }
    }
    public int getWashingCapacity() {
        return washingCapacity;
    }

    public void setWashingCapacity(int washingCapacity) {
        this.washingCapacity = washingCapacity;
    }

    @Override
    public String getJobTitle() {
        return jobTitle;
    }

    /**
     * method that decrements the washingCapacity of the intern after each washing activity
     */
    //TODO: add the bonus
    public void wash(){
        if (getWashingCapacity() > 0){
            setWashingCapacity(getWashingCapacity()-1);
        }
        else{
            System.out.printf("\n%s will not be available for any washing activity today.\n", getName());
        }


    }

}