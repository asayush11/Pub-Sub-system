package src;

import java.util.ArrayList;
import java.util.List;

public class Partition {
    private final String id;
    private final List<Subscriber> subscribers;

    public Partition(String id) {
        this.id = id;
        subscribers = new ArrayList<>();
     }
    public String getId() {
        return id;
    }

    public void addSubscriber(Subscriber subscriber) {
        subscribers.add(subscriber);
        System.out.println("Subscriber " + subscriber.getName() + " added to partition " + id);
    }

    public void removeSubscriber(Subscriber subscriber) {
        subscribers.remove(subscriber);
        System.out.println("Subscriber " + subscriber.getName() + " removed from partition " + id);
    }

    public void publish(Message message) {
        System.out.println("Publishing message to subscribers subscribed to " + id);
        for (Subscriber subscriber : subscribers) {
            subscriber.processMessage(message);
        }
    }
}
