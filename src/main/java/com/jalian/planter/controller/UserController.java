package com.jalian.planter.controller;

import com.jalian.planter.model.User;
import com.jalian.planter.service.UserService;
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
    public List<User> getAllUsers() {
        return this.userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable int id) {
        User user = this.userService.getUserById(id);

        if (user == null) {
            //Excepcion
            return null;
        } else {
            return user;
        }
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return this.userService.createUser(user);
    }
}
