package com.github.lledw.restaurant_voting.service;

import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import com.github.lledw.restaurant_voting.model.Restaurant;
import com.github.lledw.restaurant_voting.repository.RestaurantRepository;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    public List<Restaurant> getAll() {
        log.info("get all restaurants");
        return restaurantRepository.getAll();
    }

    public Restaurant save(Restaurant restaurant) {
        log.info("save restaurant {}", restaurant);
        Assert.notNull(restaurant, "Input restaurant must not be null");
        return restaurantRepository.save(restaurant);
    }

    public Restaurant get(int id) throws NotFoundException {
        log.info("get restaurant with id = {}", id);
        return restaurantRepository.findById(id).orElseThrow(() -> new NotFoundException("Not found restaurant entity with id " + id));
    }

    public void delete(int id) throws NotFoundException {
        log.info("delete restaurant with id = {}", id);
        restaurantRepository.deleteById(id);
    }

    public void update(Restaurant restaurant) {
        log.info("update restaurant {}", restaurant);
        Assert.notNull(restaurant, "Input restaurant must not be null");
        restaurantRepository.save(restaurant);
    }

    public List<Restaurant> getByName(String name) {
        log.info("find restaurant by name {}", name);
        return restaurantRepository.getByName(name.toLowerCase());
    }
}
