package ru.javaops.bootjava.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javaops.bootjava.model.Dish;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface DishRepository extends JpaRepository<Dish, Integer> {

    @Query("SELECT d FROM Dish d ORDER BY d.price")
    List<Dish> getAll();

    @Query("SELECT d FROM Dish d WHERE LOWER(d.name) =:name ORDER BY d.price")
    List<Dish> getByName(@Param("name") String name);

    @Query("SELECT d from Dish d WHERE d.price >=:minPrice AND d.price <=:maxPrice ORDER BY d.price")
    List<Dish> filterByPrice(@Param("minPrice") int minPrice, @Param("maxPrice") int maxPrice);

    @Query("SELECT d FROM Dish d WHERE d.restaurant.id =:restaurantId ORDER BY d.price")
    List<Dish> getAllByRestaurant(@Param("restaurantId") int restaurantId);

    @Query("SELECT d FROM Dish d WHERE d.restaurant.id =:restaurantId AND d.id =:id")
    Optional<Dish> getOneDish(@Param("restaurantId") int restaurantId, @Param("id") int id);

    @Transactional
    @Modifying
    @Query("DELETE FROM Dish d WHERE d.restaurant.id =:restaurantId AND d.id =:id")
    int deleteBy(@Param("restaurantId") int restaurantId, @Param("id") int id);
}
