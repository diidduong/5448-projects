package staff;

import utilities.RandomGenerator;

/**
 * represents driver and is a subclass of staff
 * @author Ahmed.H.Biby
 */
public class Driver extends Staff {
    private boolean isInjured = false;



    private int racesWon = 0;

    public boolean isInjured() {
        return isInjured;
    }
    public Driver(int day){
        super(day, JobTitle.DRIVER, 150);
    }
    public void setInjured(boolean injured) {
        isInjured = injured;
        if (injured) {
            System.out.printf("\n%s (driver) got injured!\n", getName());
        }
    }

    /**
     * method to calculate the probability (30%) of injury for a given driver
     */
    public void injury(){
        boolean injury = RandomGenerator.probabilisticOutcomeGenerator(0.3);
        setInjured(injury);
    }
    public int getRacesWon() {
        return racesWon;
    }

    public void setRacesWon(int racesWon) {
        this.racesWon = racesWon;
    }
}
