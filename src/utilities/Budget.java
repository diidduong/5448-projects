package utilities;

import staff.Staff;
import tracking.EventPublisher;
import tracking.Message;

import java.util.ArrayList;

/**
 * @author Duy Duong, Ahmed.H.Biby
 *
 * This class offers budget capabilities to deal with the financial aspects of the FNCD.
 * This class is to be instantiated only once in the main
 */
public class Budget {
    private double currentBalance;
    private double salesIncome;
    private double totalIncome;
    private double salaries;
    private double bonuses;

    public Budget(double currentBalance) {
        this.currentBalance = currentBalance;
    }

    public double getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(double currentBalance) {
        this.currentBalance = currentBalance;
    }

    public double getSalesIncome() {
        return salesIncome;
    }

    public void setSalesIncome(double salesIncome) {
        this.salesIncome = salesIncome;
    }

    public double getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(double totalIncome) {
        this.totalIncome = totalIncome;
    }

    public double getSalaries() {
        return salaries;
    }

    public void setSalaries(double salaries) {
        this.salaries = salaries;
    }

    public double getBonuses() {
        return bonuses;
    }

    public void setBonuses(double bonuses) {
        this.bonuses = bonuses;
    }


    /**
     * This method aims at deposit money to balance
     *
     * @param amount: dollar amount to be added to balance
     */
    public void addBalance(double amount){
        currentBalance += amount;
    }

    /**
     * This method is to subtract balance by given amount
     *
     *  @param amount: dollar amount to be subtracted from balance
     */
    public void subtractBalance(double amount){
        currentBalance -= amount;
    }

    /**
     * This method is to add money to sale income for keeptracking
     *
     * @param amount sale amount
     */
    public void addSaleIncome(double amount) {
        salesIncome += amount;
    }

    /**
     * This method aims at subtracting the new salary from the currentBalance.
     * @param staffs who receives new salary to be subtracted from the currentBalance.
     */
    public void addSalariesPayout(ArrayList<Staff> staffs, EventPublisher publisher){
        double dailyRate = 0;
        for (Staff staff : staffs) {
            dailyRate += staff.getDailyRate();
        }
        currentBalance -= dailyRate;
        salaries += dailyRate;

        String msg = String.format("Total salary payout increased by $%.2f", dailyRate);
        publisher.notifySubscribers(new Message(msg, 0, 0));

        System.out.printf("Current balance is $%.2f\n", currentBalance);
    }

    /**
     * This method aims at subtracting the new bonus from the currentBalance.
     * @param staffs : who receives new bonus to be subtracted from the currentBalance.
     */
    public void addBonusesPayout(ArrayList<Staff> staffs, EventPublisher publisher){
        double bonus = 0;
        for (Staff staff : staffs) {
            bonus += staff.getBonus();
        }
        currentBalance -= bonus;
        bonuses += bonus;
        String msg = String.format("Total bonuses payout increased by $%.2f", bonus);
        publisher.notifySubscribers(new Message(msg, 0, 0));

        System.out.printf("Current balance is $%.2f\n", currentBalance);
    }

    /**
     * Print out all expense and income
     */
    public void printBudget() {
        System.out.printf("Current balance: $%.2f\n", currentBalance);
        System.out.printf("Total sale income: $%.2f\n", salesIncome);
        System.out.printf("Total salaries payout: $%.2f\n", salaries);
        System.out.printf("Total bonuses payout: $%.2f\n", bonuses);
    }
}