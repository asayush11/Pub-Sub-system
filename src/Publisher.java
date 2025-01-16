package src;

public class Publisher {
    private final String name;
    private final KakfkaBroker broker;

    public Publisher(String name, KakfkaBroker broker) {
        this.name = name;
        this.broker = broker;
    }

    public void publish(Message message, String partitionId, String topicName) {
        System.out.println("Publisher " + name + " published message: " + message.getContent());
        broker.publish(message, topicName, partitionId);
    }
}
