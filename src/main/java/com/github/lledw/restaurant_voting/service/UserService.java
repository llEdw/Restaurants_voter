package com.github.lledw.restaurant_voting.service;

import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import com.github.lledw.restaurant_voting.model.User;
import com.github.lledw.restaurant_voting.repository.UserRepository;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void delete(int id) throws NotFoundException {
        log.info("delete user with id = {}", id);
        userRepository.deleteById(id);
    }

    public void update(User user) {
        log.info("update to {}", user);
        Assert.notNull(user, "Input user must not be null");
        userRepository.save(user);
    }

    public User register(User user) {
        log.info("register {}", user);
        Assert.notNull(user, "Input user must not be null");
        return userRepository.save(user);
    }

    public List<User> getAll() {
        log.info("get all users");
        return userRepository.findAll(Sort.by(Sort.Direction.ASC, "email"));
    }

    public User save(User user) {
        log.info("save {}", user);
        Assert.notNull(user, "Input user must not be null");
        return userRepository.save(user);
    }

    public User get(int id) throws NotFoundException {
        log.info("get user with id {}", id);
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException("Not found user entity with id " + id));
    }

    public void deleteById(int id) throws NotFoundException {
        log.info("delete user with id = {}", id);
        userRepository.deleteById(id);
    }

    public void updateById(User user) {
        log.info("update to {}", user);
        Assert.notNull(user, "Input user must not be null");
        userRepository.save(user);
    }

    public User getByEmail(String email) throws NotFoundException {
        log.info("find user by email {}", email);
        return userRepository.getByEmail(email.toLowerCase()).orElseThrow(() -> new NotFoundException("Not found user entity with email " + email));
    }

    public List<User> getByLastName(String lastname) {
        log.info("find user by lastname {}", lastname);
        return userRepository.getByLastName(lastname.toLowerCase());
    }
}
