package vehicle;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MotorCycleTest {
    @Test
    void MotorCycleTest_minEngineSize() {
        MotorCycle mc = new MotorCycle();
        assertTrue(mc.getEngineSize() >= 50, "Motorcycle must has engineSize at least 50 cc");
    }
}