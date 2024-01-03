package com.sherlock.box.service;

import com.sherlock.box.exception.CarNotFoundException;
import com.sherlock.box.models.User;
import com.sherlock.box.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import static java.util.Objects.isNull;

@Service
public class UserService {


    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public ResponseEntity<User> getUserById(Long id) throws Exception {
        User user = userRepository.findUserById(id);
        if (isNull(user)) {
            LOGGER.info("User with " + id + " not found");
            throw new CarNotFoundException(String.format("User with %d not found", id)); // якщо не знайшов кидай помилку
        }
        LOGGER.info("Order has been got. ID:" + user.getId());
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    public ResponseEntity<String> deletedUserById(Long id) throws CarNotFoundException {
        User user = userRepository.findUserById(id);
        if (isNull(user)) {
            throw new CarNotFoundException(String.format("User with %d not found", id));
        }
        userRepository.deleteById(id);
        return new ResponseEntity<>("Order has been deleted. ID:" + user.getId(), HttpStatus.OK);
    }

    public void updateUser(User newUser) throws CarNotFoundException {
        User user = userRepository.findUserById(newUser.getId());
        if (isNull(newUser)) {
            LOGGER.info("Order with " + newUser.getId() + " not found");
            throw new CarNotFoundException(String.format("User with %d not found", newUser.getId()));
        }
        update(user, newUser);
        LOGGER.info("Order has been updated. ID:" + user.getId());
        userRepository.save(user);
    }

    public void update(User user, User newUser) {
        user.setFirstName(newUser.getFirstName());
        user.setNumber(newUser.getNumber());
        user.setLastName(newUser.getLastName());
    }

    public ResponseEntity<String> addUser(User user) throws CarNotFoundException {
        if (isNull(user)) {
            throw new CarNotFoundException(String.format("User with %d not found", user.getId()));
        }
        LOGGER.info("Order has been added. ID:" + user.getId());
        userRepository.save(user);
        return new ResponseEntity<>("Order has been added. ID:" + user.getId(), HttpStatus.OK);
    }
}