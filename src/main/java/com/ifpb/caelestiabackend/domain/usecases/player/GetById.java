package com.ifpb.caelestiabackend.domain.usecases.player;

import com.ifpb.caelestiabackend.domain.entities.User.Player.Player;

public interface GetById {
    Player getById(Long id);
}
