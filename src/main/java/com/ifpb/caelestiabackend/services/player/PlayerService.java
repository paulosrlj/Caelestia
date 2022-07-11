package com.ifpb.caelestiabackend.services.player;

import com.ifpb.caelestiabackend.domain.entities.User.Player.Player;
import com.ifpb.caelestiabackend.domain.usecases.player.AddPlayer;
import com.ifpb.caelestiabackend.repository.PlayerRepository;
import org.springframework.stereotype.Service;

@Service
public class PlayerService implements AddPlayer {

    private final PlayerRepository playerRepository;

    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }


    @Override
    public Player add(Player player) {
        return playerRepository.save(player);
    }
}
