package src;

import java.util.ArrayList;
import java.util.List;

public class Topic {
    private final String name;
    private final List<Partition> partitions;
    public Topic(String name) {
        this.name = name;
        partitions = new ArrayList<>();
    }
    public String getName() {
        return name;
    }
    public void addPartition(Partition partition) {
        partitions.add(partition);
        System.out.println("Partition " + partition.getId() + " added to topic " + name);
    }
    public void removePartition(Partition partition) {
        partitions.remove(partition);
        System.out.println("Partition " + partition.getId() + " removed from topic " + name);
    }
    public void publish(Message message, String partitionId) {
        System.out.println("Publishing message to partition " + partitionId);
        partitions.stream()
            .filter(partition -> partition.getId().equals(partitionId))
            .findFirst()
            .ifPresentOrElse(
                partition -> partition.publish(message),
                () -> System.out.println("Partition not found")
            );
    }

    public void processMessage(String partitionId) {
        System.out.println("Processing message from partition " + partitionId);
        partitions.stream()
            .filter(partition -> partition.getId().equals(partitionId))
            .findFirst()
            .ifPresentOrElse(
                Partition::processMessage,
                () -> System.out.println("Partition not found")
            );
    }

}
