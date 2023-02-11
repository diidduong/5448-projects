package vehicle;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VehicleInspectorTest {

    @Test
    void calculateCostForBrokenVehicle() {
        Vehicle car = new Car();
        car.setInitialCost(10000);
        car.setVehicleCondition(Vehicle.VehicleCondition.BROKEN);

        double result = VehicleInspector.calculateCost(car);

        assertEquals(5000, result);
    }

    @Test
    void calculateCostForUsedVehicle() {
        Vehicle car = new Car();
        car.setInitialCost(10000);
        car.setVehicleCondition(Vehicle.VehicleCondition.USED);

        double result = VehicleInspector.calculateCost(car);

        assertEquals(8000, result);
    }

    @Test
    void calculateCostForNewVehicle() {
        Vehicle car = new Car();
        car.setInitialCost(10000);
        car.setVehicleCondition(Vehicle.VehicleCondition.LIKE_NEW);

        double result = VehicleInspector.calculateCost(car);

        assertEquals(10000, result);
    }

    @Test
    void calculatePrice() {
        Vehicle car = new Car();
        car.setInitialCost(5000);

        double result = VehicleInspector.calculatePrice(car);

        assertEquals(10000, result);
    }

    @Test
    void updateVehicleCondition() {
        Vehicle car = new Car();
        car.setVehicleCondition(Vehicle.VehicleCondition.USED);

        VehicleInspector.updateVehicleCondition(car, Vehicle.VehicleCondition.LIKE_NEW);

        assertEquals(Vehicle.VehicleCondition.LIKE_NEW, car.getVehicleCondition());

    }

    @Test
    void updateVehicleCleanliness() {
        Vehicle car = new Car();
        car.setCleanliness(Vehicle.Cleanliness.DIRTY);

        VehicleInspector.updateVehicleCleanliness(car, Vehicle.Cleanliness.CLEAN);

        assertEquals(Vehicle.Cleanliness.CLEAN, car.getCleanliness());
    }
}