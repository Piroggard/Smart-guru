package notifications.listener;

import org.apache.kafka.clients.consumer.ConsumerRecord;

public interface KafkaEventListener {

    void consume(ConsumerRecord<String, Object> event);
}
