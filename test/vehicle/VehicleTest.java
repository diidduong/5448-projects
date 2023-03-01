package vehicle;

import org.junit.jupiter.api.Test;

import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class VehicleTest {

    @Test
    void countVehicleByType() {
        ArrayList<Vehicle> vehicles = new ArrayList<>();
        vehicles.add(new Pickup());
        vehicles.add(new Car());
        vehicles.add(new Car());
        vehicles.add(new MotorCycle());

        assertEquals(1, Vehicle.countVehicleByType(vehicles, Vehicle.VehicleType.PICKUP));
        assertEquals(2, Vehicle.countVehicleByType(vehicles, Vehicle.VehicleType.CAR));
        assertEquals(1, Vehicle.countVehicleByType(vehicles, Vehicle.VehicleType.MOTORCYCLE));
        assertEquals(0, Vehicle.countVehicleByType(vehicles, Vehicle.VehicleType.PERFORMANCE_CAR));
    }

    @Test
    void getVehicleListByType() {
        ArrayList<Vehicle> vehicles = new ArrayList<>();
        vehicles.add(new Pickup());
        vehicles.add(new Car());
        vehicles.add(new Car());

        ArrayList<Vehicle> resultVehicles = Vehicle.getVehicleListByType(vehicles, Vehicle.VehicleType.CAR);
        assertEquals(2, resultVehicles.size());
    }

    @Test
    void upgradeVehicleCondition() {
        Vehicle vehicle = new PerformanceCar();
        vehicle.setVehicleCondition(Vehicle.VehicleCondition.BROKEN);
        vehicle.upgradeVehicleCondition();
        assertSame(vehicle.getVehicleCondition(), Vehicle.VehicleCondition.USED);
    }

    @Test
    void downgradeCleanliness() {
        Vehicle vehicle = new PerformanceCar();
        vehicle.setCleanliness(Vehicle.Cleanliness.SPARKLING);
        vehicle.downgradeCleanliness();
        assertSame(vehicle.getCleanliness(), Vehicle.Cleanliness.CLEAN);
    }

    @Test
    void calculateCost() {
        double initialCost = 500;

        // Check condition USED
        double discountedCost = Vehicle.calculateCost(Vehicle.VehicleCondition.USED, initialCost);
        assertEquals(400, discountedCost);

        // Check condition BROKEN
        discountedCost = Vehicle.calculateCost(Vehicle.VehicleCondition.BROKEN, initialCost);
        assertEquals(250, discountedCost);

        // Check condition NEW
        discountedCost = Vehicle.calculateCost(Vehicle.VehicleCondition.LIKE_NEW, initialCost);
        assertEquals(500, discountedCost);
    }

    @Test
    void calculatePrice() {
        double initialCost = 200;
        assertEquals(400, Vehicle.calculatePrice(initialCost));
    }
}