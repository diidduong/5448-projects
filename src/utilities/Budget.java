package utilities;
import java.util.HashMap;
import vehicle.Vehicle;
import staff.Staff;

/**
 * @author Ahmed.H.Biby
 *
 * This class offers budget capabilities to deal with the financial aspects of the FNCD.
 * This class is to be instantiated only once in the main
 */
public class Budget {

    static private double currentBalance = 500000;
    static private double reserveBalance = 250000;
    static private double salesIncome;
    static private double serviceIncome;
    static private double washingIncome;

    public static double getCurrentBalance() {
        return currentBalance;
    }

    public static void setCurrentBalance(double currentBalance) {
        Budget.currentBalance = currentBalance;
    }

    public static double getReserveBalance() {
        return reserveBalance;
    }

    public static void setReserveBalance(double reserveBalance) {
        Budget.reserveBalance = reserveBalance;
    }

    public static double getSalesIncome() {
        return salesIncome;
    }

    public static void setSalesIncome(double salesIncome) {
        Budget.salesIncome = salesIncome;
    }

    public static double getServiceIncome() {
        return serviceIncome;
    }

    public static void setServiceIncome(double serviceIncome) {
        Budget.serviceIncome = serviceIncome;
    }

    public static double getWashingIncome() {
        return washingIncome;
    }

    public static void setWashingIncome(double washingIncome) {
        Budget.washingIncome = washingIncome;
    }

    public static double getTotalIncome() {
        return totalIncome;
    }

    public static void setTotalIncome(double totalIncome) {
        Budget.totalIncome = totalIncome;
    }

    public static double getSalaries() {
        return salaries;
    }

    public static void setSalaries(double salaries) {
        Budget.salaries = salaries;
    }

    public static double getBonuses() {
        return bonuses;
    }

    public static void setBonuses(double bonuses) {
        Budget.bonuses = bonuses;
    }

    public static double getVehicleAssets() {
        return vehicleAssets;
    }

    public static void setVehicleAssets(double vehicleAssets) {
        Budget.vehicleAssets = vehicleAssets;
    }

    public static Registry<String, Double> getBudgetRegistry() {
        return budgetRegistry;
    }

    public static void setBudgetRegistry(Registry<String, Double> budgetRegistry) {
        Budget.budgetRegistry = budgetRegistry;
    }

    public static Registry<String, String> getSalariesRegistry() {
        return salariesRegistry;
    }

    public static void setSalariesRegistry(Registry<String, String> salariesRegistry) {
        Budget.salariesRegistry = salariesRegistry;
    }

    static private double totalIncome;
    static private double salaries;
    static private double bonuses;
    static private double vehicleAssets;

    static private Registry<String,Double> budgetRegistry= new Registry<>();
    static public Registry<String,String> salariesRegistry= new Registry<>();

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


    static public void start(){
        System.out.printf("\nThe Current balance is $ %f\n", getCurrentBalance());
    }

    /**
     * This method aims at adding salesincome to the currentBalance.
     * @param day: the day that is provided by the FNCDadministration for the FNCD operation simulation.
     * @param vehicle: The vehicle to be sold.
     */
    static public void addSalesIncome(int day, Vehicle vehicle){
        HashMap<String, Double> registryAction = new HashMap<String, Double>();
        double newSalesIncome = vehicle.getSalePrice();
        System.out.printf("\nSales income increased by $ %f\n", newSalesIncome);
        setSalesIncome(getSalesIncome() + newSalesIncome);
        setTotalIncome(getTotalIncome() + newSalesIncome);
        setCurrentBalance(getCurrentBalance() + newSalesIncome);
        System.out.printf("\nCurrent balance is $ %f \n", getCurrentBalance());
        registryAction.put("closingSalesIncome", getSalesIncome());
        String formattedDay = String.format("Day_%d_salesIncome", day);
        budgetRegistry.add(formattedDay, registryAction);
    }

    /**
     * This method register and subtracts the vehicle purchase cost from the current balance
     * @param day: operation day provided by the administration
     * @param vehicle: vehicle to be purchase
     */

    static public void purchaseCar(int day, Vehicle vehicle){
        HashMap<String, Double> registryAction = new HashMap<String, Double>();
        double newPurchaseCost = vehicle.getInitialCost();
        System.out.printf("\nNew car purchase for $ %f\n", newPurchaseCost);
        setCurrentBalance(getCurrentBalance() - newPurchaseCost);
        System.out.printf("\nCurrent balance is $ %f \n", getCurrentBalance());
        registryAction.put("closingCurrentBalance", getCurrentBalance());
        String formattedDay = String.format("Day_%d_salesIncome", day);
        budgetRegistry.add(formattedDay, registryAction);
    }

    /**
     * This method aims at adding washingIncome to the currentBalance.
     * @param day: the day that is provided by the FNCDadministration for the FNCD operation simulation.
     * @param newWashingIncome: The new washing income to be added.
     */
    static public void addWashingIncome(int day, double newWashingIncome){
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
     * @param day: the day that is provided by the FNCDadministration for the FNCD operation simulation.
     * @param staff who recives new salary to be subtracted from the currentBalance.
     */
    static public void addSalaries(int day, Staff staff){
        HashMap<String, Double> registryAction = new HashMap<String, Double>();
        double dailyRate =0;

        switch (staff.getJobTitle()){
            case "Mechanic":
                dailyRate = 120;
                break;
            case "Salesperson":
                dailyRate = 150;
                break;
            case "Intern":
                dailyRate = 80;
                break;
        }
        System.out.printf("\nTotal salary increased by $ %f\n", dailyRate);
        setSalaries(Budget.getSalaries()+ dailyRate);
        setCurrentBalance(Budget.getCurrentBalance() - dailyRate);
        System.out.printf("\nCurrent balance is $ %f \n", Budget.getCurrentBalance());
        registryAction.put("closingSalaries", Budget.getSalaries());
        String formattedDay = String.format("Day_%d_salaries", day);
        budgetRegistry.add(formattedDay, registryAction);
    }

    /**
     * This method aims at subtracting the new bonus from the currentBalance.
     * @param day: the day that is provided by the FNCDadministration for the FNCD operation simulation.
     * @param newBonus: The new bonus to be subtracted.
     */
    static public void addBonuses(int day, double newBonus){
        HashMap<String, Double> registryAction = new HashMap<String, Double>();
        System.out.printf("\nTotal bonuses increased by $ %f\n", newBonus);
        setBonuses(getBonuses() + newBonus);
        setCurrentBalance(getCurrentBalance() - newBonus);
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





}