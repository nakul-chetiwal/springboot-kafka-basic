package com.java.stockservice;

import com.java.basedomain.dto.OrderEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class OrderConsumer {
    public static final Logger LOGGER= LoggerFactory.getLogger(OrderConsumer.class);

 // @KafkaListener(topics="${spring.kafka.topic.name}",groupId = "${spring.kafka.consumer.group-id}")
    @KafkaListener(topics = "${spring.kafka.topic.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(OrderEvent orderEvent){

      LOGGER.info(String.format("Event order received in stock service %s", orderEvent.toString()));

      // we can also save data in to DB
  }
}
