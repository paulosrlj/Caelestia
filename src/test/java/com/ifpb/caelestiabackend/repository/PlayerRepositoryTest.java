package com.ifpb.caelestiabackend.repository;

import com.ifpb.caelestiabackend.domain.entities.Module;
import com.ifpb.caelestiabackend.domain.entities.PraticalLesson.PraticalLesson;
import com.ifpb.caelestiabackend.domain.entities.User.Player.Player;
import com.ifpb.caelestiabackend.util.factories.ModuleFactory;
import com.ifpb.caelestiabackend.util.factories.PlayerFactory;
import com.ifpb.caelestiabackend.util.factories.PraticalLessonFactory;
import lombok.extern.log4j.Log4j2;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@DisplayName("Player repository tests")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@Log4j2
class PlayerRepositoryTest {

    @Autowired
    private PlayerRepository playerRepository;

    @Test
    public void mustSavePlayerInDatabase() {
        Player player = PlayerFactory.makePlayer();

        Player persistedPlayer = playerRepository.save(player);

        log.info(persistedPlayer);

        Assertions.assertThat(persistedPlayer.getName()).isEqualTo(player.getName());
        Assertions.assertThat(persistedPlayer.getId()).isNotNull();
        Assertions.assertThat(persistedPlayer.getCreatedAt()).isNotNull();
        Assertions.assertThat(persistedPlayer.getUpdatedAt()).isNotNull();
    }

    @Test
    public void mustUpdatePlayerInDatabase() {
        Player player = PlayerFactory.makePlayer();

        Player persistedPlayer = playerRepository.save(player);
        persistedPlayer.setNickname("TogakureAtualizado");
        persistedPlayer.setCoins(200);

        Player updatedPlayer = playerRepository.save(persistedPlayer);

        Assertions.assertThat(updatedPlayer.getNickname()).isEqualTo(persistedPlayer.getNickname());
        Assertions.assertThat(updatedPlayer.getCoins()).isEqualTo(persistedPlayer.getCoins());

    }

    @Test
    public void mustFindPlayerById() {
        Player player = PlayerFactory.makePlayer();
        Player expectedPlayer = playerRepository.save(player);

        Optional<Player> playerFound = playerRepository.findById(expectedPlayer.getId());

        Assertions.assertThat(expectedPlayer).isEqualTo(playerFound.get());
    }

    @Test
    public void mustDeleteAPlayer() {
        Player player = PlayerFactory.makePlayer();
        Player persistedPlayer = playerRepository.save(player);

        playerRepository.deleteById(persistedPlayer.getId());

        Optional<Player> playerFound = playerRepository.findById(persistedPlayer.getId());

        Assertions.assertThat(playerFound.isPresent()).isFalse();
    }
}