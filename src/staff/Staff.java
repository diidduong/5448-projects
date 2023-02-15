package staff;

import utilities.RandomGenerator;
import activity.Activity;
import utilities.Registry;

import utilities.Budget;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class Staff {
    private String name;
    private String jobTitle;
    private double dailyRate;
    private double salary =0;
    private double bonus = 0;
    private int daysOfWork =0;

    private boolean isWorking =true;
    private Activity activity;

    private double turnOverProbability = 0.1;  // 3% for each employee, which is about 10% for each three

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

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        jobTitle = jobTitle;
    }

    public void startWorkingDay(int day){
        setDaysOfWork(getDaysOfWork()+1);
        Budget.addSalaries(day, this);
        HashMap<String, String> registryAction = new HashMap<String, String>();
        String formattedDay = String.format("Day_%d_%s_%s", day, getJobTitle(), getName().replace(' ', '_'));
        Budget.salariesRegistry.add(formattedDay, registryAction);
        addSalary(day);
    }
    public void addSalary(int day){

    }
    public void addBonus(double bonus) { bonus += bonus; }
    public void addWorkDay(){
        daysOfWork++;
    }

    public void quit() {
        setWorking(false);
    }




}

