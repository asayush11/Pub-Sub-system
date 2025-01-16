package src;

public class main {
    // main function
    public static void main(String[] args) {
        System.out.println("Hello, World! Welcome to the Kafka Broker System");
        KakfkaBroker broker = KakfkaBroker.getInstance();
        Topic topic = new Topic("topic1");
        Partition partition1 = new Partition("partition1");
        Partition partition2 = new Partition("partition2");
        Subscriber subscriber1 = new Subscriber("subscriber1");
        Subscriber subscriber2 = new Subscriber("subscriber2");
        Subscriber subscriber3 = new Subscriber("subscriber3");
        broker.addTopic(topic);
        broker.addPartition("topic1", partition1);
        broker.addPartition("topic1", partition2);
        broker.addSubscriber("topic1", "partition1", subscriber1);
        broker.addSubscriber("topic1", "partition1", subscriber2);
        broker.addSubscriber("topic1", "partition2", subscriber3);

        Publisher publisher = new Publisher("publisher1", broker);
        publisher.publish(new Message("message1"), "partition1", "topic1");
        publisher.publish(new Message("message@2"), "partition1", "topic1");
        broker.removeSubscriber("topic1", "partition1", subscriber1);
        publisher.publish(new Message("message@3"), "partition1", "topic1");
        publisher.publish(new Message("message@2"), "partition2", "topic1");
        broker.removeSubscriber("topic1", "partition2", subscriber3);
        broker.removeSubscriber("topic1", "partition1", subscriber2);
        broker.removePartition("topic1", partition1);
        broker.removePartition("topic1", partition2);
        broker.addSubscriber("topic1", "partition1", subscriber1);
        broker.removeTopic(topic);
        DeadLetterQueue.processMessages();
    }
}
