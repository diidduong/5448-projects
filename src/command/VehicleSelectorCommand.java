package command;

import utilities.Inventory;

/**
 * One of the concrete commands in the command pattern
 * Command to show the details of a specific vehicle in the inventory through the userinterface
 * @author Ahmed.H.Biby
 */
public class VehicleSelectorCommand implements Command {
    private Inventory inventory;
    private String name = "Ask about a specific vehicle.";

    public String getName() {
        return name;
    }

    public VehicleSelectorCommand(Inventory inventory){
        this. inventory =  inventory;
    }

    public void execute() {inventory.userInterfaceVehicleSelector();}
}
