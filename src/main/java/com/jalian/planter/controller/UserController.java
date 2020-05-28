package com.jalian.planter.controller;

import com.jalian.planter.exception.BadRequestException;
import com.jalian.planter.model.User;
import com.jalian.planter.request.LoginRequest;
import com.jalian.planter.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.status(HttpStatus.OK).body(this.userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable int id) {
        return ResponseEntity.status(HttpStatus.OK).body(this.userService.getUserById(id));
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {

        if (!validateUser(user)) {
            throw new BadRequestException("Párametros ingresados incorrectamente");
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(this.userService.createUser(user));
    }

    @PostMapping("/login")
    public ResponseEntity<User> loginUser (@RequestBody LoginRequest body) {

        if(body.getPassword() == null || body.getPassword() == "" || body.getEmail() == "" || body.getEmail() == null) {
            throw new BadRequestException("No se han ingresado los párametros");
        }

        return ResponseEntity.status(HttpStatus.OK).body(this.userService.loginUser(body.getEmail(), body.getPassword()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable int id, @RequestBody User user) {

        if(!validateUser(user)) {
            throw new BadRequestException("El parámetro de usuario esta incorrecto");
        }
        return ResponseEntity.status(HttpStatus.OK).body(this.userService.updateUser(id, user));
    }

    private boolean validateUser(User user) {

        if (user.getName() == "" || user.getName() == null || user.getEmail() == "" || user.getEmail() == null
        || user.getPassword() == "" || user.getPassword() == null) {
            return false;
        }
        return true;
    }
}
