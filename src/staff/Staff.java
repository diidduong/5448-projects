package staff;

abstract class Staff {
    private String name;
    private double dailyRate;
    private double salary;
    private double bonus;
    private int daysOfWork;
    private double turnOverProbability = 0.1;
    private boolean isWorking;

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

    public void addSalary(){
        salary = salary + dailyRate;
    }
    public void addWorkDay(){
        daysOfWork++;
    }

}