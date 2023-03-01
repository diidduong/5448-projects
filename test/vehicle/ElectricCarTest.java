package vehicle;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ElectricCarTest {
    @Test
    void electricCar_rangeTest() {
        ElectricCar ev = new ElectricCar();

        assertTrue(ev.getRange() >= 60, "Electric car must has range at least 60 miles");
        assertTrue(ev.getRange() <= 400, "Electric car must has range at most 400 miles");
    }

    @Test
    void extendRangeIfLikeNew_logicTest() {
        ElectricCar ev = new ElectricCar();
        int range = ev.getRange();

        ev.setVehicleCondition(Vehicle.VehicleCondition.LIKE_NEW);
        ev.extendRangeIfLikeNew();

        assertEquals(range + 100, ev.getRange());
    }
}