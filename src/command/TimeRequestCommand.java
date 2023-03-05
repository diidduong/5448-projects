package command;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This code is inspired from:
 * https://www.javatpoint.com/java-get-current-date
 *
 *  One of the concrete commands in the command pattern
 *  Command to dispaly the time and date through the userinterface
 *  @author Ahmed.H.Biby
 */
public class TimeRequestCommand implements Command {
    private SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss  MM/dd/yyyy");
    private Date date;

    private String name = "Ask about the time.";

    public String getName() {
        return name;
    }
    public TimeRequestCommand(){
        this.date = new Date();
    }
    public void execute(){
        System.out.printf("The current time is %s\n",formatter.format(date));
    }
}
