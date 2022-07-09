package com.ifpb.caelestiabackend.repository;

import com.ifpb.caelestiabackend.domain.entities.User.Player.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Long> {
}
