package com.jalian.planter.service;

import com.jalian.planter.exception.DataNotFoundException;
import com.jalian.planter.exception.InternalServerException;
import com.jalian.planter.model.Message;
import com.jalian.planter.model.Pot;
import com.jalian.planter.repository.MessageRepository;
import com.jalian.planter.repository.PotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PotService {

    private PotRepository potRepository;

    @Autowired
    private PotDeviceService potDeviceService;

    @Autowired
    private MessageRepository messageRepository;

    public PotService (PotRepository potRepository) {
        this.potRepository = potRepository;
    }

    public List<Pot> getAllPots () {
        try {
            List<Pot> pots = this.potRepository.findAll();

            if (pots.size() == 0) {
                throw new DataNotFoundException("No hay materas en la base de datos");
            }

            return pots;

        } catch (InternalServerException e) {
            throw new InternalServerException("No se pueden traer las materas");
        }
    }

    public Pot getPotById (int id) {
        try {
            Optional<Pot> potOptional = this.potRepository.findById(id);

            if (potOptional.isPresent()) {
                return potOptional.get();
            }
            throw new DataNotFoundException(String.format("La matera de indenficiador %d no existe en bd", id));

        } catch (InternalServerException e) {
            throw new InternalServerException("No se puede traer la información de la matera");
        }
    }

    public List<Pot> getPotByUserId (int userId) {
        try {
            List<Pot> pots = this.potRepository.findByUser_Id(userId);

            if (pots.size() == 0) {
                throw new DataNotFoundException("El usuario no tiene plantas asociadas");
            }
            return pots;

        } catch (InternalServerException e) {
            throw new InternalServerException("No se pueden traer las materias del usuario");
        }
    }

    public Pot createPot (Pot pot) {
        try {

            Pot createdPot = this.potRepository.save(pot);

            //Crear los registros de device x matera

            this.potDeviceService.insertPotDevices(createdPot);
            return createdPot;

        } catch (InternalServerException e) {
            throw new InternalServerException("No se puede crear la matera");
        }
    }

    public Pot updatePotById (int id, Pot pot) {
        try {
            boolean potExists = this.potRepository.existsById(id);

            if (potExists) {
                pot.setId(id);
                return this.potRepository.save(pot);
            }
            throw new DataNotFoundException("No existe la matera solicitada");
        } catch (InternalServerException e) {
            throw new InternalServerException("No se puede actualizar la matera");
        }
    }

    public List<Message> getMessages(int id) {
        List<Message> messages = messageRepository
                .findByPotDevice_Pot_IdOrderByPotDevice_Device_Id(id);

        if (messages.size() == 0) {
            throw new DataNotFoundException("No se encuentran mensajes para la matera");
        }
        return messages;
    }
}
