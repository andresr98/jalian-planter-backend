package com.jalian.planter.controller;

import com.jalian.planter.exception.BadRequestException;
import com.jalian.planter.model.Device;
import com.jalian.planter.request.SendRequest;
import com.jalian.planter.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/devices")
public class DeviceController {

    private DeviceService deviceService;

    public DeviceController (DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @GetMapping
    public ResponseEntity<List<Device>> getAllDevices () {
        return ResponseEntity.status(HttpStatus.OK).body(this.deviceService.getAllDevices());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Device> getDeviceById(@PathVariable int id) {
        return ResponseEntity.status(HttpStatus.OK).body(this.deviceService.getDeviceById(id));
    }

    @PostMapping
    public ResponseEntity<Device> createDevice (@RequestBody Device device) {
        if (device.getName() == "" || device.getName() == null) {
            throw new BadRequestException("Parámetros incorrectos");
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(this.deviceService.createDevice(device));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Device> updateDeviceById (@PathVariable int id, @RequestBody Device device) {
        if (device.getName() == "" || device.getName() == null) {
            throw new BadRequestException("Parámetros incorrectos");
        }
        return ResponseEntity.status(HttpStatus.OK).body(this.deviceService.updteDeviceById(id, device));
    }

    @PostMapping("/{id}/send")
    public ResponseEntity sendMessageToRabbit (@PathVariable String id, @RequestBody SendRequest sendRequest) {
        deviceService.sendMessageToRabbit(id, sendRequest.getPotId(), sendRequest.getValue());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


}
