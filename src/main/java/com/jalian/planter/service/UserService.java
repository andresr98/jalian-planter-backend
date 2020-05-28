package com.jalian.planter.service;

import com.jalian.planter.model.User;
import com.jalian.planter.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }

    public User getUserById(int id) {
        Optional<User> userOptional = this.userRepository.findById(id);

        if (userOptional.isPresent()) {
            return userOptional.get();
        }

        return null;
    }

    public User createUser (User user) {
        User userOptional = this.userRepository.findByEmail(user.getEmail());

        if (userOptional != null) {
            //Excepcion
            return null;
        }
        
        return this.userRepository.save(user);
    }
}
