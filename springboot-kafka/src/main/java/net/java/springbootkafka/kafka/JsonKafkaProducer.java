package net.java.springbootkafka.kafka;

import net.java.springbootkafka.payload.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class JsonKafkaProducer {
    private static final Logger LOGGER= LoggerFactory.getLogger(JsonKafkaProducer.class);
    private KafkaTemplate<String, User> kafkaTemplate;

    // No need to use @Autowire since the bean has one parameterize constructor, springframework will automatically inject
    public JsonKafkaProducer(KafkaTemplate<String, User> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }
// message that we write to kafka topic
    public void sendMessage(User data){
        Message<User> message= MessageBuilder
                .withPayload(data)
                .setHeader(KafkaHeaders.TOPIC,"javatopic_json")
                .build();

        LOGGER.info(String.format("Message Sent -> %s",data.toString()));
        kafkaTemplate.send(message);

    }

}
