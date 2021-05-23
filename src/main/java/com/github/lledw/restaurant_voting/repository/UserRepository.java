package com.github.lledw.restaurant_voting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.github.lledw.restaurant_voting.model.User;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("SELECT u FROM User u WHERE LOWER(u.email) =:email")
    Optional<User> getByEmail(@Param("email") String email);

    @Query("SELECT u FROM User u WHERE LOWER(u.lastName) =:last_name")
    List<User> getByLastName(@Param("last_name") String lastName);
}