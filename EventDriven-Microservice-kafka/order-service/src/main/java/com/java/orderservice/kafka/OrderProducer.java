package com.java.orderservice.kafka;

import com.java.basedomain.dto.OrderEvent;
import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class OrderProducer {

    public static final Logger LOGGER= LoggerFactory.getLogger(OrderProducer.class);

    private NewTopic topic;
    private KafkaTemplate<String, OrderEvent> orderEventKafkaTemplate;

    public OrderProducer(NewTopic topic, KafkaTemplate<String, OrderEvent> orderEventKafkaTemplate) {
        this.topic = topic;
        this.orderEventKafkaTemplate = orderEventKafkaTemplate;
    }

    public void sendMessage(OrderEvent orderEvent){
        LOGGER.info(String.format("producer: Order Event Message sent ---> %s", orderEvent.toString()));
        // since we are sending the object we send it as Message therefore create message
        // create message of orderevent
        Message<OrderEvent> message= MessageBuilder
                .withPayload(orderEvent)
                .setHeader(KafkaHeaders.TOPIC, topic.name())
                .build();
        orderEventKafkaTemplate.send(message);
    }
}
