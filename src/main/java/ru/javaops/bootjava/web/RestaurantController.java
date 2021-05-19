package ru.javaops.bootjava.web;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.javaops.bootjava.AuthUser;
import ru.javaops.bootjava.model.Restaurant;
import ru.javaops.bootjava.model.User;
import ru.javaops.bootjava.repository.RestaurantRepository;

import java.util.List;

@RestController
@RequestMapping(value = "/api/account/restaurants")
@AllArgsConstructor
@Slf4j
public class RestaurantController {

    private final RestaurantRepository restaurantRepository;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Restaurant> get() {
        log.info("get all restaurants");
        return restaurantRepository.findAll();
    }

    @DeleteMapping("{/id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        log.info("delete restaurant {}", restaurantRepository.findById(id).orElse(null).getName());
        restaurantRepository.deleteById(id);
    }
}
