package com.jalian.planter.comm;

import com.jalian.planter.service.PotDeviceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@RabbitListener(queues = "devices_outbound")
@Component
public class MessageReceiver {

    @Autowired
    private PotDeviceService potDeviceService;
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

        final boolean isValidMessage = verifyData(messages);

        if (isValidMessage) {
            final int potId = Integer.parseInt(messages[0]);
            final int deviceId = Integer.parseInt(messages[1]);
            final int value = Integer.parseInt(messages[2]);
            potDeviceService.registerMessage(potId, deviceId, value);
        }


    }

    private boolean verifyData(String[] data) {
        if (data.length != 3) {
            return false;
        }

        for (String info : data) {
            try {
                int number = Integer.parseInt(info);
            } catch (Exception e){
                return false;
            }
        }

        return true;
    }
}
