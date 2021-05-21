package ru.javaops.bootjava.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javaops.bootjava.model.Vote;

import java.util.ArrayList;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface VoteRepository extends JpaRepository<Vote, Integer> {

    Optional<Vote> findByUserId(int userId);

    @Query("SELECT COUNT(v.restaurantId) FROM Vote v GROUP BY v.restaurantId ORDER BY COUNT(v.restaurantId) DESC")
    ArrayList<Long> getCountList();

    @Query("SELECT v.restaurantId FROM Vote v WHERE COUNT(v.restaurantId) =:count GROUP BY v.restaurantId")
    int getRestaurantId(@Param("count") Long count);
}
