package ru.javaops.bootjava.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalTime;

@Entity
@Table(name = "votes")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Vote extends BaseEntity implements Serializable {

    @Column(name = "user_id")
    private int userId;

    @Column(name = "restaurant_id")
    private int restaurantId;

    @Column(name = "voting_time")
    @NotNull
    private LocalTime votingTime;
}
