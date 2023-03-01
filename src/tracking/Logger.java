package tracking;

import java.io.File;
import java.io.FileWriter;

public class Logger implements Subscriber {
    int day;

    public Logger(int day) {
        this.day = day;
        String filePath = String.format("output\\Logger-%d.txt", day);
        try {
            FileWriter fw = new FileWriter(filePath);
            fw.close();
        } catch (Exception e) {
            System.out.println("Failed to create log file for day " + day);
        }
    }

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
     *
     * @param eventString
     */
    private void appendOrCreateLogFile(String eventString) {
        String filePath = String.format("output\\Logger-%d.txt", day);
        File file = new File(filePath);

        try {
            FileWriter fw;
            if (file.exists()) {
                fw = new FileWriter(filePath, true);
            } else {
                fw = new FileWriter(filePath, false);
            }
            fw.write(eventString);
            fw.close();
        } catch (Exception e) {
            System.out.println("Failed to write log file for day " + day);
            e.printStackTrace();
        }
    }
}
