package com.example.demo.config.service;

import com.example.demo.config.exception.CarNotFoundException;
import com.example.demo.config.models.Order;
import com.example.demo.config.models.User;
import com.example.demo.config.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Service
public class UserService {


    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public ResponseEntity<User> getUserById(Long id) throws Exception {
        User user = userRepository.findUserById(id);
        if (isNull(id)){
            LOGGER.info("User with " + id + " not found");
            throw new CarNotFoundException(String.format("User with %d not found", id)); // якщо не знайшов кидай помилку
        }
        return new ResponseEntity<>(user, HttpStatus.OK );
    }

    public ResponseEntity<String> deletedUserById(Long id) throws CarNotFoundException {
        if (nonNull(id)) {
            userRepository.deleteById(id);
            LOGGER.info("Deleted user");
            return new ResponseEntity<>("Deleted user", HttpStatus.OK);
        }
        throw new CarNotFoundException(String.format("User with %d not found", id));
    }

    public void updateUser(User newUser) throws CarNotFoundException {
        User user = userRepository.findUserById(newUser.getId());
        if (nonNull(newUser)) {
            update(user, newUser);
            LOGGER.info("Updated with: " + newUser.getId());
            userRepository.save(user);
        }else {
            LOGGER.info("Order with " + newUser.getId() + " not found");
            throw new CarNotFoundException(String.format("User with %d not found", newUser.getId()));
        }

    }

    public void update(User user, User newUser){
       user.setName(newUser.getName());
       user.setNumber(newUser.getNumber());
       user.setLastName(newUser.getLastName());
    }

    public ResponseEntity<String> addUser(User user) throws CarNotFoundException {
        if (nonNull(user)) {
            userRepository.save(user);
            return new ResponseEntity<>("Added", HttpStatus.OK);
        }
        throw new CarNotFoundException(String.format("User with %d not found", user.getId()));
    }

}
