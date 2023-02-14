package staff;

import activity.Activity;

public abstract class Staff {
    private String name;
    private double dailyRate;
    private double salary;
    private double bonus;
    private int daysOfWork;
    private double turnOverProbability = 0.1;
    private boolean isWorking;
    private Activity activity;

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

    public void hire(){

    }
    public void work(){

    }

    public void quit() {

    }

    public void get_bonus() {

    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public void addSalary(){
        salary += dailyRate;
    }
    public void addBonus(double bonus) { salary += bonus; }
    public void addWorkDay(){
        daysOfWork++;
    }

}