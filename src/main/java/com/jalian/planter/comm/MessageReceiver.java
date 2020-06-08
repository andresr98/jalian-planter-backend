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

        //message structure -- potId:deviceId:value
        final String REGEX = ":";
        String[] messages = message.split(REGEX);
        logger.info(String.format("Se ha recibido como pot id: %s ", messages[0]));
        logger.info(String.format("Se ha recibido como device id: %s ", messages[1]));
        logger.info(String.format("Se ha recibido como value: %s ", messages[2]));
    }
}
