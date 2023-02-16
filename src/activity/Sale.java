package activity;

import customer.Buyer;
import staff.Staff;
import utilities.Budget;
import utilities.Inventory;
import utilities.RandomGenerator;
import vehicle.Vehicle;

import java.util.ArrayList;

/**
 * @author Duy Duong, Ahmed.H.Biby
 *
 * Subclass of Activity which allows ability to perform sale action
 */
public class Sale extends Activity {

    /**
     * Constructor to initialize sale activity which requires inventory, buyers, and budget
     *
     * @param provider salesperson
     * @param inventory current inventory
     * @param buyers all buyers
     * @param budget current budget
     */
    public Sale(Staff provider, Inventory inventory, ArrayList<Buyer> buyers, Budget budget) {
        super(provider, inventory, buyers, budget);
    }

    /**
     * Perform all sale actions. Salesperson will get a vehicle from inventory and
     * tries to sell to selected Buyer from the list. After serving that Buyer, remove
     * from the list.
     *
     * Assumption: Each salesperson will serve at max 6 buyers per day
     */
    @Override
    public void performWork() {
        ArrayList<Buyer> buyers = getBuyers();

        for (int i = 0; i < buyers.size() && i < RandomGenerator.randomIntGenerator(0, 6); i++) {
            Buyer selectedBuyer = getNextBuyer(buyers);
            if (selectedBuyer == null) {
                break; // No buyer coming today
            }
            Vehicle selectedVehicle = getNextVehicle(selectedBuyer.getWantedType());
            if (selectedVehicle == null) {
                break; // No vehicle for sale today
            }
            sellVehicleToBuyer(selectedVehicle, selectedBuyer);

            // Remove selected buyer from the serving list
            buyers.remove(selectedBuyer);
        }
    }

    /**
     * Sell selected Vehicle to Buyer with a calculated probability. If sale is successful, add sale income
     * into operating budget, give bonus to Salesperson by Vehicle Type, and move selected Vehicle to sold
     * inventory.
     *
     * @param selectedVehicle not null Vehicle
     * @param buyer not null Buyer
     */
    public void sellVehicleToBuyer(Vehicle selectedVehicle, Buyer buyer) {
        double successfulSaleProbs = buyer.getBuyingType().getProbability();
        boolean sellable = false;

        // get bonus chance of sale
        successfulSaleProbs += getSaleChanceBonus(
                selectedVehicle,
                buyer.getWantedType() == selectedVehicle.getVehicleType()
        );

        // check if successful sale by probability
        sellable = RandomGenerator.probabilisticOutcomeGenerator(successfulSaleProbs);
        if (sellable) {
            // collect money from sale
            // TODO: get the correct day
            getBudget().addSalesIncome(0, selectedVehicle);

            double bonus = getBonusByType(selectedVehicle);
            // give bonus to Salesperson
            getProvider().addBonus(bonus);

            // move vehicle from working inventory to sold vehicles
            getInventory().moveVehicleToSoldVehicles(selectedVehicle);

            System.out.printf("%s %s sold %s %s %s to Buyer for $%.2f (earned $%.2f bonus)\n",
                    getProvider().getJobTitle(),
                    getProvider().getName(),
                    selectedVehicle.getCleanliness(),
                    selectedVehicle.getVehicleCondition(),
                    selectedVehicle.getName(),
                    selectedVehicle.getSalePrice(),
                    bonus
            );
        } else {
            System.out.printf("%s %s sold %s %s %s to Buyer unsuccessfully\n",
                    getProvider().getJobTitle(),
                    getProvider().getName(),
                    selectedVehicle.getCleanliness(),
                    selectedVehicle.getVehicleCondition(),
                    selectedVehicle.getName()
            );
        }
    }

    /**
     * Get next vehicle for sale. Try to get the most expensive Vehicle of the type
     * the Buyer wants that is in the inventory. If no Vehicles of the Buyer’s Vehicle
     * type choice are available, get the most expensive Vehicle left in Inventory
     *
     * @param wantedType wanted Vehicle Type, not null
     * @return vehicle if exists, null otherwise
     */
    private Vehicle getNextVehicle(Vehicle.VehicleType wantedType) {
        Vehicle selectedVehicle = getInventory().getMostExpensiveVehicleForSaleByType(wantedType);
        if (selectedVehicle == null) {
            selectedVehicle = getInventory().getMostExpensiveVehicleForSale();
        }
        return selectedVehicle;
    }

    /**
     * Get next Buyer from the list.
     *
     * Assumption: first come first served
     *
     * @param buyers buyer list
     * @return first buyer from the list, null if empty list
     */
    private Buyer getNextBuyer(ArrayList<Buyer> buyers) {
        if (buyers.size() > 0) {
            return buyers.get(0);
        }
        return null;
    }

    /**
     * method that calculates the bonus chance for selling a car
     * @param vehicle to be sold
     * @param isWantedType buyer type
     * @return
     */
    private double getSaleChanceBonus(Vehicle vehicle, boolean isWantedType) {
        double bonusChance = 0;
        if (vehicle.getVehicleCondition() == Vehicle.VehicleCondition.LIKE_NEW) {
            bonusChance += 0.1;
        }
        if (vehicle.getCleanliness() == Vehicle.Cleanliness.SPARKING) {
            bonusChance += 0.1;
        }
        if (!isWantedType) {
            bonusChance -= 0.2;
        }
        return bonusChance;
    }

    /**
     * Get bonus for successful sale by vehicle type
     * $350 for Performance Car
     * $160 for Car
     * $220 for Pickup
     *
     * @param vehicle selected Vehicle
     * @return bonus by type, 0 otherwise
     */
    private double getBonusByType(Vehicle vehicle) {
        switch (vehicle.getVehicleType()) {
            case PERFORMANCE_CAR:
                return 350;
            case CAR:
                return 160;
            case PICKUP:
                return 220;
            default:
                return 0;
        }
    }
}
