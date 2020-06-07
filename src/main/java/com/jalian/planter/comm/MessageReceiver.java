package com.jalian.planter.comm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@RabbitListener(queues = "devices_outbound")
@Component
public class MessageReceiver {

    private static Logger logger = LoggerFactory.getLogger(MessageReceiver.class);


    @RabbitHandler
    public void processIncomingMessage(byte[] data) {
        String message = new String(data);
        logger.info(String.format("Se ha recibido: %s ", message));

        //message structure -- device_name:value
        int index = message.indexOf(":");
        String device = message.substring(0,index);
        String value = message.substring(index+1);
        //insert into database
    }
}
