package src;

import java.util.ArrayList;
import java.util.List;

public class KakfkaBroker {
    private static KakfkaBroker instance;
    private final List<Topic> topics;
    private KakfkaBroker() {
        topics = new ArrayList<>();
    }
    public static synchronized KakfkaBroker getInstance() {
        if (instance == null) {
            instance = new KakfkaBroker();
        }
        return instance;
    }
    public void addTopic(Topic topic) {
        topics.add(topic);
        System.out.println("Topic " + topic.getName() + " added");
    }
    public void removeTopic(Topic topic) {
        topics.remove(topic);
        System.out.println("Topic " + topic.getName() + " removed");
    }
    public void publish(Message message, String topicName, String partitionId) {
        System.out.println("Publishing message to topic " + topicName);
        topics.stream()
            .filter(topic -> topic.getName().equals(topicName))
            .findFirst()
            .ifPresentOrElse(
                topic -> topic.publish(message, partitionId),
                () -> System.out.println("Topic not found")
            );
    }

    public void addPartition(String topicName, Partition partition) {
        System.out.println("Adding partition " + partition.getId() + " to topic " + topicName);
        topics.stream()
            .filter(topic -> topic.getName().equals(topicName))
            .findFirst()
            .ifPresentOrElse(
                topic -> topic.addPartition(partition),
                () -> System.out.println("Topic not found")
            );
    }
    public void removePartition(String topicName, Partition partition) {
        System.out.println("Removing partition " + partition.getId() + " from topic " + topicName);
        topics.stream()
            .filter(topic -> topic.getName().equals(topicName))
            .findFirst()
            .ifPresentOrElse(
                topic -> topic.removePartition(partition),
                () -> System.out.println("Topic not found")
            );
    }

    public void processMessage(String topicName, String partitionId) {
        System.out.println("Processing message from topic " + topicName);
        topics.stream()
            .filter(topic -> topic.getName().equals(topicName))
            .findFirst()
            .ifPresentOrElse(
                topic -> topic.processMessage(partitionId),
                () -> System.out.println("Topic not found")
            );
    }
}
