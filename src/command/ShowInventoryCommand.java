package command;

import utilities.Inventory;

/**
 * One of the concrete commands in the command pattern
 * Command to show the available vehicles in the inventory through the userinterface
 * @author Ahmed.H.Biby
 */
public class ShowInventoryCommand implements Command {
    private Inventory inventory;
    private String name = "See the available vehicles.";

    public String getName() {
        return name;
    }

    public ShowInventoryCommand(Inventory inventory){
        this. inventory =  inventory;
    }


    public void execute() {
        inventory.userInterfaceShowInventory();
    }
}
