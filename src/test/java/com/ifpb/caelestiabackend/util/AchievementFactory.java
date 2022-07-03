package com.ifpb.caelestiabackend.util;

import com.ifpb.caelestiabackend.domain.entities.Achievement.Achievement;
import com.ifpb.caelestiabackend.domain.entities.Achievement.BonusType;
import com.ifpb.caelestiabackend.domain.entities.Module;
import com.ifpb.caelestiabackend.domain.entities.PraticalLesson.PraticalLesson;

import java.math.BigDecimal;

public class AchievementFactory {

    public static Achievement makeAchievement() {
        return Achievement.builder()
                .achievementName("Observador Celeste")
                .description("+5% de xp em lições do tipo teórica")
                .baseBonusPercentage(new BigDecimal("0.5"))
                .bonusType(BonusType.THEORIC_LESSON)
                .build();
    }

    public static Achievement makePersistedAchievement() {
        return Achievement.builder()
                .id(1L)
                .achievementName("Observador Celeste")
                .description("+5% de xp em lições do tipo teórica")
                .baseBonusPercentage(new BigDecimal("0.5"))
                .bonusType(BonusType.THEORIC_LESSON)
                .build();
    }
}
