package vehicle;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VehicleFactoryTest {

    @Test
    void createCarVehicle() {
        Vehicle car = VehicleFactory.createVehicle(Vehicle.VehicleType.CAR);
        assertTrue(car instanceof Car);
    }

    @Test
    void createPerformanceCarVehicle() {
        Vehicle performanceCar = VehicleFactory.createVehicle(Vehicle.VehicleType.PERFORMANCE_CAR);
        assertTrue(performanceCar instanceof PerformanceCar);
    }

    @Test
    void createPickupVehicle() {
        Vehicle pickup = VehicleFactory.createVehicle(Vehicle.VehicleType.PICKUP);
        assertTrue(pickup instanceof Pickup);
    }
}