package utilities;

import command.*;

import java.util.HashMap;
import java.util.Scanner;

/**
 * The class responsible for handling the Human-controlled interaction with the FNCD simulation
 * Also represents the client for the command pattern
 * @author Ahmed.H.Biby
 */
public class UserInterface {
    private FNCDAdministration selectedFNCD;

    private Simulator simulator;

    HashMap<Integer, Command> availableCommands = new HashMap<Integer, Command>();

    public Simulator getSimulator() {
        return simulator;
    }

    public void setSimulator(Simulator simulator) {
        this.simulator = simulator;
    }

    public HashMap<Integer, Command> getAvailableCommands() {
        return availableCommands;
    }

    public void setAvailableCommands(HashMap<Integer, Command> availableCommands) {
        this.availableCommands = availableCommands;
    }

    public FNCDAdministration getSelectedFNCD() {
        return selectedFNCD;
    }

    public void setSelectedFNCD(FNCDAdministration selectedFNCD) {
        this.selectedFNCD = selectedFNCD;
    }

    public UserInterface(Simulator simulator) {
        System.out.println("##############################");
        System.out.println("User interface session started");
        System.out.println("##############################");
        setSimulator(simulator);
        FNCDLocationSelectorCommand fncdLocationSelectorCommand = new FNCDLocationSelectorCommand(simulator);
        fncdLocationSelectorCommand.execute();
        setSelectedFNCD(simulator.getSelectedFNCD());
        SalesPersonSelectorCommand salesPersonSelectorCommand = new SalesPersonSelectorCommand(getSelectedFNCD());
        salesPersonSelectorCommand.execute();
    }

    /**
     * starts and operates the userinterface
     */
    public void start() {
        SalesPersonNameRequestCommand salesPersonNameRequestCommand = new SalesPersonNameRequestCommand(getSelectedFNCD());
        availableCommands.put(1, salesPersonNameRequestCommand);
        SellVehicleCommand sellVehicleCommand = new SellVehicleCommand(getSelectedFNCD());
        availableCommands.put(2, sellVehicleCommand);
        ShowInventoryCommand showInventoryCommand = new ShowInventoryCommand(getSelectedFNCD().getInventory());
        availableCommands.put(3, showInventoryCommand);
        TimeRequestCommand timeRequestCommand = new TimeRequestCommand();
        availableCommands.put(4, timeRequestCommand);
        VehicleSelectorCommand vehicleSelectorCommand = new VehicleSelectorCommand(getSelectedFNCD().getInventory());
        availableCommands.put(5, vehicleSelectorCommand);
        SalesPersonSelectorCommand salesPersonSelectorCommand = new SalesPersonSelectorCommand(getSelectedFNCD());
        availableCommands.put(6, salesPersonSelectorCommand);
        FNCDLocationSelectorCommand fncdLocationSelectorCommand = new FNCDLocationSelectorCommand(getSimulator());
        availableCommands.put(7, fncdLocationSelectorCommand);
        CommandInvoker commandInvoker = new CommandInvoker();
        Scanner scanner = new Scanner(System.in);
        int userEntry = 1000; // initialization to enter the while loop
        while (userEntry != 0) {
            System.out.println("\nEnter the number corresponding to the command you want");
            for (Integer i : availableCommands.keySet()) {
                System.out.printf("%d) %s\n", i, availableCommands.get(i).getName());
            }
            System.out.println("Enter 0 to exit");
            userEntry = scanner.nextInt();
            if (userEntry == 0) {
                break;
            }
            if (availableCommands.get(userEntry) == fncdLocationSelectorCommand) {
                commandInvoker.setCommand(availableCommands.get(userEntry));
                commandInvoker.execute();
                setSelectedFNCD(simulator.getSelectedFNCD());

                // all the commands need to reconfigured if the location is changed
                salesPersonNameRequestCommand = new SalesPersonNameRequestCommand(getSelectedFNCD());
                availableCommands.put(1, salesPersonNameRequestCommand);
                sellVehicleCommand = new SellVehicleCommand(getSelectedFNCD());
                availableCommands.put(2, sellVehicleCommand);
                showInventoryCommand = new ShowInventoryCommand(getSelectedFNCD().getInventory());
                availableCommands.put(3, showInventoryCommand);
                timeRequestCommand = new TimeRequestCommand();
                availableCommands.put(4, timeRequestCommand);
                vehicleSelectorCommand = new VehicleSelectorCommand(getSelectedFNCD().getInventory());
                availableCommands.put(5, vehicleSelectorCommand);
                salesPersonSelectorCommand = new SalesPersonSelectorCommand(getSelectedFNCD());
                availableCommands.put(6, salesPersonSelectorCommand);
                fncdLocationSelectorCommand = new FNCDLocationSelectorCommand(getSimulator());
                availableCommands.put(7, fncdLocationSelectorCommand);


                commandInvoker.setCommand(availableCommands.get(6));
                commandInvoker.execute();

            } else {
                commandInvoker.setCommand(availableCommands.get(userEntry));
                commandInvoker.execute();
            }



        }
        System.out.println("##############################");
        System.out.println("User interface session ended");
        System.out.println("##############################");
        System.out.println();
        for (FNCDAdministration fncd : getSimulator().getFncdAdministrationArrayList()) {
            System.out.println("\n######################################################################");
            System.out.printf("************ Ending of (%s) FNCD simulation for %s days ************\n",
                    fncd.getName(), fncd.getDay());
            System.out.println("#######################################################################");
            fncd.ending();
            fncd.dailyReport(); // tabular report at the end of the day
            fncd.endSimulation();
        }
    }
}



