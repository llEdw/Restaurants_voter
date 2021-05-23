package com.github.lledw.restaurant_voting.service;

import com.github.lledw.restaurant_voting.model.Vote;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.github.lledw.restaurant_voting.repository.VoteRepository;

import java.time.LocalTime;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class VoteService {

    public static final LocalTime voteBound = LocalTime.of(11, 0);
    private final VoteRepository voteRepository;

    public Vote save(int restaurantId, int userId, LocalTime time) {
        log.info("restaurant with id = {} has been chosen by user with id = {} at {}", restaurantId, userId, time);
        Vote oldVote = voteRepository.findByUserId(userId).orElse(null);
        if (oldVote != null) {
            if (time.isAfter(voteBound)) {
                return oldVote;
            }
        }
        return voteRepository.save(new Vote(userId, restaurantId, time));
    }

    public List<Vote> getAll() {
        log.info("get all votes");
        return voteRepository.findAll();
    }
}
