package com.jalian.planter.controller;

import com.jalian.planter.model.Tip;
import com.jalian.planter.service.TipService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/tips")
public class TipContoller {

    private TipService tipService;

    public  TipContoller (TipService tipService) {
        this.tipService = tipService;
    }

    @GetMapping
    public ResponseEntity<List<Tip>> getAllTips () {
        return ResponseEntity.status(HttpStatus.OK).body(tipService.getAllTips());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tip> getTipById (@PathVariable int id) {
        return ResponseEntity.status(HttpStatus.OK).body(tipService.getTipById(id));
    }

    @PostMapping
    public ResponseEntity<Tip> createTip (@RequestBody Tip tip) {
        return ResponseEntity.status(HttpStatus.CREATED).body(tipService.createTip(tip));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tip> updateTipById (@PathVariable int id, @RequestBody Tip tip) {
        return ResponseEntity.status(HttpStatus.OK).body(tipService.updateTipById(id, tip));
    }
}
