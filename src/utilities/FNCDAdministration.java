package utilities;

import staff.*;
import vehicle.*;
import activity.*;
import customer.Buyer;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Duy Duong, Ahmed.H.Biby
 *
 * This class acts as the simulator engine and operarations summary generator for the FNCD operation.
 * This class is to be instantiated only once in the main.
 */
public class FNCDAdministration {
    private boolean workingStatus = true;
    private int day = 1;
    private final int endDay = 30; // 30-day simulation
    private final int numStaffEach = 3;
    private final int numVehicleEach = 4;


    public Registry internRegistry= new Registry();
    public ArrayList<Intern> internArrayList = new ArrayList();
    public Registry mechanicRegistry= new Registry();
    public ArrayList<Mechanic> mechanicArrayList = new ArrayList();
    public Registry salesPersonRegistry= new Registry();
    public ArrayList<Driver> driverArrayList = new ArrayList();
    public Registry driverRegistry= new Registry();
    public ArrayList<Salesperson> salesPersonArrayList = new ArrayList();
    public ArrayList<Driver> injuredDriverArrayList= new ArrayList<>();
    public ArrayList<Staff> departedStaffs = new ArrayList<>();

    public Budget budget = new Budget();
    public Inventory inventory = new Inventory();

    /**
     * Start up FNCD simulation which has pre-event when FNCD hires staffs
     * and purchases vehicles then run 30-day business
     */
    public void start() {
        System.out.println();
        System.out.println("****************************************");
        System.out.println("    Welcome to the FNCD simulation");
        System.out.println("********** Inauguration day ************");

        // Hire all staffs
        for (int i = 0; i < numStaffEach; i++){
            hireIntern(day);
            hireMechanic(day);
            hireSalesperson(day);
            hireDriver(day);
        }

        // Purchases all vehicles
        for (int i = 0; i < numVehicleEach; i++){
            purchaseVehicle(new PerformanceCar(day));
            purchaseVehicle(new Car(day));
            purchaseVehicle(new Pickup(day));
            purchaseVehicle(new MotorCycle(day));
            purchaseVehicle(new MonsterTruck(day));
            purchaseVehicle(new ElectricCar(day));

        }

        dailyReport(day);
        System.out.println("********** End of the Inauguration day ************");

        // Start 30 days simulation
        operate();
    }

    /**
     * Purchase vehicle at current day and add to working inventory
     * @param vehicle new vehicle
     */
    public void purchaseVehicle(Vehicle vehicle) {
        budget.purchaseVehicle(vehicle, day);
        inventory.getWorkingInventory().add(vehicle);
    }

    /**
     * This method is the main simulator engine that has all the hiring, promoting, quiting, selling, washing, repairing,
     * and the financial operations.
     *
     */
    public void operate() {
        for (int day = 1; day <= endDay; day++) {
            if (day % 7 == 1 || day % 7 == 4)  {
                System.out.printf("\n********** day %d ************\n", day);
                System.out.println("********** Working & Racing day ************");
                opening(day);
                washing(day);
                repairing(day);
                selling(day);
                racing(day);
                ending(day);
                dailyReport(day);
            }
            else {
                System.out.printf("\n********** day %d ************\n", day);
                System.out.println("********** Working day ************");
                opening(day);
                washing(day);
                repairing(day);
                selling(day);
                ending(day);
                dailyReport(day);
            }
        }
        System.out.println("********** End of FNCD operation simulation  ************");
        System.out.println("********** End of FNCD simulation  ************");
    }

    /**
     * Do opening on given day
     *
     * @param day current day
     */
    public void opening(int day) {
        System.out.printf("Opening... (current budget $%.2f)\n", budget.getCurrentBalance());
        workForceMaintenance(day);
        inventoryMaintenance(day);
        System.out.println();
    }

    /**
     * Do washing activity
     * @param day current day
     */
    public void washing(int day) {
        System.out.println("Washing...");
        for (Intern intern : internArrayList) {
            intern.setActivity(new Wash(intern, inventory));
            intern.getActivity().performWork();
        }
        System.out.println();
    }

