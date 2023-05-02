package com.java.orderservice.controller;

import com.java.basedomain.dto.Order;
import com.java.basedomain.dto.OrderEvent;
import com.java.orderservice.kafka.OrderProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/")
public class OrderController {

    public static final Logger LOGGER= LoggerFactory.getLogger(OrderController.class);

    public  OrderProducer orderProducer;

    public OrderController(OrderProducer orderProducer) {
        this.orderProducer = orderProducer;
    }

    @PostMapping("/orders")
    public String placeOrder(@RequestBody Order order){
        order.setOrderId(UUID.randomUUID().toString());
        OrderEvent orderEvent=new OrderEvent();
        orderEvent.setMessage("pending");
        orderEvent.setStatus("Order status is in pending state");
        orderEvent.setOrder(order);
        LOGGER.info(String.format("Controller :Order Data Sent ---> %s",orderEvent.toString()));
        orderProducer.sendMessage(orderEvent);
        return "Order is placed successfully";
    }
}
