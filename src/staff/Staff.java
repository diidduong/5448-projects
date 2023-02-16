package staff;

import utilities.RandomGenerator;
import activity.Activity;
import utilities.Registry;

import java.util.HashMap;

/**
 * @author Duy Duong, Ahmed.H.Biby
 *
 * Abstract class of Staff
 */
public abstract class Staff {
    public enum JobTitle {
        INTERN, MECHANIC, SALESPERSON
    }
    
    private String name;
    private JobTitle jobTitle;
    private double dailyRate;
    private double salary;
    private double bonus;
    private double totalBonus;
    private int daysOfWork;

    private boolean isWorking = true;
    private Activity activity;
    private Registry registry = new Registry();

    private double turnOverProbability = 0.1;  // 3% for each employee, which is about 10% for each three

    public Staff(int day, JobTitle jobTitle, double dailyRate) {
        setName(RandomGenerator.nameGenerator());
        this.jobTitle = jobTitle;
        this.dailyRate = dailyRate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getDailyRate() {
        return dailyRate;
    }

    public void setDailyRate(double dailyRate) {
        this.dailyRate = dailyRate;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public double getBonus() {
        return bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    public double getTotalBonus() {
        return totalBonus;
    }

    public void setTotalBonus(double totalBonus) {
        this.totalBonus = totalBonus;
    }

    public int getDaysOfWork() {
        return daysOfWork;
    }

    public void setDaysOfWork(int daysOfWork) {
        this.daysOfWork = daysOfWork;
    }

    public double getTurnOverProbability() {
        return turnOverProbability;
    }

    public void setTurnOverProbability(double turnOverProbability) {
        this.turnOverProbability = turnOverProbability;
    }

    public boolean isWorking() {
        return isWorking;
    }

    public void setWorking(boolean working) {
        isWorking = working;
    }

    public JobTitle getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(JobTitle jobTitle) {
        this.jobTitle = jobTitle;
    }

    public void startWorkingDay(int day){
        setDaysOfWork(getDaysOfWork()+1);
//        Budget.addSalaries(day, this);
        HashMap<String, String> registryAction = new HashMap<String, String>();
        String formattedDay = String.format("Day_%d_%s_%s", day, getJobTitle(), getName().replace(' ', '_'));
//        Budget.salariesRegistry.add(formattedDay, registryAction);
        addSalary();
    }
    public void addSalary(){
        this.salary += dailyRate;
    }

    public void addBonus(double bonus) {
        this.bonus += bonus;
    }

    public void addTotalBonus() {
        this.totalBonus += bonus;
        this.bonus = 0; // reset earned bonus after adding to total
    }
    public void addWorkDay(){
        daysOfWork++;
    }

    public void quit() {
        setWorking(false);
    }

    public Registry getRegistry() {
        return registry;
    }

    public void setRegistry(Registry registry) {
        this.registry = registry;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
        addWorkDay();
    }

    /**
     * method that check if the staff member will continue to work or will quit and add Salaries/workdays
     * @param day: a given day by the administration
     */
    public void workOrQuit(int day) {
        boolean isQuit = RandomGenerator.probabilisticOutcomeGenerator(getTurnOverProbability());
        if (isQuit) {
            quit();
            System.out.printf("\n%s (%s) quited.\n", getName(), getJobTitle());
            HashMap<String, String> registryAction = new HashMap<>();
            String formattedDay = String.format("Day_%d_%s_%s_quited", day, getJobTitle(), getName().replace(' ', '_'));
            registry.add(formattedDay, registryAction);
        }
    }
}

