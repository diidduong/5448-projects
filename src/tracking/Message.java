package tracking;

public class Message {
    String message;
    double moneyEarnedByStaff;
    double moneyEarnedByFNCD;

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
