package com.jalian.planter.controller;

import com.jalian.planter.model.User;
import com.jalian.planter.request.LoginRequest;
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

        if (!valideUser(user)) {
            return null;
        }

        return this.userService.createUser(user);
    }

    @PostMapping("/login")
    public User loginUser (@RequestBody LoginRequest body) {

        return this.userService.loginUser(body.getEmail(), body.getPassword());
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable int id, @RequestBody User user) {
        return this.userService.updateUser(id, user);
    }

    private boolean valideUser(User user) {

        if (user.getName() == "" || user.getName() == null || user.getEmail() == "" || user.getEmail() == null
        || user.getPassword() == "" || user.getPassword() == null) {
            return false;
        }

        return true;
    }
}
