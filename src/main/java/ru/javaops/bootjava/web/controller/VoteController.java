package ru.javaops.bootjava.web.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.javaops.bootjava.AuthUser;
import ru.javaops.bootjava.model.Vote;
import ru.javaops.bootjava.service.VoteService;

import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
@Slf4j
public class VoteController {

    private final VoteService voteService;

    @PostMapping(value = "/votes", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void save(@RequestParam("a") int restaurantId, @AuthenticationPrincipal AuthUser authUser) {
        LocalTime time = LocalTime.now();
        log.info("restaurant with id = {} has been chosen by user with id = {} at {}", restaurantId, authUser.id(), time);
        voteService.save(restaurantId, authUser.id(), time);
    }

    @GetMapping(value = "/votes", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Vote> getAll() {
        log.info("get all votes");
        return voteService.getAll();
    }
}
