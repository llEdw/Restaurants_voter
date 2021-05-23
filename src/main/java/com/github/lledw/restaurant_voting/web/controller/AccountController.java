package com.github.lledw.restaurant_voting.web.controller;

import com.github.lledw.restaurant_voting.AuthUser;
import com.github.lledw.restaurant_voting.service.UserService;
import com.github.lledw.restaurant_voting.util.ValidationUtil;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.github.lledw.restaurant_voting.model.Role;
import com.github.lledw.restaurant_voting.model.User;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/account")
@AllArgsConstructor
@Slf4j
public class AccountController {

    private final UserService userService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public User get(@AuthenticationPrincipal AuthUser authUser) {
        log.info("get {}", authUser);
        return authUser.getUser();
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@AuthenticationPrincipal AuthUser authUser) throws NotFoundException {
        log.info("delete {}", authUser);
        userService.delete(authUser.id());
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody User user, @AuthenticationPrincipal AuthUser authUser) {
        log.info("update {} to {}", authUser, user);
        User oldUser = authUser.getUser();
        ValidationUtil.assureIdConsistent(user, oldUser.id());
        user.setRoles(oldUser.getRoles());
        if (user.getPassword() == null) {
            user.setPassword(oldUser.getPassword());
        }
        userService.update(user);
    }

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<User> register(@Valid @RequestBody User user) {
        log.info("register {}", user);
        ValidationUtil.checkNew(user);
        user.setRoles(Set.of(Role.USER));
        user = userService.register(user);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/account")
                .build().toUri();
        return ResponseEntity.created(uriOfNewResource).body(user);
    }

    @GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> getAll() {
        log.info("get all users");
        return userService.getAll();
    }

    @PostMapping(value = "/users", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<User> save(@Valid @RequestBody User user) {
        log.info("save {}", user);
        ValidationUtil.checkNew(user);
        user.setRoles(Set.of(Role.USER));
        user = userService.save(user);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/account")
                .build().toUri();
        return ResponseEntity.created(uriOfNewResource).body(user);
    }

    @GetMapping(value = "/users/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public User get(@PathVariable int id) throws NotFoundException {
        log.info("get user with id = {}", id);
        return userService.get(id);
    }

    @DeleteMapping("/users/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable int id) throws NotFoundException {
        log.info("delete user with id = {}", id);
        userService.deleteById(id);
    }

    @PutMapping(value = "/users/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateById(@Valid @RequestBody User user, @PathVariable int id) {
        log.info("update user with id {} to {}", id, user);
        ValidationUtil.assureIdConsistent(user, id);
        userService.updateById(user);
    }

    @GetMapping(value = "/users/by-email", produces = MediaType.APPLICATION_JSON_VALUE)
    public User getByEmail(@RequestParam String email) throws NotFoundException {
        log.info("find user by email {}", email);
        return userService.getByEmail(email);
    }

    @GetMapping(value = "/users/by-lastname", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> getByLastName(@RequestParam String lastname) {
        log.info("find user by lastname {}", lastname);
        return userService.getByLastName(lastname);
    }
}
