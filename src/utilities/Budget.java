package utilities;
import java.util.ArrayList;
import java.util.HashMap;
import vehicle.Vehicle;
import staff.Staff;

/**
 * @author Duy Duong, Ahmed.H.Biby
 *
 * This class offers budget capabilities to deal with the financial aspects of the FNCD.
 * This class is to be instantiated only once in the main
 */
public class Budget {

    private double currentBalance = 500000;
    private double reserveBalance = 250000;
    private double salesIncome;
    private double serviceIncome;
    private double washingIncome;
    private double totalIncome;
    private double salaries;
    private double bonuses;
    private double vehicleAssets;

    private Registry<String,Double> budgetRegistry= new Registry<>();
    public Registry<String,String> salariesRegistry= new Registry<>();

    public double getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(double currentBalance) {
        this.currentBalance = currentBalance;
    }

    public double getReserveBalance() {
        return reserveBalance;
    }

    public void setReserveBalance(double reserveBalance) {
        this.reserveBalance = reserveBalance;
    }

    public double getSalesIncome() {
        return salesIncome;
    }

    public void setSalesIncome(double salesIncome) {
        this.salesIncome = salesIncome;
    }

    public double getServiceIncome() {
        return serviceIncome;
    }

    public void setServiceIncome(double serviceIncome) {
        this.serviceIncome = serviceIncome;
    }

    public double getWashingIncome() {
        return washingIncome;
    }

