package com.ifpb.caelestiabackend.domain.usecases.achievement;

import com.ifpb.caelestiabackend.domain.entities.Achievement.Achievement;

public interface GetAchievementById {
    Achievement getById(Long id);
}
