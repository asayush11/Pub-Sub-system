package src;

public class Subscriber {
    private final String name;

    public Subscriber(String name) {
        this.name = name;
    }

    public void processMessage(Message message) {
        System.out.println("Subscriber " + name + " received message: " + message.getContent());
        if (message.getContent().contains("@")) {
            System.out.println("Subscriber " + name + " processed message: " + message.getContent());
        } else {
            DeadLetterQueue.addMessage(message);
        }
    }

    public String getName() {
        return name;
    }
}
