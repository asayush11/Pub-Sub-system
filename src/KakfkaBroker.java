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
        for (Topic topic : topics) {
            if (topic.getName().equals(topicName)) {
                topic.publish(message, partitionId);
                return;
            }
        }
        System.out.println("Topic not found");
    }
    public void addSubscriber(String topicName, String partitionId, Subscriber subscriber) {
        System.out.println("Adding subscriber " + subscriber.getName() + " to topic " + topicName);
        for (Topic topic : topics) {
            if (topic.getName().equals(topicName)) {
                topic.addSubscriber(partitionId, subscriber);
                return;
            }
        }
        System.out.println("Topic not found");
    }
    public void removeSubscriber(String topicName, String partitionId, Subscriber subscriber) {
        System.out.println("Removing subscriber " + subscriber.getName() + " from topic " + topicName);
        for (Topic topic : topics) {
            if (topic.getName().equals(topicName)) {
                topic.removeSubscriber(partitionId, subscriber);
                return;
            }
        }
        System.out.println("Topic not found");
    }
    public void addPartition(String topicName, Partition partition) {
        System.out.println("Adding partition " + partition.getId() + " to topic " + topicName);
        for (Topic topic : topics) {
            if (topic.getName().equals(topicName)) {
                topic.addPartition(partition);
                return;
            }
        }
        System.out.println("Topic not found");
    }
    public void removePartition(String topicName, Partition partition) {
        System.out.println("Removing partition " + partition.getId() + " from topic " + topicName);
        for (Topic topic : topics) {
            if (topic.getName().equals(topicName)) {
                topic.removePartition(partition);
                return;
            }
        }
        System.out.println("Topic not found");
    }
}
