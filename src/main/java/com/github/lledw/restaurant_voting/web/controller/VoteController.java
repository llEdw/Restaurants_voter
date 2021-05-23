package com.github.lledw.restaurant_voting.web.controller;

import com.github.lledw.restaurant_voting.AuthUser;
import com.github.lledw.restaurant_voting.model.Vote;
import com.github.lledw.restaurant_voting.service.VoteService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
@Slf4j
public class VoteController {

    private final VoteService voteService;

    @PostMapping(value = "/votes", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Vote> save(@RequestParam("a") int restaurantId, @AuthenticationPrincipal AuthUser authUser) {
        LocalTime time = LocalTime.now();
        log.info("restaurant with id = {} has been chosen by user with id = {} at {}", restaurantId, authUser.id(), time);
        Vote created = voteService.save(restaurantId, authUser.id(), time);
        return ResponseEntity.ok().body(created);
    }

    @GetMapping(value = "/votes", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Vote> getAll() {
        log.info("get all votes");
        return voteService.getAll();
    }
}
