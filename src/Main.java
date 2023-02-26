import tracking.EventPublisher;
import tracking.Logger;
import tracking.Subscriber;
import tracking.Tracker;

/**
 * @author Duy Duong, Ahmed.H.Biby
 * Main program to run the FNCD simulation
 */
public class Main {

    public static void main(String[] args) {
//        FNCDAdministration fncd = new FNCDAdministration();
//        fncd.start(); // run simulation

        EventPublisher publisher = new EventPublisher();
        Logger logger = new Logger();
        Tracker tracker = new Tracker();

        publisher.addSubscriber(logger);
        publisher.addSubscriber(tracker);
        publisher.notifySubscribers();
    }
}
