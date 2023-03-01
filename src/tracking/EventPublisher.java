package tracking;

import java.util.ArrayList;

interface Subject {
    void addSubscriber(Subscriber o);
    void removeSubscriber(Subscriber o);
    void notifySubscribers(Message message);
}

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

    @Override
    public void notifySubscribers(Message message) {
        System.out.println(message.message);
        subscribers.forEach(s -> s.update(message));
    }
}
