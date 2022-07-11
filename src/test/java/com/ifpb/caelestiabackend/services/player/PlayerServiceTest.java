package com.ifpb.caelestiabackend.services.player;

import com.ifpb.caelestiabackend.domain.entities.Achievement.Achievement;
import com.ifpb.caelestiabackend.domain.entities.Module;
import com.ifpb.caelestiabackend.domain.entities.User.Player.Player;
import com.ifpb.caelestiabackend.repository.PlayerRepository;
import com.ifpb.caelestiabackend.util.factories.AchievementFactory;
import com.ifpb.caelestiabackend.util.factories.ModuleFactory;
import com.ifpb.caelestiabackend.util.factories.PlayerFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Player service tests")
class PlayerServiceTest {

    @Autowired
    private PlayerService playerService;

    @MockBean
    private PlayerRepository playerRepository;

    @Test
    public void shouldAddAPlayer() {
        Player player = PlayerFactory.makePlayer();
        Player expectedPlayer = PlayerFactory.makePersistedPlayer();

        Mockito.when(playerRepository.save(ArgumentMatchers.eq(player)))
                .thenReturn(expectedPlayer);

        Player persistedPlayer = playerService.add(player);

        Assertions.assertEquals(expectedPlayer, persistedPlayer);
    }

    @Test
    public void shouldGetPlayertById() {
        Player player = PlayerFactory.makePlayer();
        Player expectedPlayer = PlayerFactory.makePersistedPlayer();

        Mockito.when(playerRepository.save(ArgumentMatchers.eq(player)))
                .thenReturn(expectedPlayer);

        Player persistedPlayer = playerRepository.save(player);

        Mockito.when(playerRepository.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.of(persistedPlayer));

        Player playerFound = playerService.getById(persistedPlayer.getId());

        Assertions.assertEquals(persistedPlayer, playerFound);
    }
}