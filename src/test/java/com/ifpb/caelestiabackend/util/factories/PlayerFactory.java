package com.ifpb.caelestiabackend.util.factories;

import com.ifpb.caelestiabackend.domain.entities.User.Player.Player;

import java.time.LocalDateTime;

public class PlayerFactory {

    public static Player makePlayer() {
        return Player.builder()
                .name("Paulo Sérgio")
                .nickname("TogakureRyu")
                .email("paulo@gmail.com")
                .passwordHash("hashedpassword")
                .coins(0)
                .totalPoints(0L)
                .level(1)
                .build();
    }

    public static Player makePersistedPlayer() {
        Player player = makePlayer();
        player.setId(1L);
        player.setCreatedAt(LocalDateTime.now());
        player.setUpdatedAt(LocalDateTime.now());
        return player;
    }
}