    /**
     * Do repairing activity
     * @param day current day
     */
    public void repairing(int day) {
        System.out.println("Repairing...");
        for (Mechanic mechanic : mechanicArrayList) {
            mechanic.setActivity(new Repair(mechanic, inventory));
            mechanic.getActivity().performWork();
        }
        System.out.println();
    }

    /**
     * Do selling activity
     * @param day current day
     */
    public void selling(int day) {
        System.out.println("Selling...");
        int numBuyers = (day % 7 == 6) ?
                RandomGenerator.randomIntGenerator(2, 8) :
                RandomGenerator.randomIntGenerator(0, 6);
        System.out.println("Number of buyers: " + numBuyers);
        // generate buyers
        ArrayList<Buyer> buyers = new ArrayList<>();
        for (int i = 0; i < numBuyers; i++) {
            buyers.add(new Buyer());
        }

        for (Salesperson salesperson : salesPersonArrayList) {
            salesperson.setActivity(new Sale(salesperson, inventory, buyers, budget));
            salesperson.getActivity().performWork();
        }
        System.out.println();
    }
    /**
     * Do racing activity
     * @param day current day
     */
    public void racing(int day){
        System.out.println("Racing...");
        Race race = new Race(inventory, this);
    }

    /**
     * Do ending activity
     * @param day current day
     */
    public void ending(int day) {
        System.out.println("Ending...");
        // Paying all salaries
        ArrayList<Staff> allStaffs = new ArrayList<>();
        allStaffs.addAll(internArrayList);
        allStaffs.addAll(mechanicArrayList);
        allStaffs.addAll(salesPersonArrayList);
        allStaffs.addAll(driverArrayList);
        allStaffs.addAll(injuredDriverArrayList);

        budget.addSalariesPayout(day,allStaffs);
        budget.addBonusesPayout(day, allStaffs);
        for (Staff staff : allStaffs) {
            staff.addSalary();
            staff.addTotalBonus();
        }

        // 10% one Intern quits
        Intern intern = internArrayList.get(0);
        intern.workOrQuit(day);
        if (!intern.isWorking()) {
            departedStaffs.add(intern);
            internArrayList.remove(intern);
        }

        // 10% one Mechanic quits
        Mechanic mechanic = mechanicArrayList.get(0);
        mechanic.workOrQuit(day);
        if (!mechanic.isWorking()) {
            departedStaffs.add(mechanic);
            mechanicArrayList.remove(mechanic);
        }

        // 10% one Intern quits
        Salesperson salesperson = salesPersonArrayList.get(0);
        salesperson.workOrQuit(day);
        if (!salesperson.isWorking()) {
            departedStaffs.add(salesperson);
            salesPersonArrayList.remove(salesperson);
        }
        ArrayList<Driver> dummyDriverArrayList = new ArrayList<Driver>();
        for (Driver driver:driverArrayList) {
            dummyDriverArrayList.add(driver);
        }

        for (Driver driver:dummyDriverArrayList) {
            if (driver.isInjured()) {
            injuredDriverArrayList.add(driver);
            driverArrayList.remove(driver);
        }}

        if(budget.getCurrentBalance() < 0) {
            budget.useReserve(day);
        }



        System.out.println();
    }

    /**
     * method that checks if there is any shortage in any of the workforce and hire if shortage exists
     * @param day: provided by the administration
     */
    public void workForceMaintenance(int day) {
        if (mechanicArrayList.size() < numStaffEach) {
            int mechanicShortage = numStaffEach - mechanicArrayList.size();
            for (int i=0; i<mechanicShortage; i++) {
                promoteInternToMechanic(day);
            }
        }
        if (salesPersonArrayList.size() < numStaffEach) {
            int salesPersonShortage = numStaffEach - salesPersonArrayList.size();
            for (int i=0; i<salesPersonShortage; i++) {
                promoteInternToSalesperson(day);
            }
        }
        if (internArrayList.size() < numStaffEach) {
            int interShortage = numStaffEach - internArrayList.size();
            for (int i=0; i< interShortage; i++) {
                hireIntern(day);
            }
        }

        if (driverArrayList.size() < numStaffEach) {
            int driverShortage = numStaffEach - driverArrayList.size();
            for (int i=0; i< driverShortage; i++) {
                hireDriver(day);
            }
        }
    }

