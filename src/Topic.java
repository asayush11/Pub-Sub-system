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
        for (Partition partition : partitions) {
            if (partition.getId().equals(partitionId)) {
                partition.publish(message);
                return;
            }
        }
        System.out.println("Partition not found");
    }

    public void addSubscriber(String partitionId, Subscriber subscriber) {
        System.out.println("Adding subscriber " + subscriber.getName() + " to partition " + partitionId);
        for (Partition partition : partitions) {
            if (partition.getId().equals(partitionId)) {
                partition.addSubscriber(subscriber);
                return;
            }
        }
        System.out.println("Partition not found");
    }
    public void removeSubscriber(String partitionId, Subscriber subscriber) {
        System.out.println("Removing subscriber " + subscriber.getName() + " from partition " + partitionId);
        for (Partition partition : partitions) {
            if (partition.getId().equals(partitionId)) {
                partition.removeSubscriber(subscriber);
                return;
            }
        }
        System.out.println("Partition not found");
    }

}
