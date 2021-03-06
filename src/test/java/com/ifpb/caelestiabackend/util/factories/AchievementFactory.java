package com.ifpb.caelestiabackend.util.factories;

import com.ifpb.caelestiabackend.domain.entities.Achievement.Achievement;
import com.ifpb.caelestiabackend.domain.entities.Achievement.BonusType;

import java.math.BigDecimal;

public class AchievementFactory {

    public static Achievement makeAchievement() {
        return Achievement.builder()
                .achievementName("Observador Celeste")
                .description("+5% de xp em lições do tipo teórica")
                .baseBonusPercentage(new BigDecimal("0.5"))
                .bonusType(BonusType.THEORIC_LESSON)
                .urlImage("https://urlqualquer.com.br")
                .build();
    }

    public static Achievement makePersistedAchievement() {
        Achievement ac = makeAchievement();
        ac.setId(1L);
        return ac;
    }
}
