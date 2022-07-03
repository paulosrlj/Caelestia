package com.ifpb.caelestiabackend.domain.usecases.achievement;

import com.ifpb.caelestiabackend.domain.entities.Achievement.Achievement;

public interface AddAchievement {
    Achievement add(Achievement achievement);
}
