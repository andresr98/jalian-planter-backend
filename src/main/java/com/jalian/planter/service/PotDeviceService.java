package com.jalian.planter.service;

import com.jalian.planter.comm.MessageSender;
import com.jalian.planter.model.Device;
import com.jalian.planter.model.Message;
import com.jalian.planter.model.Pot;
import com.jalian.planter.model.PotDevice;
import com.jalian.planter.repository.MessageRepository;
import com.jalian.planter.repository.PotDeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PotDeviceService {

    private PotDeviceRepository potDeviceRepository;

    @Autowired
    private DeviceService deviceService;

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private MessageSender messageSender;

    public PotDeviceService (PotDeviceRepository potDeviceRepository) {
        this.potDeviceRepository = potDeviceRepository;
    }

    //Insertar los dispositivos asociados a una matera cuando se crea una nueva matera
    public void insertPotDevices (Pot pot) {
        List<Device> devices = this.deviceService.getAllDevices();

        for(Device device : devices) {
            PotDevice potDevice;

            //Verificar si ya existe la asociación

            Optional<PotDevice> potDeviceOptional = this.potDeviceRepository.findByPotAndDevice(pot, device);

            if (potDeviceOptional.isPresent()) {
                potDevice = potDeviceOptional.get();
            } else {
                potDevice = new PotDevice(true);
            }

            potDevice.setPot(pot);
            potDevice.setDevice(device);

            //Mantener el registro actualizado de la última información recibida por el sensor
            potDevice.setUpdatedAt(LocalDateTime.now());

            //Guardar o actualizar registro
            this.potDeviceRepository.save(potDevice);
        }
    }

    //Actualiar updatedAt cuando se reciba un mensaje via MQTT
    public void updateDate(int potId, int deviceId) {
        Optional <PotDevice> potDeviceOptional = this.potDeviceRepository.findByPot_IdAndDevice_Id(potId, deviceId);

        if (potDeviceOptional.isPresent()) {
            PotDevice potDevice = potDeviceOptional.get();
            potDevice.setUpdatedAt(LocalDateTime.now());

            this.potDeviceRepository.save(potDevice);
        }
    }

    public void registerMessage(int potId, int deviceId, int value) {
        Optional <PotDevice> potDeviceOptional = potDeviceRepository.findByPot_IdAndDevice_Id(potId, deviceId);

        if (potDeviceOptional.isPresent()) {
            PotDevice potDevice = potDeviceOptional.get();
            Message message = new Message(value);
            message.setPotDevice(potDevice);
            messageRepository.save(message);
            updateDate(potId, deviceId);
            sendMessage(potId, deviceId, value);
        }

    }

    private void sendMessage(int potId, int deviceId, int value) {
        String routingKey = "pot_" + Integer.toString(potId);
        String messageToSend = Integer.toString(deviceId) + ":" + Integer.toString(value);
        messageSender.send(routingKey, messageToSend.getBytes());
    }
}
