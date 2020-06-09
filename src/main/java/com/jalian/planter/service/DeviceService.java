package com.jalian.planter.service;

import com.jalian.planter.comm.MessageSender;
import com.jalian.planter.exception.DataNotFoundException;
import com.jalian.planter.exception.InternalServerException;
import com.jalian.planter.model.Device;
import com.jalian.planter.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DeviceService {

    private DeviceRepository deviceRepository;

    @Autowired
    private MessageSender messageSender;

    @Autowired
    private PotDeviceService potDeviceService;

    public DeviceService(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    public List<Device> getAllDevices () {
        try {
            List<Device> devices = this.deviceRepository.findAll();

            if (devices.size() == 0) {
                throw new DataNotFoundException("No hay dispositivos en la bd");
            }

            return devices;
        } catch (InternalServerException e) {
            throw new InternalServerException("No se pueden traer los dispositivos");
        }
    }

    public Device getDeviceById (int id) {
        try {
            Optional<Device> optionalDevice = this.deviceRepository.findById(id);

            if (optionalDevice.isPresent()) {
                return optionalDevice.get();
            }

            throw new DataNotFoundException(String.format("El dispositivo con identificador %d no existe en la bd", id));

        } catch (InternalServerException e) {
            throw new InternalServerException("No se puede traer el dispositivo");
        }
    }

    public Device createDevice (Device device) {
        try {
            return this.deviceRepository.save(device);

        } catch (InternalServerException e) {
            throw new InternalServerException("No se puede crear el dispositivo");
        }
    }

    public Device updteDeviceById (int id, Device device) {
        try {

            boolean existsDevice = this.deviceRepository.existsById(id);

            if(existsDevice) {
                device.setId(id);
                return this.deviceRepository.save(device);
            }

            throw new DataNotFoundException(String.format("El dispositivo con identificador %d no existe en la bd", id));

        } catch (InternalServerException e) {
            throw new InternalServerException("No se puede actualizar el dispositivo");
        }
    }

    public void sendMessageToRabbit(String deviceId, String potId, String value) {
        potDeviceService.registerMessage(Integer.parseInt(potId), Integer.parseInt(deviceId),
                Integer.parseInt(value));

        messageSender.send("pot_" + potId +  "_" + deviceId, value.getBytes());
    }

}
