package ru.javaops.bootjava.service;

import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.javaops.bootjava.model.Dish;
import ru.javaops.bootjava.repository.DishRepository;
import ru.javaops.bootjava.repository.RestaurantRepository;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class DishService {

    private final DishRepository dishRepository;
    private final RestaurantRepository restaurantRepository;

    public List<Dish> getAll() {
        log.info("get all dishes");
        return dishRepository.getAll();
    }

    public List<Dish> getByName(String name) {
        log.info("get meal by name {}", name);
        return dishRepository.getByName(name.toLowerCase());
    }

    public List<Dish> filterByPrice(int minPrice, int maxPrice) {
        log.info("filter between {} roubles and {} roubles", minPrice, maxPrice);
        return dishRepository.filterByPrice(minPrice, maxPrice);
    }

    public List<Dish> getAllByRestaurant(int restaurantId) {
        log.info("get all dishes of restaurant with id = {}", restaurantId);
        return dishRepository.getAllByRestaurant(restaurantId);
    }

    public Dish save(Dish dish, int restaurantId) throws NotFoundException {
        log.info("add new dish - {}", dish);
        Assert.notNull(dish, "Input dish must not be null");
        dish.setRestaurant(restaurantRepository.findById(restaurantId).orElseThrow(() -> new NotFoundException("Not found restaurant entity with id " + restaurantId)));
        return dishRepository.save(dish);
    }

    public Dish getOneDish(int restaurantId, int id) throws NotFoundException {
        log.info("get dish of restaurant with id = {}", restaurantId);
        return dishRepository.getOneDish(restaurantId, id).orElseThrow(() -> new NotFoundException("Not found dish entity with id " + id));
    }

    public void delete(int restaurantId, int id) throws NotFoundException {
        log.info("delete dish with id = {} of restaurant with restaurantId = {}", id, restaurantId);
        if (dishRepository.deleteBy(restaurantId, id) == 0) {
            throw new NotFoundException("Not found restaurant entity with id " + id);
        }
    }

    public void update(Dish dish, int restaurantId, int id) throws NotFoundException {
        log.info("update dish {} with id = {} of restaurant with restaurantId = {}", dish, id, restaurantId);
        Assert.notNull(dish, "Input dish must not be null");
        dish.setRestaurant(restaurantRepository.findById(restaurantId).orElseThrow(() -> new NotFoundException("Not found restaurant entity with id " + restaurantId)));
        dishRepository.save(dish);
    }
}
