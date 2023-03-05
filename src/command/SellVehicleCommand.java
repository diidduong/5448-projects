package command;

import utilities.FNCDAdministration;

/**
 * One of the concrete commands in the command pattern
 * Command to sell a vehicle through the userinterface
 * @author Ahmed.H.Biby
 */
public class SellVehicleCommand implements Command {
    private FNCDAdministration fncd;

    private String name = "Select vehicle to buy.";

    public String getName() {
        return name;
    }

    public SellVehicleCommand(FNCDAdministration fncd){
        this.fncd = fncd;
    }


    public void execute() {fncd.userInterfaceVehicleSelling();
    }
}
