package src;

import java.util.HashMap;
import java.util.Map;

public class Subscriber {
    private final String name;
    private final KakfkaBroker broker;
    private final Map<Topic, Partition> topicPartitions;
    public Subscriber(String name) {
        this.topicPartitions = new HashMap<>();
        this.name = name;
        this.broker = KakfkaBroker.getInstance();
    }

    public void processMessage() {
        System.out.println("Subscriber " + name + " processing message:");
        for (Map.Entry<Topic, Partition> entry : topicPartitions.entrySet()) {
            Topic topic = entry.getKey();
            Partition partition = entry.getValue();
            System.out.println("Processing message from topic " + topic.getName() + " and partition " + partition.getId());
            broker.processMessage(topic.getName(), partition.getId());
        }
    }

    public String getName() {
        return name;
    }

    public void addTopicPartition(Topic topic, Partition partition) {
        topicPartitions.put(topic, partition);
        System.out.println("Subscriber " + name + " added topic " + topic.getName() + " and partition " + partition.getId());
    }

    public void removeTopicPartition(Topic topic) {
        topicPartitions.remove(topic);
        System.out.println("Subscriber " + name + " removed topic " + topic.getName() );
    }
}
