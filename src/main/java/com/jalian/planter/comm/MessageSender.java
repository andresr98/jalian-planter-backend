package com.jalian.planter.comm;


import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class MessageSender {

    private RabbitTemplate rabbitTemplate;

    public MessageSender(RabbitTemplate rabbitTemplate){
        this.rabbitTemplate = rabbitTemplate;
    }

    public void send(String routingKey, byte[] message){
        this.rabbitTemplate.convertAndSend("amq.topic", routingKey, message);
    }

}
