package utilities;
import java.util.HashMap;

// this class to be instantiated only once in the main
// I removed the calculateSalary() method. Let's have fixed rate in the staff class

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

    // get the int day from FNCD_administration
    public void useReserve(int day){
        HashMap<String, Double> registryAction = new HashMap<String, Double>();
        currentBalance = currentBalance + reserveBalance;
        System.out.printf("\nReserve balance %f is used", reserveBalance);
        System.out.printf("\nCurrent balance is $ %f \n", currentBalance);
        reserveBalance = 0;
        registryAction.put("closingReserveBalance", reserveBalance);
        String formattedDay = String.format("Day_%d_reserveBalance", day);
        budgetRegistry.add(formattedDay, registryAction);
        //System.out.println(budgetRegistry.getRegistry());
    }
    public void addSalesIncome(int day, double newSalesIncome){
        HashMap<String, Double> registryAction = new HashMap<String, Double>();
        System.out.printf("\nSales income increased by $ %f\n", newSalesIncome);
        salesIncome = salesIncome + newSalesIncome;
        totalIncome = totalIncome + newSalesIncome;
        currentBalance = currentBalance + newSalesIncome;
        System.out.printf("\nCurrent balance is $ %f \n", currentBalance);
        registryAction.put("closingSalesIncome", salesIncome);
        String formattedDay = String.format("Day_%d_salesIncome", day);
        budgetRegistry.add(formattedDay, registryAction);
        //System.out.println(budgetRegistry.getRegistry());
    }

    public void addWashingIncome(int day, double newWashingIncome){
        HashMap<String, Double> registryAction = new HashMap<String, Double>();
        System.out.printf("\nWashing income increased by $ %f\n", newWashingIncome);
        washingIncome = washingIncome + newWashingIncome;
        serviceIncome = serviceIncome + newWashingIncome;
        totalIncome = totalIncome + newWashingIncome;
        currentBalance = currentBalance + newWashingIncome;
        System.out.printf("\nCurrent balance is $ %f \n", currentBalance);
        registryAction.put("closingWashingIncome", washingIncome);
        String formattedDay = String.format("Day_%d_washingIncome", day);
        budgetRegistry.add(formattedDay, registryAction);
        //System.out.println(budgetRegistry.getRegistry());
    }

    public void addSalaries(int day, double newSalary){
        HashMap<String, Double> registryAction = new HashMap<String, Double>();
        System.out.printf("\nTotal salary increased by $ %f\n", newSalary);
        salaries = salaries + newSalary;
        currentBalance = currentBalance - newSalary;
        System.out.printf("\nCurrent balance is $ %f \n", currentBalance);
        registryAction.put("closingSalaries", salaries);
        String formattedDay = String.format("Day_%d_salaries", day);
        budgetRegistry.add(formattedDay, registryAction);
        //System.out.println(budgetRegistry.getRegistry());
    }

    public void addBonuses(int day, double newBonus){
        HashMap<String, Double> registryAction = new HashMap<String, Double>();
        System.out.printf("\nTotal bonuses increased by $ %f\n", newBonus);
        bonuses = bonuses + newBonus;
        currentBalance = currentBalance - newBonus;
        System.out.printf("\nCurrent balance is $ %f \n", currentBalance);
        registryAction.put("bonuses", bonuses);
        String formattedDay = String.format("Day_%d_bonuses", day);
        budgetRegistry.add(formattedDay, registryAction);
        //System.out.println(budgetRegistry.getRegistry());
    }

    public void addVehicleAsset(int day, double newVehicleAsset){
        HashMap<String, Double> registryAction = new HashMap<String, Double>();
        System.out.printf("\nTotal vehicle assets increased by $ %f\n", newVehicleAsset);
        vehicleAssets = vehicleAssets + newVehicleAsset;
        System.out.printf("\nCurrent vehicle assets worth $ %f \n", vehicleAssets);
        registryAction.put("closingVehicleAssets", vehicleAssets);
        String formattedDay = String.format("Day_%d_vehicleAssets", day);
        budgetRegistry.add(formattedDay, registryAction);
        //System.out.println(budgetRegistry.getRegistry());
    }

    public void removeVehicleAsset(int day, double newVehicleAsset){
        HashMap<String, Double> registryAction = new HashMap<String, Double>();
        System.out.printf("\nTotal vehicle assets decreased by $ %f\n", newVehicleAsset);
        vehicleAssets = vehicleAssets - newVehicleAsset;
        System.out.printf("\nCurrent vehicle assets worth $ %f \n", vehicleAssets);
        registryAction.put("closingVehicleAssets", vehicleAssets);
        String formattedDay = String.format("Day_%d_vehicleAssets", day);
        budgetRegistry.add(formattedDay, registryAction);
        System.out.println(budgetRegistry.getRegistry());
    }





}