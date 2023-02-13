import org.junit.jupiter.api.Test;
import vehicle.Car;
import vehicle.PerformanceCar;
import vehicle.Vehicle;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class InventoryTest {

    @Test
    void getMostExpensiveVehicleByType() {
        Inventory inventory = new Inventory();

        Vehicle car1 = new Car();
        car1.setSalePrice(12000);

        Vehicle car2 = new Car();
        car2.setSalePrice(15000);

        Vehicle performanceCar = new PerformanceCar();
        performanceCar.setSalePrice(19000);

        inventory.getVehicleList().add(car1);
        inventory.getVehicleList().add(car2);
        inventory.getVehicleList().add(performanceCar);

        Vehicle result = inventory.getMostExpensiveVehicleByType("Car");

        assertEquals(car2, result);

    }

    @Test
    void getMostExpensiveVehicle() {
        Inventory inventory = new Inventory();

        Vehicle car1 = new Car();
        car1.setSalePrice(12000);

        Vehicle car2 = new Car();
        car2.setSalePrice(15000);

        Vehicle performanceCar = new PerformanceCar();
        performanceCar.setSalePrice(19000);

        inventory.getVehicleList().add(car1);
        inventory.getVehicleList().add(car2);
        inventory.getVehicleList().add(performanceCar);

        Vehicle result = inventory.getMostExpensiveVehicle();

        assertEquals(performanceCar, result);
    }
}