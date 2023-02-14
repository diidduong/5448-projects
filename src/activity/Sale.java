package activity;

import customer.Buyer;
import staff.Staff;
import utilities.Budget;
import utilities.Inventory;
import utilities.RandomGenerator;
import vehicle.Vehicle;

import java.util.ArrayList;

/**
 * @author Duy Duong
 *
 * Subclass of Activity which allows ability to perform sale action
 */
public class Sale extends Activity {

    public Sale(Staff provider) {
        super(provider);
    }

    /**
     * Perform all sale actions. Salesperson will get a vehicle from inventory and
     * tries to sell to selected Buyer from the list. After serving that Buyer, remove
     * from the list.
     *
     * @param budget current budget
     * @param inventory current inventory
     * @param buyers list of all current buyers
     */
    public void performSale(Budget budget, Inventory inventory, ArrayList<Buyer> buyers) {
        // TODO: select some of buyers to serve
        for (int i = 0; i < buyers.size() / 3; i++) {
            Buyer selectedBuyer = getNextBuyer(buyers);
            if (selectedBuyer == null) {
                break; // No buyer coming today
            }
            Vehicle selectedVehicle = getNextVehicle(inventory, selectedBuyer.getWantedType());
            if (selectedVehicle == null) {
                break; // No vehicle for sale today
            }
            sellVehicleToBuyer(selectedVehicle, selectedBuyer, budget, inventory);

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
     * @param budget current budget
     * @param inventory current inventory
     */
    public void sellVehicleToBuyer(Vehicle selectedVehicle, Buyer buyer, Budget budget, Inventory inventory) {
        double successfulSaleProbs = buyer.getBuyingType().getProbability();
        boolean sellable = false;

        // get bonus chance of sale
        successfulSaleProbs += getSaleChanceBonus(
                selectedVehicle,
                buyer.getWantedType().equals(selectedVehicle.getClass().getSimpleName())
        );

        // check if successful sale by probability
        sellable = RandomGenerator.probabilisticOutcomeGenerator(successfulSaleProbs);
        if (sellable) {
            // collect money from sale
            // TODO: get the correct day
            budget.addSalesIncome(0, selectedVehicle.getSalePrice());

            // give bonus to Salesperson
            getProvider().addBonus(getBonusByType(selectedVehicle.getClass().getSimpleName()));

            // move vehicle from working inventory to sold vehicles
            inventory.moveVehicleToSoldVehicles(selectedVehicle);
        }
    }

    /**
     * Get next vehicle for sale. Try to get the most expensive Vehicle of the type
     * the Buyer wants that is in the inventory. If no Vehicles of the Buyer’s Vehicle
     * type choice are available, get the most expensive Vehicle left in Inventory
     *
     * @param inventory inventory
     * @return vehicle if exists, null otherwise
     */
    private Vehicle getNextVehicle(Inventory inventory, String wantedType) {
        Vehicle selectedVehicle = inventory.getMostExpensiveVehicleForSaleByType(wantedType);
        if (selectedVehicle == null) {
            selectedVehicle = inventory.getMostExpensiveVehicleForSale();
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
     * @param vehicleType Vehicle Type
     * @return bonus by type, 0 otherwise
     */
    private double getBonusByType(String vehicleType) {
        switch (vehicleType) {
            case "PerformanceCar":
                return 350;
            case "Car":
                return 160;
            case "Pickup":
                return 220;
            default:
                return 0;
        }
    }
}
