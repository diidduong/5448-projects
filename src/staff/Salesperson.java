package staff;

import activity.ExtendedWarrantyAddOn;
import activity.RoadRescueCoverageAddOn;
import activity.SatelliteRadioAddOn;
import activity.UnderCoatingAddOn;
import customer.Buyer;
import tracking.EventPublisher;
import tracking.Message;
import utilities.Inventory;
import utilities.RandomGenerator;
import vehicle.Vehicle;

import java.util.HashMap;

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
    public Vehicle sellVehicles(Buyer buyer, Inventory inventory, EventPublisher publisher) {
        if (buyer == null) {
            return null; // No buyer coming today
        }
        Vehicle selectedVehicle = getNextVehicle(inventory, buyer.getWantedType());
        if (selectedVehicle == null) {
            return null; // No vehicle for sale today
        }
        return sellVehicle(selectedVehicle, buyer, publisher);
    }

    /**
     * Sell selected Vehicle to Buyer with a calculated probability. If sale is successful, add sale income
     * into operating budget, give bonus to Salesperson by Vehicle Type, and move selected Vehicle to sold
     * inventory.
     *
     * @param selectedVehicle not null Vehicle
     * @param buyer not null Buyer
     */
    public Vehicle sellVehicle(Vehicle selectedVehicle, Buyer buyer, EventPublisher publisher) {
        double successfulSaleProbs = buyer.getBuyingType().getProbability();
        boolean sellable;
        String addOns = "";
        double totalAdoOnPrice = 0;

        // get bonus chance of sale
        successfulSaleProbs += getSaleChanceBonus(
                selectedVehicle,
                buyer.getWantedType() == selectedVehicle.getVehicleType()
        );

        // check if successful sale by probability
        sellable = RandomGenerator.probabilisticOutcomeGenerator(successfulSaleProbs);
        if (sellable) {
            HashMap<String, Double> randomAddOn = randomAddOn(selectedVehicle);
            for (String name : randomAddOn.keySet()) {
                addOns = name;
                totalAdoOnPrice = randomAddOn.get(name);
            }

            selectedVehicle.setInStock(false); // mark vehicle as sold
            double bonus = getBonusByType(selectedVehicle);
            // give bonus to Salesperson
            addBonus(bonus);

            String msg = "";
            if (totalAdoOnPrice != 0) {
                msg = String.format("%s %s (earned $%.2f bonus) sold %s %s (%s) %s to Buyer for $%.2f + $%.2f (%s (addOns)).\n",
                        getJobTitle(),
                        getName(),
                        bonus,
                        selectedVehicle.getCleanliness(),
                        selectedVehicle.getVehicleCondition(),
                        selectedVehicle.getVehicleType(),
                        selectedVehicle.getName(),
                        selectedVehicle.getSalePrice(),
                        totalAdoOnPrice,
                        addOns

                );
                // Update sale price to include addons
                selectedVehicle.setSalePrice(selectedVehicle.getSalePrice() + totalAdoOnPrice);
            } else {
                msg = String.format("%s %s sold %s %s %s to Buyer for $%.2f without any addOns (earned $%.2f bonus)\n",
                        getJobTitle(),
                        getName(),
                        selectedVehicle.getCleanliness(),
                        selectedVehicle.getVehicleCondition(),
                        selectedVehicle.getName(),
                        selectedVehicle.getSalePrice(),
                        bonus
                );
            }
            publisher.notifySubscribers(new Message(msg, bonus, 0));
            return selectedVehicle;
        } else {
            String msg = String.format("%s %s sold %s %s %s to Buyer unsuccessfully\n",
                    getJobTitle(),
                    getName(),
                    selectedVehicle.getCleanliness(),
                    selectedVehicle.getVehicleCondition(),
                    selectedVehicle.getName()
            );
            publisher.notifySubscribers(new Message(msg, 0, 0));
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
     * randomly select a combination of addOns, if any, and calculates their price
     * @param vehicle to be sold
     * @return addOnSummary: hashmap with addOns as a key and Total addOnPrice as value
     */

    public HashMap <String, Double> randomAddOn(Vehicle vehicle){
        boolean randomExtendedWarrantyAddOnPurchase = RandomGenerator.probabilisticOutcomeGenerator(0.25);
        boolean randomUndercoatingAddOnPurchase = RandomGenerator.probabilisticOutcomeGenerator(0.10);
        boolean randomRoadRescueCoverageAddOnPurchase = RandomGenerator.probabilisticOutcomeGenerator(0.5);
        boolean randomSatelliteRadioAddOnPurchase = RandomGenerator.probabilisticOutcomeGenerator(0.40);
        HashMap <String, Double> addOnSummary  = new HashMap<>();
        String addOnString = "";
        double addOnTotalPrice = 0;

        if (randomExtendedWarrantyAddOnPurchase){
            ExtendedWarrantyAddOn extendedWarrantyAddOn = new ExtendedWarrantyAddOn(vehicle);
            addOnTotalPrice = addOnTotalPrice + extendedWarrantyAddOn.getAddOnPrice();
            addOnString = addOnString.concat("ExtendedWarranty");

        }
        if (randomUndercoatingAddOnPurchase){
            UnderCoatingAddOn underCoatingAddOn= new UnderCoatingAddOn(vehicle);
            addOnTotalPrice = addOnTotalPrice + underCoatingAddOn.getAddOnPrice();
            if (addOnString.isEmpty()) {
                addOnString = addOnString.concat("Undercoating");
            }
            else{
                addOnString = addOnString.concat(", Undercoating");
            }
        }
        if (randomRoadRescueCoverageAddOnPurchase){
            RoadRescueCoverageAddOn roadRescueCoverageAddOn= new RoadRescueCoverageAddOn(vehicle);
            addOnTotalPrice = addOnTotalPrice + roadRescueCoverageAddOn.getAddOnPrice();
            if (addOnString.isEmpty()) {
                addOnString = addOnString.concat("RoadRescueCoverage");
            }
            else{
                addOnString = addOnString.concat(", RoadRescueCoverage");
            }
        }
        if (randomSatelliteRadioAddOnPurchase){
            SatelliteRadioAddOn satelliteRadioAddOn = new SatelliteRadioAddOn(vehicle);
            addOnTotalPrice = addOnTotalPrice + satelliteRadioAddOn.getAddOnPrice();
            if (addOnString.isEmpty()) {
                addOnString = addOnString.concat("SatelliteRadio");
            }
            else{
                addOnString = addOnString.concat(", SatelliteRadio");
            }
        }

        addOnSummary.put(addOnString, addOnTotalPrice);
        return addOnSummary;
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
            case ELECTRIC_CAR:
                return 300;
            case MOTORCYCLE:
                return 150;
            case MONSTER_TRUCK:
                return 500;
            default:
                return 0;
        }
    }
}