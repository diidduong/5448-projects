package command;

import utilities.FNCDAdministration;

/**
 * One of the concrete commands in the command pattern
 * Command to select the SalesPerson for the userinterface
 * @author Ahmed.H.Biby
 */
public class SalesPersonSelectorCommand implements Command {
    private FNCDAdministration fncd;
    private String name = "Change Salesperson.";

    public String getName() {
        return name;
    }

    public SalesPersonSelectorCommand(FNCDAdministration fncd){
        this.fncd = fncd;
    }


    public void execute() {
        fncd.userInterfaceSalesPersonSelector();
    }
}
