package vehicle;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MotorCycleTest {

    @Test
    void MotorCycleTest_logicTest() {
        MotorCycle mc = new MotorCycle(10);
    }
    @Test
    void MotorCycleTest_minEngineSize() {
        MotorCycle mc = new MotorCycle(10);
        assert mc.getEngineSize() >= 50: "Motorcycle has enginSize lower than 50 cc";
    }
}