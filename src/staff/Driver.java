package staff;

import tracking.EventPublisher;
import tracking.Message;
import utilities.RandomGenerator;

/**
 * represents driver and is a subclass of staff
 * @author Duy Duong, Ahmed.H.Biby
 */
public class Driver extends Staff {
    private boolean isInjured;
    private int racesWon;

    public Driver() {
        super(JobTitle.DRIVER, 150);
    }

    public boolean isInjured() {
        return isInjured;
    }

    public void setInjured(boolean injured) {
        isInjured = injured;
    }

    public int getRacesWon() {
        return racesWon;
    }

    public void setRacesWon(int racesWon) {
        this.racesWon = racesWon;
    }

    /**
     * method to calculate the probability (30%) of injury for a given driver
     */
    public void injury(EventPublisher publisher){
        isInjured = RandomGenerator.probabilisticOutcomeGenerator(0.3);
        if (isInjured) {
            String msg = String.format("%s %s got injured!\n", getJobTitle(), getName());
            publisher.notifySubscribers(new Message(msg, 0, 0));
        }
    }
}
