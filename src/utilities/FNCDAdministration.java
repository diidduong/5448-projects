package utilities;

import customer.Buyer;
import staff.Intern;
import staff.Mechanic;
import staff.Salesperson;
import staff.Staff;
import vehicle.Vehicle;

import java.util.ArrayList;

/**
 * @author Duy Duong, Ahmed.H.Biby
 *
 * This class acts as the simulator engine and operarations summary generator for the FNCD operation.
 * This class is to be instantiated only once in the main.
 */
public class FNCDAdministration {
    private int day = 1;
    private final int END_DAY = 30; // 30-day simulation
    private final int NUM_STAFF_EACH = 3;
    private final int NUM_VEHICLE_EACH = 4;
    private final double INITIAL_BALANCE = 500000;
    private final double RESERVE_BALANCE = 250000;

    ArrayList<Staff> staffs = new ArrayList<>();
    ArrayList<Staff> departedStaffs = new ArrayList<>();

    Budget budget = new Budget(INITIAL_BALANCE);
    Inventory inventory = new Inventory();

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
        for (Staff.JobTitle title : Staff.JobTitle.values()) {
            for (int i = 0; i < NUM_STAFF_EACH; i++){
                hireStaff(title);
            }
        }

        // Purchases all vehicles
        for (Vehicle.VehicleType type : Vehicle.VehicleType.values()) {
            for (int i = 0; i < NUM_VEHICLE_EACH; i++){
                purchaseVehicle(type);
            }
        }

        dailyReport();
        System.out.println("********** End of the Inauguration day ************");

