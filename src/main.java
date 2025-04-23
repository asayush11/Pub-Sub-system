package src;

public class main {
    // main function
    public static void main(String[] args) {
        System.out.println("Hello, World! Welcome to the Kafka Broker System");
        KakfkaBroker broker = KakfkaBroker.getInstance();

        Topic topic1 = new Topic("topic1");
        Topic topic2 = new Topic("topic2");

        Partition partition1 = new Partition("partition1");
        Partition partition2 = new Partition("partition2");
        Partition partition3 = new Partition("partition3");

        Subscriber subscriber1 = new Subscriber("subscriber1");
        Subscriber subscriber2 = new Subscriber("subscriber2");
        Subscriber subscriber3 = new Subscriber("subscriber3");

        broker.addTopic(topic1);
        broker.addTopic(topic2);

        broker.addPartition("topic1", partition1);
        broker.addPartition("topic1", partition2);
        broker.addPartition("topic2", partition3);

        subscriber1.addTopicPartition(topic1, partition1);
        subscriber2.addTopicPartition(topic1, partition2);
        subscriber1.addTopicPartition(topic2, partition3);


        Publisher publisher = new Publisher("publisher1");
        publisher.publish(new Message("message1"), "partition1", "topic1");
        publisher.publish(new Message("message@2556667"), "partition3", "topic2");
        publisher.publish(new Message("message@3"), "partition1", "topic1");
        publisher.publish(new Message("message@2"), "partition2", "topic1");

        subscriber1.processMessage();
        subscriber2.processMessage();
        subscriber3.processMessage();

        broker.removePartition("topic1", partition1);
        broker.removePartition("topic1", partition2);
        subscriber1.processMessage();
        DeadLetterQueue.processMessages();
    }
}
