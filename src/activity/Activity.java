package activity;

import customer.Buyer;
import staff.Staff;
import utilities.Budget;
import utilities.Inventory;

import java.util.ArrayList;

/**
 * @author Duy Duong, Ahmed.H.Biby
 *
 * Activity is a super class, it allows ability to perform daily jobs
 */
public abstract class Activity {
    private String serviceName;

    private Staff provider;
    private Inventory inventory;
    private ArrayList<Buyer> buyers;
    private Budget budget;
    private double successProbability;
    private double bonus;

    /**
     * Constructor for activity initialization
     * @param provider assigned staff
     * @param inventory current inventory
     * @param buyers all buyers
     * @param budget current budget
     */
    public Activity(Staff provider, Inventory inventory, ArrayList<Buyer> buyers, Budget budget) {
        this.provider = provider;
        this.inventory = inventory;
        this.buyers = buyers;
        this.budget = budget;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public Staff getProvider() {
        return provider;
    }

    public void setProvider(Staff provider) {
        this.provider = provider;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public ArrayList<Buyer> getBuyers() {
        return buyers;
    }

    public void setBuyers(ArrayList<Buyer> buyers) {
        this.buyers = buyers;
    }

    public Budget getBudget() {
        return budget;
    }

    public void setBudget(Budget budget) {
        this.budget = budget;
    }

    public double getSuccessProbability() {
        return successProbability;
    }

    public void setSuccessProbability(double successProbability) {
        this.successProbability = successProbability;
    }

    public double getBonus() {
        return bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    /**
     * Method required to perform daily work
     */
    public abstract void performWork();
}