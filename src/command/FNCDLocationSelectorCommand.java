package command;

import utilities.FNCDAdministration;
import utilities.Simulator;
/**
 * One of the concrete commands in the command pattern
 * Command to select the FNCD branch for the userinterface
 * @author Ahmed.H.Biby
 */
public class FNCDLocationSelectorCommand implements Command {
    private Simulator simulator;

    private String name = "Select FNCD branch.";

    public String getName() {
        return name;
    }

    public FNCDLocationSelectorCommand(Simulator simulator){
        this.simulator= simulator;
    }


    public void execute(){
        simulator.userInterfaceFNCDSelector();
    }


}
