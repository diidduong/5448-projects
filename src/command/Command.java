package command;

import staff.Salesperson;
import utilities.*;

/**
 * The command interface of the command pattern
 * @author Ahmed.H.Biby
 */
public interface Command {
    public void execute();
    public String getName();
    }