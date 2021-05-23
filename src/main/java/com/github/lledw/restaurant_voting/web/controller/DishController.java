package com.github.lledw.restaurant_voting.web.controller;

import com.github.lledw.restaurant_voting.service.DishService;
import com.github.lledw.restaurant_voting.util.ValidationUtil;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.github.lledw.restaurant_voting.model.Dish;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
@Slf4j
public class DishController {

    public static final String API_RESTAURANTS = "/restaurants";
    private final DishService dishService;

    @GetMapping(value = "/dishes", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Dish> getAll() {
        log.info("get all dishes");
        return dishService.getAll();
    }

    @GetMapping(value = "/dishes/by", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Dish> getByName(@RequestParam String name) {
        log.info("get meal by name {}", name);
        return dishService.getByName(name);
    }

    @GetMapping(value = "/dishes/filter", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Dish> filterByPrice(@RequestParam("a") int minPrice, @RequestParam("b") int maxPrice) {
        log.info("filter between {} roubles and {} roubles", minPrice, maxPrice);
        return dishService.filterByPrice(minPrice, maxPrice);
    }

    @GetMapping(value = API_RESTAURANTS + "/{restaurantId}/dishes", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Dish> getAllByRestaurant(@PathVariable int restaurantId) {
        log.info("get all dishes of restaurant with id = {}", restaurantId);
        return dishService.getAllByRestaurant(restaurantId);
    }

    @PostMapping(value = API_RESTAURANTS + "/{restaurantId}/dishes", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<Dish> save(@PathVariable int restaurantId, @Valid @RequestBody Dish dish) throws NotFoundException {
        log.info("add new dish - {} of restaurant with id {}", dish, restaurantId);
        ValidationUtil.checkNew(dish);
        Dish created = dishService.save(dish, restaurantId);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/restaurants/{restaurantId}/dishes/{id}")
                .buildAndExpand(restaurantId, created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(dish);
    }

    @GetMapping(value = API_RESTAURANTS + "/{restaurantId}/dishes/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Dish getOneDish(@PathVariable int restaurantId, @PathVariable int id) throws NotFoundException {
        log.info("get dish with id = {} of restaurant with restaurantId = {}", id, restaurantId);
        return dishService.getOneDish(restaurantId, id);
    }

    @DeleteMapping(API_RESTAURANTS + "/{restaurantId}/dishes/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int restaurantId, @PathVariable int id) throws NotFoundException {
        log.info("delete dish with id = {} of restaurant with restaurantId = {}", id, restaurantId);
        dishService.delete(restaurantId, id);
    }

    @PutMapping(value = API_RESTAURANTS + "/{restaurantId}/dishes/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody Dish dish, @PathVariable int restaurantId, @PathVariable int id) throws NotFoundException {
        log.info("update dish {} with id = {} of restaurant with restaurantId = {}", dish, id, restaurantId);
        dishService.update(dish, restaurantId, id);
    }
}
