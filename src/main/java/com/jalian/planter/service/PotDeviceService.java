package com.jalian.planter.service;

import com.jalian.planter.model.Device;
import com.jalian.planter.model.Pot;
import com.jalian.planter.model.PotDevice;
import com.jalian.planter.repository.PotDeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PotDeviceService {

    private PotDeviceRepository potDeviceRepository;

    @Autowired
    private DeviceService deviceService;

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
}
