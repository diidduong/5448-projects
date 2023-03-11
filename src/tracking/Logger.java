package tracking;

import java.io.File;
import java.io.FileWriter;

/**
 * @author Duy Duong, Ahmed.H.Biby
 *
 * Concrete subscriber class, Singleton pattern
 */
public class Logger implements Subscriber {
    int day;
    private static Logger instance; // lazy instantiation

    /**
     * Constructor to initialize log file
     */
    private Logger() {}

    /**
     * Singleton get instance method to return single instance
     * @return single instance of Logger
     */
    public static Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }

    /**
     * When update, write latest event output to current log file
     * @param message message object
     */
    @Override
    public void update(Message message) {
       appendOrCreateLogFile(message.message);
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    /**
     * Method to create new log file for specific day
     * @param day given day
     */
    public void createNewLogFile(int day) {
        String filePath = String.format("output\\Logger-%d.txt", day);
        try {
            FileWriter fw = new FileWriter(filePath);
            fw.close();
        } catch (Exception e) {
            System.out.println("Failed to create log file for day " + day);
        }
    }

    /**
     * Method to write string to log file, create file before writing if file doesn't exist
     * @param eventString event output string
     */
    private void appendOrCreateLogFile(String eventString) {
        String filePath = String.format("output\\Logger-%d.txt", day);
        File file = new File(filePath);

        try {
            FileWriter fw;
            if (file.exists()) {
                fw = new FileWriter(filePath, true); // create new file
            } else {
                fw = new FileWriter(filePath, false); // append string to existing file
            }
            fw.write(eventString);
            fw.close();
        } catch (Exception e) {
            System.out.println("Failed to write log file for day " + day);
            e.printStackTrace();
        }
    }
}
