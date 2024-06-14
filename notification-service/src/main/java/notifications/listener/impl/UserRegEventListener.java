package notifications.listener.impl;

import notifications.listener.KafkaEventListener;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class UserRegEventListener implements KafkaEventListener {

    @Override
    public void consume(ConsumerRecord<String, Object> event) {

    }
}