    /**
     * Check if there is shortage of a vehicleType. If there is, purchase new vehicle
     * of that type and add them to working inventory
     *
     * @param day current day
     */
    public void inventoryMaintenance(int day) {
        ArrayList<Vehicle> workingInventory = inventory.getWorkingInventory();
        double numPerformanceCar = 0;
        double numCar = 0;
        double numPickup = 0;
        double numElectricCar = 0;
        double numMotorcycle = 0;
        double numMonsterTruck = 0;
        for (Vehicle vehicle : workingInventory) {
            switch (vehicle.getVehicleType()) {
                case PERFORMANCE_CAR:
                    numPerformanceCar++;
                    continue;
                case CAR:
                    numCar++;
                    continue;
                case PICKUP:
                    numPickup++;
                    continue;
                case ELECTRIC_CAR:
                    numElectricCar++;
                    continue;
                case MONSTER_TRUCK:
                    numMonsterTruck++;
                    continue;
                case MOTORCYCLE:
                    numMotorcycle++;
                    continue;
                default:
            }
        }
        if (numPerformanceCar < numVehicleEach) {
            for (int i = 0; i < (numVehicleEach - numPerformanceCar); i++) {
                purchaseVehicle(new PerformanceCar(day));
            }
        }
        if (numCar < numVehicleEach) {
            for (int i = 0; i < (numVehicleEach - numCar); i++) {
                purchaseVehicle(new Car(day));
            }
        }
        if (numPickup < numVehicleEach) {
            for (int i = 0; i < (numVehicleEach - numPickup); i++) {
                purchaseVehicle(new Pickup(day));
            }

        }
        if (numMotorcycle < numVehicleEach) {
            for (int i = 0; i < (numVehicleEach - numMotorcycle); i++) {
                purchaseVehicle(new MotorCycle(day));
            }
        }
        if (numMonsterTruck < numVehicleEach) {
            for (int i = 0; i < (numVehicleEach - numMonsterTruck); i++) {
                purchaseVehicle(new MonsterTruck(day));
            }
        }
        if (numElectricCar < numVehicleEach) {
            for (int i = 0; i < (numVehicleEach - numElectricCar); i++) {
                purchaseVehicle(new ElectricCar(day));
            }
        }
    }

    /**
     * method for hiring new intern
     * @param day: provided by the administration
     */
    public void hireIntern(int day){
        Intern intern = new Intern(day);
        inputHireRegistry(intern);
        internArrayList.add(intern);
    }

    /**
     * method for hiring new mechanic
     * @param day: provided by the administration
     */
    public void hireMechanic(int day){
        Mechanic mechanic = new Mechanic(day);
        inputHireRegistry(mechanic);
        mechanicArrayList.add(mechanic);
    }

    /**
     * method for hiring new salesperson
     * @param day: provided by the administration
     */
    public void hireSalesperson(int day){
        Salesperson salesperson = new Salesperson(day);
        inputHireRegistry(salesperson);
        salesPersonArrayList.add(salesperson);
    }

    /**
     * method for hiring new driver
     * @param day: provided by the administration
     */
    public void hireDriver(int day){
        Driver driver = new Driver(day);
        inputHireRegistry(driver);
        driverArrayList.add(driver);
    }

    /**
     * This is to save registry of hiring new staff
     *
     * @param staff new hired staff
     */
    public void inputHireRegistry(Staff staff) {
        HashMap<String, String> registryAction = new HashMap<>();
        System.out.printf("Hired %s %s.\n", staff.getJobTitle(), staff.getName());
        registryAction.put("name", staff.getName());
        String formattedDay = String.format("Day_%d_%s_%s", day, staff.getJobTitle(), staff.getName().replace(' ', '_'));
        staff.getRegistry().add(formattedDay, registryAction);
    }

