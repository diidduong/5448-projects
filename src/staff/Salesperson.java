package staff;

import customer.Buyer;
import utilities.Inventory;
import utilities.RandomGenerator;
import vehicle.Vehicle;

/**
 * @author Duy Duong, Ahmed.H.Biby
 *
 * Salesperson is subclass of Staff which has JobTitle and dailyRate
 */
public class Salesperson extends Staff {
    public Salesperson(){
        super(JobTitle.SALESPERSON, 150);
    }

    /**
     *
     * @param buyer
     * @param inventory
     * @return
     */
    public Vehicle sellVehicles(Buyer buyer, Inventory inventory) {
        if (buyer == null) {
            return null; // No buyer coming today
        }
        Vehicle selectedVehicle = getNextVehicle(inventory, buyer.getWantedType());
        if (selectedVehicle == null) {
            return null; // No vehicle for sale today
        }
        return sellVehicles(selectedVehicle, buyer);
    }

    /**
     * Sell selected Vehicle to Buyer with a calculated probability. If sale is successful, add sale income
     * into operating budget, give bonus to Salesperson by Vehicle Type, and move selected Vehicle to sold
     * inventory.
     *
     * @param selectedVehicle not null Vehicle
     * @param buyer not null Buyer
     */
    public Vehicle sellVehicles(Vehicle selectedVehicle, Buyer buyer) {
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
            selectedVehicle.setInStock(false); // mark vehicle as sold
            double bonus = getBonusByType(selectedVehicle);
            // give bonus to Salesperson
            addBonus(bonus);

            System.out.printf("%s %s sold %s %s %s to Buyer for $%.2f (earned $%.2f bonus)\n",
                    getJobTitle(),
                    getName(),
                    selectedVehicle.getCleanliness(),
                    selectedVehicle.getVehicleCondition(),
                    selectedVehicle.getName(),
                    selectedVehicle.getSalePrice(),
                    bonus
            );

            return selectedVehicle;
        } else {
            System.out.printf("%s %s sold %s %s %s to Buyer unsuccessfully\n",
                    getJobTitle(),
                    getName(),
                    selectedVehicle.getCleanliness(),
                    selectedVehicle.getVehicleCondition(),
                    selectedVehicle.getName()
            );
            return null;
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
    private Vehicle getNextVehicle(Inventory inventory, Vehicle.VehicleType wantedType) {
        Vehicle selectedVehicle = inventory.getMostExpensiveVehicleForSaleByType(wantedType);
        if (selectedVehicle == null) {
            selectedVehicle = inventory.getMostExpensiveVehicleForSale();
        }
        return selectedVehicle;
    }

    /**
     * method that calculates the bonus chance for selling a car
     * @param vehicle to be sold
     * @param isWantedType buyer type
     * @return calculated sale chance bonus
     */
    private double getSaleChanceBonus(Vehicle vehicle, boolean isWantedType) {
        double bonusChance = 0;
        if (vehicle.getVehicleCondition() == Vehicle.VehicleCondition.LIKE_NEW) {
            bonusChance += 0.1;
        }
        if (vehicle.getCleanliness() == Vehicle.Cleanliness.SPARKLING) {
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