package src;

import java.util.*;

public class Partition {
    private final String id;
    private final List<Message> messageQueue;

    public Partition(String id) {
        this.id = id;
        messageQueue = new LinkedList<>();
     }
    public String getId() {
        return id;
    }

    public void publish(Message message) {
        messageQueue.addLast(message);
        System.out.println("Message added to partition " + id + ": " + message.getContent());
    }

    public void processMessage() {
        if (!messageQueue.isEmpty()) {
            var attempt = 3;
            while (attempt>0) {
                attempt--;
                if(messageQueue.get(0).getContent().length() < 10) {
                    Message message = messageQueue.removeFirst();
                    System.out.println("Processing message from partition " + id + ": " + message.getContent());
                    return;
                }
            }
            System.out.println("Message failed to process after 3 attempts, adding to dead letter queue: " + messageQueue.get(0).getContent());
            DeadLetterQueue.addMessage(messageQueue.removeFirst());
        } else {
            System.out.println("No messages to process in partition " + id);
        }
    }
}
