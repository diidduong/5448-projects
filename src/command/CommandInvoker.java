package command;

/**
 * The command invoker of the command pattern
 * @author Ahmed.H.Biby
 */
public class CommandInvoker {
    Command command;
    public void setCommand(Command command){
        this.command = command;
    }
    public void execute(){
        command.execute();
    }

    }
