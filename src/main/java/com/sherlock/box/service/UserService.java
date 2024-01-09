package com.sherlock.box.service;

import com.sherlock.box.exception.UserNotFoundException;
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


    public ResponseEntity<User> getUserById(Long id) throws UserNotFoundException {
        User user = userRepository.findUserById(id);
        if (isNull(user)) {
            LOGGER.info("User with " + id + " not found");
            throw new UserNotFoundException(String.format("User with %d not found", id)); // якщо не знайшов кидай помилку
        }
        LOGGER.info("User has been got. ID:" + user.getId());
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    public ResponseEntity<String> deletedUserById(Long id) throws UserNotFoundException {
        User user = userRepository.findUserById(id);
        if (isNull(user)) {
            throw new UserNotFoundException(String.format("User with %d not found", id));
        }
        userRepository.deleteById(id);
        return new ResponseEntity<>("User has been deleted. ID:" + user.getId(), HttpStatus.OK);
    }

    public ResponseEntity<String> addUser(User user){
        userRepository.save(user);
        return new ResponseEntity<>("User has been added. ID:" + user.getId(), HttpStatus.OK);
    }

    public void updateUser(User newUser) throws UserNotFoundException {
        User user = userRepository.findUserById(newUser.getId());
        if (isNull(user)) {
            LOGGER.info("User with " + newUser.getId() + " not found");
            throw new UserNotFoundException(String.format("User with %d not found", newUser.getId()));
        }
        update(user, newUser);
        LOGGER.info("User has been updated. ID:" + user.getId());
        userRepository.save(user);
    }

    private void update(User user, User newUser) {
        user.setFirstName(newUser.getFirstName());
        user.setNumber(newUser.getNumber());
        user.setLastName(newUser.getLastName());
    }
}
