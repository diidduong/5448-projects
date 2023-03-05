package command;

import staff.Salesperson;
import utilities.FNCDAdministration;
/**
 * One of the concrete commands in the command pattern
 * Command to ask for the name of the SalesPerson for the userinterface
 * @author Ahmed.H.Biby
 */
public class SalesPersonNameRequestCommand implements Command {
    private FNCDAdministration fncdAdministration;
    private String name = "Ask about Salesperson name.";

    public String getName() {
        return name;
    }


    public SalesPersonNameRequestCommand(FNCDAdministration fncdAdministration){

        this.fncdAdministration = fncdAdministration;
    }

    public void execute(){
        System.out.println(fncdAdministration.getSelectedSalesPerson().getName());
    }


}
