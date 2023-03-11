package vehicle;

import utilities.RandomGenerator;

/**
 * represents the paraMotor and is a subclass of vehicle
 * @author Ahmed.H.Biby
 */
public class ParaMotor extends Vehicle{
    public enum ParaMotorLaunchType {
        TRIKE_WHEEL_LAUNCHER,QUAD_WHEEL_LAUNCHER, FOOT_LAUNCHER
    }
    private int maximumAltitude;
    private int maximumCruisingAirSpeed;

    public ParaMotorLaunchType getLaunchType() {
        return launchType;
    }

    public void setLaunchType(ParaMotorLaunchType launchType) {
        this.launchType = launchType;
    }

    private ParaMotorLaunchType launchType;
    public ParaMotor() {
        super(VehicleType.PARAMOTOR, 5000, 12000);
        maximumAltitude = RandomGenerator.randomIntGenerator(5000, 15000);
        maximumCruisingAirSpeed = RandomGenerator.randomIntGenerator(20, 50);
        int randomIntSelector = RandomGenerator.randomIntGenerator(0,100);
        double launchTypePriceFactor;
        if (randomIntSelector <=33){
            setLaunchType(ParaMotorLaunchType.TRIKE_WHEEL_LAUNCHER);
            launchTypePriceFactor = 0.3;
        } else if (randomIntSelector <=66) {
            setLaunchType(ParaMotorLaunchType.FOOT_LAUNCHER);
            launchTypePriceFactor = 0.0;
        } else {
            setLaunchType(ParaMotorLaunchType.QUAD_WHEEL_LAUNCHER);
            launchTypePriceFactor = 0.5;
        }
        // the sale price is affected by the maximumAltitude (feet), maximumCruisingAirSpeed (miles/hour), and launchType
        setSalePrice(getSalePrice()*(1+ maximumAltitude/50000 + maximumCruisingAirSpeed/750) + launchTypePriceFactor);
        System.out.printf("\nA %s and %s ParaMotor %s is available in the inventory.\n", getCleanliness(),
                getVehicleCondition(), getName());
    }
    /**
     * ParaMotor name will include maximumAltitude (feet) and maximumCruisingAirSpeed (miles/hour)
     * @return name with maximumAltitude (feet) and maximumCruisingAirSpeed (miles/hour)
     */
    @Override
    public String getName() {

        return String.format("%s (%d  feet maximumAltitude & %d mile/hour maximumCruisingAirSpeed) with %s ", super.getName(),
                maximumAltitude, maximumCruisingAirSpeed, getLaunchType());
    }
}
