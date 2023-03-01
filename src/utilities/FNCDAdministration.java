package utilities;

import customer.Buyer;
import staff.*;
import tracking.EventPublisher;
import tracking.Logger;
import tracking.Message;
import tracking.Tracker;
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
    private final int NUM_RACE_VEHICLE = 3;
    private final double INITIAL_BALANCE = 500000;
    private final double RESERVE_BALANCE = 250000;

    ArrayList<Staff> staffs = new ArrayList<>();
    ArrayList<Staff> departedStaffs = new ArrayList<>();

    Budget budget = new Budget(INITIAL_BALANCE);
    Inventory inventory = new Inventory();

    EventPublisher publisher;
    Logger logger;
    Tracker tracker;

    /**
     * Start up FNCD simulation which has pre-event when FNCD hires staffs
     * and purchases vehicles then run 30-day business
     */
    public void start() {
        System.out.println();
        System.out.println("****************************************");
        System.out.println("    Welcome to the FNCD simulation");
        System.out.println("********** Inauguration day ************");

        // Subscribe tracker at the beginning of simulation
        publisher = new EventPublisher();
        tracker = new Tracker();
        publisher.addSubscriber(tracker);

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

        publisher.removeSubscriber(tracker); // remove tracker subscription at the end of simulation
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

            String msg = String.format("\nReserve balance %.2f is used, current balance is %.2f\n",
                    RESERVE_BALANCE,
                    budget.getCurrentBalance()
            );
            publisher.notifySubscribers(new Message(msg, 0, 0));
        }
        // subtract vehicle sale price from balance
        budget.subtractBalance(salePrice);

        // add vehicle to working inventory
        inventory.getWorkingInventory().add(vehicle);

        // output purchase vehicle event
        String msg = String.format("Purchased %s, %s %s %s for $ %.2f\n",
                vehicle.getVehicleCondition(),
                vehicle.getCleanliness(),
                vehicle.getVehicleType(),
                vehicle.getName(),
                salePrice
        );
        publisher.notifySubscribers(new Message(msg, 0, 0));
    }

    /**
     * This method is the main simulator engine that has all the hiring, promoting, quiting, selling, washing, repairing,
     * and the financial operations.
     */
    public void operate() {
        while (day <= END_DAY) {
            // initialization of daily logger
            logger = new Logger(day);
            publisher.addSubscriber(logger);
            // Set day for logger and tracker
            logger.setDay(day);
            tracker.setDay(day);


            // Increase num work day of all staffs
            for (Staff staff : staffs) {
                staff.addWorkDay();
            }

            // Sunday and Wednesday is for both working and racing activity, other day is working
            if (day % 7 == 1 || day % 7 == 4) {
                System.out.printf("\n********** day %d ************\n", day);
                System.out.println("********** Working & Racing day ************");

                opening();
                washing();
                repairing();
                selling();
                racing();
                ending();
            } else {
                System.out.printf("\n********** day %d ************\n", day);
                System.out.println("********** Working day ************");
                opening();
                washing();
                repairing();
                selling();
                ending();
            }

            dailyReport(); // tabular report at the end of the day

            publisher.removeSubscriber(logger); // remove logger at the end of each day
            tracker.printDailySummary(); // tracker summary

            day++; // next day work
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
            ((Intern) intern).washVehicles(inventory.getWorkingInventory(), publisher);
        }
    }

    /**
     * Do repair activity
     */
    public void repairing() {
        System.out.println("\nRepairing...");
        ArrayList<Staff> mechanics = Staff.getStaffListByType(staffs, Staff.JobTitle.MECHANIC);
        for (Staff mechanic : mechanics) {
            ((Mechanic) mechanic).repairVehicles(inventory.getWorkingInventory(), publisher);
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
            Vehicle vehicle = ((Salesperson) salespersonList.get(randomIdx)).sellVehicles(buyer, inventory, publisher);
            if (vehicle != null) {
                inventory.moveVehicleToSoldVehicles(vehicle);
                budget.addBalance(vehicle.getSalePrice());
                budget.addSaleIncome(vehicle.getSalePrice());
                // Announce change in money for FNCD
                String msg = String.format("Added sale income $%.2f to current balance\n", vehicle.getSalePrice());
                publisher.notifySubscribers(new Message(msg, 0, vehicle.getSalePrice()));
            }
        }
    }

    /**
     * Do racing activity
     */
    public void racing() {
        System.out.println("\nRacing...");

        ArrayList<Vehicle> vehiclesForRacing = inventory.getVehiclesForRace(NUM_RACE_VEHICLE);
        if (vehiclesForRacing.size() == 0){
            System.out.println("FNCD is unable to participate in the race today due to the lack of running vehicles");
        } else {
            participateInRace(vehiclesForRacing);
        }
    }

    /**
     * simulates the race results of given Vehicles and their associated drivers.
     * Update the states for drivers (winning and injury) and vehicles (winning and being Broken)
     * depending on their rank in the races
     *
     * @param vehiclesForRacing: available vehicles for racing
     */
    public void participateInRace(ArrayList<Vehicle> vehiclesForRacing){
        ArrayList<Staff> availableDrivers = Staff.getStaffListByType(staffs, Staff.JobTitle.DRIVER);
        ArrayList<Integer> disposableRanks = new ArrayList<>();
        for (int i = 0; i < vehiclesForRacing.size(); i++){
            Driver driver = (Driver) availableDrivers.get(i);
            Vehicle vehicle = vehiclesForRacing.get(i);

            // Get a random rank from 1-20
            int rank = RandomGenerator.randomIntGenerator(1,20);
            disposableRanks.add(rank);
            while(disposableRanks.contains(rank)){
                rank = RandomGenerator.randomIntGenerator(1,20); // find a unique rank
            }
            disposableRanks.add(rank);

            // Output race attendance
            String msg = String.format("%s (Driver) raced with %s (%s) and achieved rank no. %d.\n", driver.getName(),
                    vehicle.getName(), vehicle.getVehicleType(), rank
            );
            publisher.notifySubscribers(new Message(msg, 0, 0));

            if (rank <= 3) {
                // Output racing result
                msg = String.format("%s (Driver) won with %s (%s) (earned $ 1000 bonus)!\n",driver.getName(), vehicle.getName(),
                        vehicle.getVehicleType());
                publisher.notifySubscribers(new Message(msg, 1000, 0));

                driver.setRacesWon(driver.getRacesWon() + 1); // increase driver win count
                driver.addBonus(1000); // Add bonus to Driver that drove the winning vehicle
                vehicle.setRacesWon(vehicle.getRacesWon() + 1); // increase vehicle win count

                // Increase sale price
                if (vehicle.getRacesWon() == 1) {
                    vehicle.setSalePrice(vehicle.getSalePrice() * 1.1);
                }
            } else if (rank >= 16){
                // Check if Driver becomes Injured
                driver.injury(publisher);

                // If driver is injured, Driver leave FNCD or move to departed staff
                if (driver.isInjured()) {
                    driver.setWorking(false);
                    staffs.remove(driver);
                    departedStaffs.add(driver);
                }
                // Vehicles become Broken if they lose
                vehicle.setVehicleCondition(Vehicle.VehicleCondition.BROKEN);
                // Output Broken vehicle result after the race
                msg = String.format("%s (%s) went BROKEN.\n", vehicle.getName(), vehicle.getVehicleType());
                publisher.notifySubscribers(new Message(msg, 0, 0));
            }
        }
    }

    /**
     * Do ending activity
     */
    public void ending() {
        System.out.println("\nEnding...");
        String msg = "";
        // pay all salaries
        budget.addSalariesPayout(staffs, publisher);
        budget.addBonusesPayout(staffs, publisher);

        for (Staff staff : staffs) {
            // Output payout for each staff including daily rate and bonuses
            msg = String.format("%s %s received $%.2f (salary) and $%.2f (bonuses)\n",
                    staff.getJobTitle(),
                    staff.getName(),
                    staff.getDailyRate(),
                    staff.getBonus()
            );
            publisher.notifySubscribers(new Message(msg, staff.getSalary() + staff.getBonus(), 0));

            staff.addSalary(); // pay the base daily rate to staff
            staff.addTotalBonus(); // pay bonuses earned by staff
        }

        // One member of each type can quit at 10%, if quit, move staff to departed staff list
        for (Staff.JobTitle title : Staff.JobTitle.values()) {
            if (title == Staff.JobTitle.DRIVER) continue; // Drivers don't quit
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
     * method that promotes an intern in case of a given staff quited
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

        // Print out inventory
        ArrayList<Vehicle> allVehicles = new ArrayList<>();
        allVehicles.addAll(inventory.getWorkingInventory());
        allVehicles.addAll(inventory.getSoldVehicles());

        System.out.println("Inventory");
        System.out.println("Type | Name | Cost | SalePrice | Condition | Cleanliness | InStock | RacesWon");
        System.out.println("------------------------------------------------------------------------");
        for (Vehicle vehicle : allVehicles) {
            System.out.printf("%s | %s | %.2f | %.2f | %s | %s | %s | %d\n",
                    vehicle.getVehicleType(),
                    vehicle.getName(),
                    vehicle.getInitialCost(),
                    vehicle.getSalePrice(),
                    vehicle.getVehicleCondition(),
                    vehicle.getCleanliness(),
                    vehicle.isInStock(),
                    vehicle.getRacesWon()
            );
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


