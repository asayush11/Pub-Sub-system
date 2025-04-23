package src;

import java.util.ArrayList;
import java.util.List;

public class DeadLetterQueue {
    private static final List<Message> messages = new ArrayList<>();
    public static void addMessage(Message message) {
        messages.add(message);
        System.out.println("Message added to dead letter queue: " + message.getContent());
    }
    public static void processMessages() {
        System.out.println("Processing messages in dead letter queue");
        messages.forEach(message -> {
            System.out.println("Processing message: " + message.getContent());
        });
    }
}
