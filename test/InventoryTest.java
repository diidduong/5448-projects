import org.junit.jupiter.api.Test;
import utilities.Inventory;
import vehicle.Car;
import vehicle.PerformanceCar;
import vehicle.Vehicle;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InventoryTest {

    @Test
    void getMostExpensiveVehicleByType() {
        Inventory inventory = new Inventory();

        Vehicle car1 = new Car(0);
        car1.setSalePrice(12000);

        Vehicle car2 = new Car(0);
        car2.setSalePrice(15000);

        Vehicle performanceCar = new PerformanceCar(0);
        performanceCar.setSalePrice(19000);

        inventory.getWorkingInventory().add(car1);
        inventory.getWorkingInventory().add(car2);
        inventory.getWorkingInventory().add(performanceCar);

        Vehicle result = inventory.getMostExpensiveVehicleForSaleByType(Vehicle.VehicleType.CAR);

        assertEquals(car2, result);
    }

    @Test
    void getMostExpensiveVehicle() {
        Inventory inventory = new Inventory();

        Vehicle car1 = new Car(0);
        car1.setSalePrice(12000);

        Vehicle car2 = new Car(0);
        car2.setSalePrice(15000);

        Vehicle performanceCar = new PerformanceCar(0);
        performanceCar.setSalePrice(19000);

        inventory.getWorkingInventory().add(car1);
        inventory.getWorkingInventory().add(car2);
        inventory.getWorkingInventory().add(performanceCar);
        System.out.println(performanceCar);
        System.out.println(inventory.getWorkingInventory().get(2));
        Vehicle result = inventory.getMostExpensiveVehicleForSale();

        assertEquals(performanceCar, result);
    }
}