    public void setWashingIncome(double washingIncome) {
        this.washingIncome = washingIncome;
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

    public double getVehicleAssets() {
        return vehicleAssets;
    }

    public void setVehicleAssets(double vehicleAssets) {
        this.vehicleAssets = vehicleAssets;
    }

    public Registry<String, Double> getBudgetRegistry() {
        return budgetRegistry;
    }

    public void setBudgetRegistry(Registry<String, Double> budgetRegistry) {
        this.budgetRegistry = budgetRegistry;
    }

    public Registry<String, String> getSalariesRegistry() {
        return salariesRegistry;
    }

    public void setSalariesRegistry(Registry<String, String> salariesRegistry) {
        this.salariesRegistry = salariesRegistry;
    }

    /**
     * This method aims at using the reserve balance in case the currentBalance goes to zero or less during the operations.
     * @param day: the day that is provided by the FNCDadministration for the FNCD operation simulation.
     */
    public void useReserve(int day){
        HashMap<String, Double> registryAction = new HashMap<String, Double>();
        currentBalance = currentBalance + reserveBalance;
        System.out.printf("\nReserve balance %f is used", reserveBalance);
        System.out.printf("\nCurrent balance is $ %f \n", currentBalance);
        reserveBalance = 0;
        registryAction.put("closingReserveBalance", reserveBalance);
        String formattedDay = String.format("Day_%d_reserveBalance", day);
        budgetRegistry.add(formattedDay, registryAction);

    }

    /**
     * This method aims at adding salesincome to the currentBalance.
     * @param day: the day that is provided by the FNCDadministration for the FNCD operation simulation.
     * @param vehicle: The vehicle to be sold.
     */
    public void addSalesIncome(int day, Vehicle vehicle){
        HashMap<String, Double> registryAction = new HashMap<String, Double>();
        double newSalesIncome = vehicle.getSalePrice();
        System.out.printf("Sales income increased by $ %f\n", newSalesIncome); //TODO: comment
        setSalesIncome(getSalesIncome() + newSalesIncome);
        setTotalIncome(getTotalIncome() + newSalesIncome);
        setCurrentBalance(getCurrentBalance() + newSalesIncome);
        System.out.printf("Current balance is $ %f \n", getCurrentBalance()); //TODO: comment
        registryAction.put("closingSalesIncome", getSalesIncome());
        String formattedDay = String.format("Day_%d_salesIncome", day);
        budgetRegistry.add(formattedDay, registryAction);
    }

    /**
     * This method register and subtracts the vehicle purchase cost from the current balance
     * @param vehicle : vehicle to be purchase
     * @param day : operation day provided by the administration
     */

    public void purchaseVehicle(Vehicle vehicle, int day){
        HashMap<String, Double> registryAction = new HashMap<>();
        double newPurchaseCost = vehicle.getInitialCost();
        if (currentBalance < newPurchaseCost) {
            useReserve(day);
        }
        System.out.printf("Purchased %s, %s %s for $ %f\n",
                vehicle.getVehicleCondition(),
                vehicle.getCleanliness(),
                vehicle.getName(),
                newPurchaseCost
        );
        setCurrentBalance(getCurrentBalance() - newPurchaseCost);
//        System.out.printf("\nCurrent balance is $ %f \n", getCurrentBalance());
        registryAction.put("closingCurrentBalance", getCurrentBalance());
        String formattedDay = String.format("Day_%d_salesIncome", day);
        budgetRegistry.add(formattedDay, registryAction);
    }

    /**
     * This method aims at adding washingIncome to the currentBalance.
     * @param day: the day that is provided by the FNCDadministration for the FNCD operation simulation.
     * @param newWashingIncome: The new washing income to be added.
     */
    public void addWashingIncome(int day, double newWashingIncome){
        HashMap<String, Double> registryAction = new HashMap<String, Double>();
        System.out.printf("\nWashing income increased by $ %f\n", newWashingIncome);
        setWashingIncome(getWashingIncome() + newWashingIncome);
        setServiceIncome(getServiceIncome() + newWashingIncome);
        setTotalIncome(getTotalIncome() + newWashingIncome);
        setCurrentBalance(getCurrentBalance()+newWashingIncome);
        System.out.printf("\nCurrent balance is $ %f \n", getCurrentBalance());
        registryAction.put("closingWashingIncome", getWashingIncome());
        String formattedDay = String.format("Day_%d_washingIncome", day);
        budgetRegistry.add(formattedDay, registryAction);
    }

    /**
     * This method aims at subtracting the new salary from the currentBalance.
     * @param day : the day that is provided by the FNCDadministration for the FNCD operation simulation.
     * @param staffs who receives new salary to be subtracted from the currentBalance.
     */
    public void addSalariesPayout(int day, ArrayList<Staff> staffs){
        HashMap<String, Double> registryAction = new HashMap<String, Double>();
        double dailyRate = 0;
        for (Staff staff : staffs) {
            dailyRate += staff.getDailyRate();
        }
        System.out.printf("\nTotal salary payout increased by $ %f\n", dailyRate);
        setSalaries(this.getSalaries()+ dailyRate);
        setCurrentBalance(this.getCurrentBalance() - dailyRate);
        System.out.printf("\nCurrent balance is $ %f \n", this.getCurrentBalance());
        registryAction.put("closingSalaries", this.getSalaries());
        String formattedDay = String.format("Day_%d_salaries", day);
        budgetRegistry.add(formattedDay, registryAction);
    }

    /**
     * This method aims at subtracting the new bonus from the currentBalance.
     * @param day : the day that is provided by the FNCDadministration for the FNCD operation simulation.
     * @param staffs : who receives new bonus to be subtracted from the currentBalance.
     */
    public void addBonusesPayout(int day, ArrayList<Staff> staffs){
        HashMap<String, Double> registryAction = new HashMap<String, Double>();
        double bonus = 0;
        for (Staff staff : staffs) {
            bonus += staff.getBonus();
        }
        System.out.printf("\nTotal bonuses payout increased by $ %f\n", bonus);
        setBonuses(getBonuses() + bonus);
        setCurrentBalance(getCurrentBalance() - bonus);
        System.out.printf("\nCurrent balance is $ %f \n", getCurrentBalance());
        registryAction.put("bonuses", getBonuses());
        String formattedDay = String.format("Day_%d_bonuses", day);
        budgetRegistry.add(formattedDay, registryAction);
    }

    /**
     * This method aims at adding the new vehicle asset.
     * @param day: the day that is provided by the FNCDadministration for the FNCD operation simulation.
     * @param newVehicleAsset: The new vehicle asset to be added.
     */
    public void addVehicleAsset(int day, double newVehicleAsset){
        HashMap<String, Double> registryAction = new HashMap<String, Double>();
        System.out.printf("\nTotal vehicle assets increased by $ %f\n", getVehicleAssets());
        setVehicleAssets(getVehicleAssets() + newVehicleAsset);
        System.out.printf("\nCurrent vehicle assets worth $ %f \n", getVehicleAssets());
        registryAction.put("closingVehicleAssets", getVehicleAssets());
        String formattedDay = String.format("Day_%d_vehicleAssets", day);
        budgetRegistry.add(formattedDay, registryAction);
    }

    /**
     * This method aims at subtracting the new vehicle asset.
     * @param day: the day that is provided by the FNCDadministration for the FNCD operation simulation.
     * @param newVehicleAsset: The new vehicle asset to be subtraacted.
     */
    public void removeVehicleAsset(int day, double newVehicleAsset){
        HashMap<String, Double> registryAction = new HashMap<String, Double>();
        System.out.printf("\nTotal vehicle assets decreased by $ %f\n", newVehicleAsset);
        setVehicleAssets(getVehicleAssets() - newVehicleAsset);
        System.out.printf("\nCurrent vehicle assets worth $ %f \n", getVehicleAssets());
        registryAction.put("closingVehicleAssets", getVehicleAssets());
        String formattedDay = String.format("Day_%d_vehicleAssets", day);
        budgetRegistry.add(formattedDay, registryAction);
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