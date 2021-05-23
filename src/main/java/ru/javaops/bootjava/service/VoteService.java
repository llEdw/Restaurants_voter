package ru.javaops.bootjava.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.javaops.bootjava.model.Vote;
import ru.javaops.bootjava.repository.VoteRepository;

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
        if (voteRepository.findByUserId(userId).orElse(null) != null) {
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
