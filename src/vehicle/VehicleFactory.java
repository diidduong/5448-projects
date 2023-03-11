package vehicle;

/**
 * @author Duy Duong, Ahmed.H.Biby
 *
 * Factory class to create vehicle products
 */
public class VehicleFactory {
    /**
     * Factory method to create vehicle instance by type
     * Reference: Project 2 example solution
     *
     * @param type vehicle type
     * @return vehicle instance
     */
    public static Vehicle createVehicle(Vehicle.VehicleType type) {
        switch (type) {
            case PERFORMANCE_CAR:
                return new PerformanceCar();
            case CAR:
                return new Car();
            case PICKUP:
                return new Pickup();
            case ELECTRIC_CAR:
                return new ElectricCar();
            case MOTORCYCLE:
                return new MotorCycle();
            case MONSTER_TRUCK:
                return new MonsterTruck();
            case PARAMOTOR:
                return new ParaMotor();
            case DOZER:
                return new Dozer();
            case GOLF_CART:
                return new GolfCart();
            default:
                throw new IllegalArgumentException("Unknown vehicle type [" + type + "]");
        }
    }
}
