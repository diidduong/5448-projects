package tracking;

/**
 * @author Duy Duong, Ahmed.H.Biby
 * A communicating object between pub/sub
 */
public class Message {
    String message;
    double moneyEarnedByStaff;
    double moneyEarnedByFNCD;

    /**
     * Constructor to create message for communication
     * @param message event output string
     * @param moneyEarnedByStaff earning by staff
     * @param moneyEarnedByFNCD earning by FNCD
     */
    public Message(String message, double moneyEarnedByStaff, double moneyEarnedByFNCD) {
        this.message = message;
        this.moneyEarnedByStaff = moneyEarnedByStaff;
        this.moneyEarnedByFNCD = moneyEarnedByFNCD;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public double getMoneyEarnedByStaff() {
        return moneyEarnedByStaff;
    }

    public void setMoneyEarnedByStaff(double moneyEarnedByStaff) {
        this.moneyEarnedByStaff = moneyEarnedByStaff;
    }

    public double getMoneyEarnedByFNCD() {
        return moneyEarnedByFNCD;
    }

    public void setMoneyEarnedByFNCD(double moneyEarnedByFNCD) {
        this.moneyEarnedByFNCD = moneyEarnedByFNCD;
    }
}
