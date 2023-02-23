package vehicle;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.*;

class ElectricCarTest {

    @Test
    void electricCar_logicTest() {
        ElectricCar EV = new ElectricCar(10);
    }

    @Test
    void electricCar_rangeTest() {
        ElectricCar EV = new ElectricCar(10);
        Assertions.assertTrue(EV.getRange() >= 60, "Electric car has range lower than 60 miles");
        Assertions.assertTrue(EV.getRange() < 400, "Electric car has range higher than 400 miles");
    }

    @Test
    void extendRangeIfLikeNew_logicTest() {
        ElectricCar EV = new ElectricCar(10);
        System.out.println(EV.getRange());
        System.out.println(EV.getVehicleCondition());
        EV.extendRangeIfLikeNew();
        System.out.println(EV.getRange());
    }
}