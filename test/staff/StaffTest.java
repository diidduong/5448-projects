package staff;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class StaffTest {

    @Test
    void countStaffByType() {
        ArrayList<Staff> staffs = new ArrayList<>();
        staffs.add(new Intern());
        staffs.add(new Intern());
        staffs.add(new Driver());
        staffs.add(new Mechanic());

        assertEquals(2, Staff.countStaffByType(staffs, Staff.JobTitle.INTERN));
        assertEquals(1, Staff.countStaffByType(staffs, Staff.JobTitle.DRIVER));
        assertEquals(1, Staff.countStaffByType(staffs, Staff.JobTitle.MECHANIC));
        assertEquals(0, Staff.countStaffByType(staffs, Staff.JobTitle.SALESPERSON));
    }

    @Test
    void getStaffListByType() {
        ArrayList<Staff> staffs = new ArrayList<>();
        staffs.add(new Intern());
        staffs.add(new Intern());
        staffs.add(new Driver());
        staffs.add(new Mechanic());

        ArrayList<Staff> resultStaffs = Staff.getStaffListByType(staffs, Staff.JobTitle.INTERN);
        assertEquals(2, resultStaffs.size());
    }
}