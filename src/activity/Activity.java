package activity;

import staff.Staff;
import vehicle.Vehicle;

public abstract class Activity {
    private String serviceName;
    private Staff provider;
    private Vehicle vehicle;
    private double successProbability;
    private double bonus;

    public Activity(Staff provider) {
        this.provider = provider;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public Staff getProvider() {
        return provider;
    }

    public void setProvider(Staff provider) {
        this.provider = provider;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public double getSuccessProbability() {
        return successProbability;
    }

    public void setSuccessProbability(double successProbability) {
        this.successProbability = successProbability;
    }

    public double getBonus() {
        return bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }
}
