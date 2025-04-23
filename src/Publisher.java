package src;

public class Publisher {
    private final String name;
    private final KakfkaBroker broker;

    public Publisher(String name) {
        this.name = name;
        this.broker = KakfkaBroker.getInstance();
    }

    public void publish(Message message, String partitionId, String topicName) {
        System.out.println("Publisher " + name + " published message: " + message.getContent());
        broker.publish(message, topicName, partitionId);
    }
}
