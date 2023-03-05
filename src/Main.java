import utilities.FNCDAdministration;
import utilities.Simulator;
import utilities.UserInterface;

/**
 * @author Duy Duong, Ahmed.H.Biby
 * Main program to run the FNCD simulation
 */
public class Main {

    public static void main(String[] args) {

        FNCDAdministration fncdNorth =new FNCDAdministration("North");
        FNCDAdministration fncdSouth =new FNCDAdministration("South");
        Simulator simulator = new Simulator(30); //endDay for automated operation
        simulator.addFNCD(fncdNorth);
        simulator.addFNCD(fncdSouth);
        simulator.simulate();
        UserInterface userInterface = new UserInterface(simulator);
        userInterface.start();

    }
}
