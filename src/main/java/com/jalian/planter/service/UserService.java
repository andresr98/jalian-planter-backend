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
        Optional<User> userOptional = this.userRepository.findByEmail(user.getEmail());

        if (userOptional.isPresent()) {
            //Excepcion
            return null;
        }
        try {
            user.setRole(2);
            return this.userRepository.save(user);

        } catch (Exception e) {
            return null;
        }
    }

    public User loginUser (String email, String password) {
        try {
            Optional<User> userOptional = this.userRepository.findByEmailAndPassword(email, password);

            if (userOptional.isPresent()) {
                return userOptional.get();
            }
            else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

    public User updateUser (int id, User user) {
        try {
            Optional<User> userOptional = this.userRepository.findById(id);

            if(userOptional.isPresent()) {
                user.setId(id);
                return this.userRepository.save(user);
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }
}