    /**
     * method that promotes an intern in case of a given mechanic quited
     * @param day: provided by the administration
     */
    public void promoteInternToSalesperson(int day){
        Salesperson salesperson = new Salesperson(day);
        Intern interTobePromoted = internArrayList.get(0);
        salesperson.setName(interTobePromoted.getName());
        salesperson.setSalary(interTobePromoted.getSalary());
        salesperson.setDaysOfWork(interTobePromoted.getDaysOfWork());
        salesperson.setTotalBonus(interTobePromoted.getTotalBonus());
        HashMap<String, String> registryAction = new HashMap<>();
        System.out.printf("\n%s is promoted from Intern to Salesperson.\n", interTobePromoted.getName());
        registryAction.put("name", salesperson.getName());
        String formattedDay = String.format("Day_%d_salesperson_%s", day, interTobePromoted.getName().replace(' ', '_'));
//        FNCDAdministration.mechanicRegistry.add(formattedDay, registryAction);
        salesPersonArrayList.add(salesperson);
        internArrayList.remove(interTobePromoted);
    }

    /**
     * method that promotes an intern in case of a given mechanic quited
     * @param day: provided by the administration
     */
    public void promoteInternToMechanic(int day){
        Mechanic mechanic = new Mechanic(day);
        Intern interTobePromoted = internArrayList.get(0);
        mechanic.setName(interTobePromoted.getName());
        mechanic.setSalary(interTobePromoted.getSalary());
        mechanic.setDaysOfWork(interTobePromoted.getDaysOfWork());
        mechanic.setBonus(interTobePromoted.getBonus());
        HashMap<String, String> registryAction = new HashMap<String, String>();
        System.out.printf("\n%s is promoted from intern to Salesperson.\n", interTobePromoted.getName());
        registryAction.put("name", mechanic.getName());
        String formattedDay = String.format("Day_%d_salesperson_%s", day, interTobePromoted.getName().replace(' ', '_'));
//        FNCDAdministration.mechanicRegistry.add(formattedDay, registryAction);
        mechanicArrayList.add(mechanic);
        internArrayList.remove(interTobePromoted);
    }

    /**
     * This method is the daily report generator that summarizes all the necessary operational details.
     *
     * @param day: the day that is provided by the FNCDadministration for the FNCD operation simulation.
     */
    public void dailyReport(int day) {
        // Print out staffs
        System.out.println("Staff members");
        System.out.println("Title | Name | TotalDaysWorked | TotalNormalPay | TotalBonusPay | Working");
        System.out.println("------------------------------------------------------------------------");
        ArrayList<Staff> allStaffs = new ArrayList<>();
        allStaffs.addAll(internArrayList);
        allStaffs.addAll(mechanicArrayList);
        allStaffs.addAll(salesPersonArrayList);
        allStaffs.addAll(driverArrayList);
        allStaffs.addAll(injuredDriverArrayList);
        allStaffs.addAll(departedStaffs);
        for (Staff staff : allStaffs) {
            System.out.printf("%s | %s | %d | %.2f | %.2f | %s\n",
                    staff.getJobTitle(),
                    staff.getName(),
                    staff.getDaysOfWork(),
                    staff.getSalary(),
                    staff.getTotalBonus(),
                    staff.isWorking()
            );
        }

        System.out.println();

        ArrayList<Vehicle> allVehicles = new ArrayList<>();
        allVehicles.addAll(inventory.getWorkingInventory());
        allVehicles.addAll(inventory.getSoldVehicles());

        // Print out inventory
        System.out.println("Inventory");
        System.out.println("Type | Name | Cost | SalePrice | Condition | Cleanliness | InStock");
        System.out.println("------------------------------------------------------------------------");
        for (Vehicle vehicle : allVehicles) {
            System.out.printf("%s | %s | %.2f | %.2f | %s | %s | %s\n",
                    vehicle.getVehicleType(),
                    vehicle.getName(),
                    vehicle.getInitialCost(),
                    vehicle.getSalePrice(),
                    vehicle.getVehicleCondition(),
                    vehicle.getCleanliness(),
                    vehicle.isInStock());
        }

        System.out.println();

        // Print out all budget
        System.out.println("Budget");
        System.out.println("TotalOperatingBudget | TotalSale");
        System.out.printf("%.2f | %20.2f",
                budget.getCurrentBalance(),
                budget.getSalesIncome()
        );
        System.out.println();
    }
}


