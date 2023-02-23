package staff;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DriverTest {

    @Test
    void setInjured_logicTest() {
        Driver driver = new Driver(19);
        System.out.println(driver.isInjured());
        driver.setInjured(true);
        System.out.println(driver.isInjured());
    }

    @Test
    void isInjured_logicTest() {
        Driver driver = new Driver(19);
        driver.injury();
        System.out.println(driver.isInjured());
    }
}