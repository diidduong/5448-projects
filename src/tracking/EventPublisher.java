package tracking;

import java.util.ArrayList;

/**
 * @author Duy Duong, Ahmed.H.Biby
 * Interface subject class for publisher implementation
 */
interface Subject {
    void addSubscriber(Subscriber o);
    void removeSubscriber(Subscriber o);
    void notifySubscribers(Message message);
}

/**
 * @author Duy Duong, Ahmed.H.Biby
 * Implementation of publisher
 */
public class EventPublisher implements Subject{
    ArrayList<Subscriber> subscribers = new ArrayList<>();

    @Override
    public void addSubscriber(Subscriber s) {
        subscribers.add(s);
    }

    @Override
    public void removeSubscriber(Subscriber s) {
        subscribers.remove(s);
    }

    /**
     * Method to notify all subscribers about new event output
     * @param message message object
     */
    @Override
    public void notifySubscribers(Message message) {
        System.out.println(message.message);
        subscribers.forEach(s -> s.update(message));
    }
}
