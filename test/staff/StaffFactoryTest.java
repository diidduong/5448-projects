package staff;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class StaffFactoryTest {

    @Test
    void createInternStaff() {
        Staff intern = StaffFactory.createStaff(Staff.JobTitle.INTERN);
        assertTrue(intern instanceof Intern);
    }

    @Test
    void createSalespersonStaff() {
        Staff salesperson = StaffFactory.createStaff(Staff.JobTitle.SALESPERSON);
        assertTrue(salesperson instanceof Salesperson);
    }

    @Test
    void createMechanicStaff() {
        Staff mechanic = StaffFactory.createStaff(Staff.JobTitle.MECHANIC);
        assertTrue(mechanic instanceof Mechanic);
    }
}