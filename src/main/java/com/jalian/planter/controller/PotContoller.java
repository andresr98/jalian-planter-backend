package com.jalian.planter.controller;

import com.jalian.planter.model.Message;
import com.jalian.planter.model.Pot;
import com.jalian.planter.repository.MessageRepository;
import com.jalian.planter.service.PotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pots")
public class PotContoller {

    private PotService potService;



    public PotContoller (PotService potService) {
        this.potService = potService;
    }

    @GetMapping
    public ResponseEntity<List<Pot>> getAllPots () {
        return ResponseEntity.status(HttpStatus.OK).body(this.potService.getAllPots());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pot> getPotById (@PathVariable int id) {
        return ResponseEntity.status(HttpStatus.OK).body(this.potService.getPotById(id));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Pot>> getPotsByUserId (@PathVariable int userId) {
        return ResponseEntity.status(HttpStatus.OK).body(this.potService.getPotByUserId(userId));
    }

    @PostMapping
    public ResponseEntity<Pot> createPot (@RequestBody Pot pot) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.potService.createPot(pot));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pot> updatePotById (@PathVariable int id, @RequestBody Pot pot) {
        return ResponseEntity.status(HttpStatus.OK).body(this.potService.updatePotById(id, pot));
    }

    @GetMapping("/{id}/messages")
    public ResponseEntity<List<Message>> getAllMessages(@PathVariable int id) {
        return ResponseEntity.status(HttpStatus.OK).body(potService.getMessages(id));
    }
}