        // Start 30 days simulation
        operate();
    }

    /**
     * Purchase vehicle with given type and add to working inventory. If low fund,
     * add reserve fund before purchasing
     *
     * @param type vehicle type
     */
    public void purchaseVehicle(Vehicle.VehicleType type) {
        Vehicle vehicle = Vehicle.createVehicleByType(type);

        double salePrice = vehicle.getSalePrice();
        // add reserve balance if not enough money
        if (budget.getCurrentBalance() < salePrice) {
            budget.addBalance(RESERVE_BALANCE);
            // Announce "Adding money to the FNCD budget due to low funds"
            System.out.printf("\nReserve balance %.2f is used", RESERVE_BALANCE);
            System.out.printf("\nCurrent balance is $ %.2f \n", budget.getCurrentBalance());
        }
        // subtract vehicle sale price from balance
        budget.subtractBalance(salePrice);

        // add vehicle to working inventory
        inventory.getWorkingInventory().add(vehicle);

        // output purchase vehicle event
        System.out.printf("Purchased %s, %s %s for $ %.2f\n",
                vehicle.getVehicleCondition(),
                vehicle.getCleanliness(),
                vehicle.getName(),
                salePrice
        );
    }

    /**
     * This method is the main simulator engine that has all the hiring, promoting, quiting, selling, washing, repairing,
     * and the financial operations.
     *
     */
    public void operate() {
        while (day <= END_DAY) {
            day++; // next day work

            // Increase num work day of all staffs
            for (Staff staff : staffs) {
                staff.addWorkDay();
            }

            if (day % 7 != 0) {
                System.out.printf("\n********** day %d ************\n", day);
                System.out.println("********** Working day ************");

                opening();
                washing();
                repairing();
                selling();
                ending();

                dailyReport();
            } else {
                System.out.printf("\n********** day %d ************\n", day);
                System.out.println("********** Weekend ************");
            }

        }
        System.out.println("********** End of FNCD operation simulation  ************");
        System.out.println("********** End of FNCD simulation  ************");
    }

    /**
     * Do open activity
     *
     */
    public void opening() {
        System.out.printf("\nOpening... (current budget $%.2f)\n", budget.getCurrentBalance());

        workForceMaintenance();
        inventoryMaintenance();
    }

    /**
     * Do wash activity
     */
    public void washing() {
        System.out.println("\nWashing...");
        ArrayList<Staff> interns = Staff.getStaffListByType(staffs, Staff.JobTitle.INTERN);
        for (Staff intern : interns) {
            ((Intern) intern).washVehicles(inventory.getWorkingInventory());
        }
    }

    /**
     * Do repair activity
     */
    public void repairing() {
        System.out.println("\nRepairing...");
        ArrayList<Staff> mechanics = Staff.getStaffListByType(staffs, Staff.JobTitle.MECHANIC);
        for (Staff mechanic : mechanics) {
            ((Mechanic) mechanic).repairVehicles(inventory.getWorkingInventory());
        }
    }

    /**
     * Do selling activity
     */
    public void selling() {
        System.out.println("\nSelling...");

        ArrayList<Buyer> buyers = getBuyers(day);

        // Assign random salesperson to buyer to sell vehicle
        ArrayList<Staff> salespersonList = Staff.getStaffListByType(staffs, Staff.JobTitle.SALESPERSON);
        for (Buyer buyer : buyers) {
            int randomIdx = RandomGenerator.randomIntGenerator(0,salespersonList.size()-1);
            Vehicle vehicle = ((Salesperson) salespersonList.get(randomIdx)).sellVehicles(buyer, inventory);
            if (vehicle != null) {
                inventory.moveVehicleToSoldVehicles(vehicle);
                budget.addBalance(vehicle.getSalePrice());
                budget.addSaleIncome(vehicle.getSalePrice());
                // Announce change in money for FNCD
                System.out.printf("Added sale income %.2f to current balance\n", vehicle.getSalePrice());
            }
        }
    }

    /**
     * Do ending activity
     */
    public void ending() {
        System.out.println("\nEnding...");

        // pay all salaries
        budget.addSalariesPayout(staffs);
        budget.addBonusesPayout(staffs);
        for (Staff staff : staffs) {
            staff.addSalary();
            staff.addTotalBonus();
        }

        // One member of each type can quit at 10%, if quit, move staff to departed staff list
        for (Staff.JobTitle title : Staff.JobTitle.values()) {
            ArrayList<Staff> staffList = Staff.getStaffListByType(staffs, title);
            Staff staff = staffList.get(0);
            staff.workOrQuit(); // staff going to quit
            if (!staff.isWorking()) {
                staffs.remove(staff);
                departedStaffs.add(staff);
            }
        }
    }

    /**
     * method that checks if there is any shortage in any of the workforce and hire if shortage exists
     */
    public void workForceMaintenance() {
        ArrayList<Staff> interns = Staff.getStaffListByType(staffs, Staff.JobTitle.INTERN);
        int numMechanic = Staff.countStaffByType(staffs, Staff.JobTitle.MECHANIC);
        int numSalesperson = Staff.countStaffByType(staffs, Staff.JobTitle.SALESPERSON);

        // Promote existing interns to mechanic if shortage
        int mechanicShortage = NUM_STAFF_EACH - numMechanic;
        for (int i=0; i<mechanicShortage; i++) {
            if (interns.size() > 0) {
                Staff promotingIntern = interns.get(0);
                interns.remove(promotingIntern);
                promoteStaff(promotingIntern, Staff.JobTitle.MECHANIC);
            }
        }

        // Promote existing interns to salesperson if shortage
        int salesPersonShortage = NUM_STAFF_EACH - numSalesperson;
        for (int i=0; i<salesPersonShortage; i++) {
            if (interns.size() > 0) {
                Staff promotingIntern = interns.get(0);
                interns.remove(promotingIntern);
                promoteStaff(promotingIntern, Staff.JobTitle.SALESPERSON);
            }
        }

        // Hire all missing staff
        for (Staff.JobTitle title : Staff.JobTitle.values()) {
            int numStaff = Staff.countStaffByType(staffs, title);
            int staffShortage = NUM_STAFF_EACH - numStaff;
            for (int i = 0; i < staffShortage; i++) {
                hireStaff(title);
            }
        }
    }

    /**
     * Check if there is shortage of a vehicleType. If there is, purchase new vehicle
     * of that type and add them to working inventory
     */
    public void inventoryMaintenance() {
        ArrayList<Vehicle> workingInventory = inventory.getWorkingInventory();
        for (Vehicle.VehicleType type : Vehicle.VehicleType.values()) {
            int numVehicleShortage = NUM_VEHICLE_EACH - Vehicle.countVehicleByType(workingInventory, type);
            for (int i = 0; i < numVehicleShortage; i++) {
                purchaseVehicle(type);
            }
        }
    }

    /**
     * method for hiring new staff
     */
    public void hireStaff(Staff.JobTitle title){
        Staff newStaff = Staff.createStaffByType(title);
        staffs.add(newStaff);

        // Output new hire event
        System.out.printf("Hired %s %s\n", title, newStaff.getName());
    }

    /**
     * method that promotes an intern in case of a given mechanic quited
     */
    public void promoteStaff(Staff staff, Staff.JobTitle title){
        Staff newStaff = Staff.createStaffByType(title);

        newStaff.setName(staff.getName());
        newStaff.setSalary(staff.getSalary());
        newStaff.setDaysOfWork(staff.getDaysOfWork());
        newStaff.setBonus(staff.getBonus());

        // output promote event
        System.out.printf("%s is promoted from %s to %s.\n",
                staff.getName(),
                staff.getJobTitle(),
                newStaff.getJobTitle()
        );

        staffs.add(newStaff);
        staffs.remove(staff);
    }

    /**
     * Get a list of buyer on given day
     *
     * @param day current day
     * @return buyer list
     */
    public ArrayList<Buyer> getBuyers(int day) {
        int numBuyers = (day % 7 == 6) ?
                RandomGenerator.randomIntGenerator(2, 8) :
                RandomGenerator.randomIntGenerator(0, 6);
        System.out.println("Number of buyers: " + numBuyers);

        // generate buyers
        ArrayList<Buyer> buyers = new ArrayList<>();
        for (int i = 0; i < numBuyers; i++) {
            buyers.add(new Buyer());
        }

        return buyers;
    }

    /**
     * This method is the daily report generator that summarizes all the necessary operational details.
     *
     */
    public void dailyReport() {
        // Print out staffs
        System.out.println("\nDaily Report...");
        System.out.println("Staff members");
        System.out.println("Title | Name | TotalDaysWorked | TotalNormalPay | TotalBonusPay | Working");
        System.out.println("------------------------------------------------------------------------");
        ArrayList<Staff> allStaffs = new ArrayList<>();
        allStaffs.addAll(staffs);
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


