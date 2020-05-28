package com.jalian.planter.service;

import com.jalian.planter.exception.BadRequestException;
import com.jalian.planter.exception.DataDuplicationException;
import com.jalian.planter.exception.DataNotFoundException;
import com.jalian.planter.exception.InternalServerException;
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
        try {
            List<User> users = this.userRepository.findAll();

            if (users.size() == 0 ) {
                throw new DataNotFoundException("No hay usuarios en la base de datos");
            }

            return this.userRepository.findAll();

        } catch (InternalServerException e) {
            throw new InternalServerException("No se puede acceder a la base de datos");
        }
    }

    public User getUserById(int id) {
        try {
            Optional<User> userOptional = this.userRepository.findById(id);

            if (userOptional.isPresent()) {
                return userOptional.get();
            }

            throw new DataNotFoundException(String.format("El usuario solicitado con identificador %d no existe en la bd", id));

        } catch (InternalServerException e) {
            throw new InternalServerException("No se puede obtener la infomación del usuario");
        }
    }

    public User createUser (User user) {
        try {

            Optional<User> userOptional = this.userRepository.findByEmail(user.getEmail());

            if (userOptional.isPresent()) {
                throw new DataDuplicationException("Ya existe el correo ingresado");
            }

            final int roleValue = 2;
            user.setRole(roleValue);

            return this.userRepository.save(user);

        } catch (InternalServerException e) {
            throw new InternalServerException("No se puede crear el usuario");
        }
    }

    public User loginUser (String email, String password) {
        try {

            Optional<User> userOptional = this.userRepository.findByEmailAndPassword(email, password);

            if (userOptional.isPresent()) {
                return userOptional.get();
            }
            else {
               throw new BadRequestException("Credenciales incorrectas");
            }
        } catch (InternalServerException e) {
            throw new InternalServerException("No se puede conectar con el servidor");
        }
    }

    public User updateUser (int id, User user) {
        try {
            Optional<User> userOptional = this.userRepository.findById(id);

            if(userOptional.isPresent()) {
                user.setId(id);
                return this.userRepository.save(user);

            } else {
                throw new DataNotFoundException(String.format("No se encuentra el usuario con el idenfiticador %d", id));
            }
        } catch (InternalServerException e) {
            throw new InternalServerException("No se puede actualizar el usuario");
        }
    }
}
