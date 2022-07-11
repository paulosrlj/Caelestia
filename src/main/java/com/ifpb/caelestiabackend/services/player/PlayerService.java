package com.ifpb.caelestiabackend.services.player;

import com.ifpb.caelestiabackend.domain.entities.User.Player.Player;
import com.ifpb.caelestiabackend.domain.usecases.player.AddPlayer;
import com.ifpb.caelestiabackend.domain.usecases.player.GetById;
import com.ifpb.caelestiabackend.repository.PlayerRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class PlayerService implements AddPlayer, GetById {

    private final PlayerRepository playerRepository;

    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }


    @Override
    public Player add(Player player) {
        return playerRepository.save(player);
    }

    @Override
    public Player getById(Long id) {
        Optional<Player> player = playerRepository.findById(id);

        if (player.isEmpty()) {
            throw new EntityNotFoundException(String.format("O Jogador de Id %d não existe.", id));
        }

        return player.get();
    }
}
