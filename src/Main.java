import utilities.FNCDAdministration;
import utilities.*;
import staff.*;
import vehicle.*;

public class Main {

    public static void main(String[] args) {

        // start simulation
        FNCDAdministration.start();
        // daily operation
        FNCDAdministration.operate();

/* The detected bug example
       Car car = new Car(2);
        Budget.purchaseCar(2,car);

 */




    }
}
