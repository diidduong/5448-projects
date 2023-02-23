package activity;

import staff.*;
import utilities.FNCDAdministration;
import utilities.Inventory;
import utilities.RandomGenerator;
import vehicle.*;
import java.util.ArrayList;

/**
 * @author Ahmed.H.Biby
 * This class includes the attributes and mehtods related to the racing event for the FNCD
 */
public class Race extends Activity{

    private int racesWon = 0;
    final private int numVehiclesRequiredForRacing = 3;
    @Override
    public void performWork() {
        ;
    }
    public int getRacesWon() {
        return racesWon;
    }
    public void setRacesWon(int racesWon) {
        this.racesWon = racesWon;
    }
    public Race(Inventory inventory, FNCDAdministration fncd){
        super(null, inventory,null,null);
        ArrayList<Vehicle> vehiclesForRacing = getVehiclesForRace(numVehiclesRequiredForRacing);
        if (vehiclesForRacing.size() == 0){
            System.out.println("FNCD is unable to participate in the race today due to the lack of running vehicles");
        }else{
            participateInRace(vehiclesForRacing,fncd);
        }
    }

    /**
     * Gets the three vehicles belonging to a rondomly selected vehicle type if they are not broken,
     * they will participate in the race
     *
     * @param numVehiclesRequired: the max. number of vehicles required to participate in a given race
     * @return Arraylist of the qualified vehicles for a randomly selected vehicle type
     */
    public ArrayList<Vehicle> getVehiclesForRace(int numVehiclesRequired){
        Vehicle.VehicleType[] availableVehicleTypesforRacing = {Vehicle.VehicleType.MONSTER_TRUCK,
                Vehicle.VehicleType.MOTORCYCLE, Vehicle.VehicleType.PERFORMANCE_CAR, Vehicle.VehicleType.PICKUP};
        int randomInt = RandomGenerator.randomIntGenerator(0,availableVehicleTypesforRacing.length-1);
        Vehicle.VehicleType randomlySelectedVehicleTypeforRacing = availableVehicleTypesforRacing[randomInt];
        ArrayList<Vehicle> availableVehiclesByTypeArrayList = getInventory().getVehiclesByType(randomlySelectedVehicleTypeforRacing);
        ArrayList<Vehicle> dummyAvailableVehiclesByTypeArrayList = new ArrayList<Vehicle>();
        for (Vehicle vehicle: availableVehiclesByTypeArrayList) {
            dummyAvailableVehiclesByTypeArrayList.add(vehicle);
        }
        for (Vehicle vehicle: dummyAvailableVehiclesByTypeArrayList) {
            if (vehicle.getVehicleCondition() == Vehicle.VehicleCondition.BROKEN){
                availableVehiclesByTypeArrayList.remove(vehicle);
            }else{;}
        }
        while (availableVehiclesByTypeArrayList.size() > numVehiclesRequired){
            availableVehiclesByTypeArrayList.remove(availableVehiclesByTypeArrayList.get(0));
        }
        return availableVehiclesByTypeArrayList;
    }

    /**
     * simulates the race results of given Vehicles and their associated drivers.
     * Update the states for drivers (winning and injury) and vehicles (winning and being Broken) depending on their rank in the races
     * @param vehiclesForRacing: available vehicles for racing
     * @param fncd: adminstration that has the driverArrayList as an attribute
     */
    public void participateInRace(ArrayList<Vehicle> vehiclesForRacing, FNCDAdministration fncd){
        ArrayList<Driver> availableDrivers= fncd.driverArrayList;
        ArrayList<Integer> disposableRanks = new ArrayList<Integer>();
        for (int i = 0; i < vehiclesForRacing.size(); i++){
            Driver driver = availableDrivers.get(i);
            Vehicle vehicle = vehiclesForRacing.get(i);
            Integer rank = (Integer) RandomGenerator.randomIntGenerator(1,20);
            while(disposableRanks.contains(rank)){
                rank = (Integer) RandomGenerator.randomIntGenerator(1,20);
            }
            System.out.printf("%s (Driver) raced with %s (%s) and achieved rank no. %s.\n", driver.getName(),
                    vehicle.getName(), vehicle.getVehicleType(), String.valueOf(rank));
            if (rank <=3){
                System.out.printf("%s (Driver) won with %s (%s)!\n",driver.getName(), vehicle.getName(),
                        vehicle.getVehicleType());
                setRacesWon(getRacesWon() + 1);
                driver.setRacesWon(driver.getRacesWon() + 1);
                driver.addBonus(1000);
                vehicle.setRacesWon(vehicle.getRacesWon() + 1);
                if (vehicle.getRacesWon()== 1){
                    vehicle.setSalePrice(vehicle.getSalePrice()* 1.1);
                }else{;}

            } else if (rank >=16){
                driver.injury();
                vehicle.setVehicleCondition(Vehicle.VehicleCondition.BROKEN);
                System.out.printf("%s (%s) went BROKEN.\n",vehicle.getName(),vehicle.getVehicleType());
            }
        }
    }
}

