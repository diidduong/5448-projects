package staff;


import utilities.RandomGenerator;

import java.util.ArrayList;


/**
 * @author Duy Duong, Ahmed.H.Biby
 *
 * Abstract class of Staff
 */
public abstract class Staff {
    public enum JobTitle {
        INTERN, MECHANIC, SALESPERSON, DRIVER
    }
    
    private String name;
    private JobTitle jobTitle;
    private double dailyRate;
    private double salary;
    private double bonus;
    private double totalBonus;
    private int daysOfWork;

    private boolean isWorking = true;

    private final static double TURNOVER_PROBABILITY = 0.1;  // 3% for each employee, which is about 10% for each three

    public Staff(JobTitle jobTitle, double dailyRate) {
        name = RandomGenerator.personNameGenerator();
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



    public int getDaysOfWork() {
        return daysOfWork;
    }

    public void setDaysOfWork(int daysOfWork) {
        this.daysOfWork = daysOfWork;
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

    /**
     * Create staff by given title
     *
     * @param title wanted title
     * @return a staff object
     */
    public static Staff createStaffByType(Staff.JobTitle title) {
        switch (title) {
            case MECHANIC:
                return new Mechanic();
            case SALESPERSON:
                return new Salesperson();
            case INTERN:
                return new Intern();
            case DRIVER:
                return new Driver();
            default:
                throw new IllegalArgumentException("Unknown staff title + [" + title + "]");
        }
    }

    /**
     * Count number of staff of a specific job title
     * Reference: Project 2 example solution class Staff.howManyStaffByType
     *
     * @param staffs all staff
     * @param title wanted title
     * @return number of staff
     */
    public static int countStaffByType(ArrayList<Staff> staffs, JobTitle title) {
        int count = 0;
        for (Staff staff : staffs) {
            if (staff.jobTitle == title) {
                count++;
            }
        }
        return count;
    }

    /**
     * Get all staff for given job title
     * Reference: Project 2 example solution class Staff.getStaffByType
     *
     * @param staffs list of all staffs
     * @param jobTitle wanted job title
     * @return list of staffs that match given title
     */
    public static ArrayList<Staff> getStaffListByType(ArrayList<Staff> staffs, JobTitle jobTitle) {
        ArrayList<Staff> staffList = new ArrayList<>();
        for (Staff staff : staffs) {
            if (staff.jobTitle == jobTitle) {
                staffList.add(staff);
            }
        }
        return staffList;
    }

    /**
     * method that check if the staff member will continue to work or will quit and add Salaries/workdays
     */
    public void workOrQuit() {
        boolean isQuit = RandomGenerator.probabilisticOutcomeGenerator(TURNOVER_PROBABILITY);
        if (isQuit) {
            quit();
            System.out.printf("\n%s (%s) quited.\n", getName(), getJobTitle());
        }
    }

}


