package com.ifpb.caelestiabackend.services.achievement;

import com.ifpb.caelestiabackend.domain.entities.Achievement.Achievement;
import com.ifpb.caelestiabackend.domain.usecases.achievement.AddAchievement;
import com.ifpb.caelestiabackend.domain.usecases.achievement.DeleteAchievement;
import com.ifpb.caelestiabackend.domain.usecases.achievement.GetAchievementById;
import com.ifpb.caelestiabackend.domain.usecases.achievement.UpdateAchievement;
import com.ifpb.caelestiabackend.repository.AchievementRepository;
import org.springframework.stereotype.Service;

@Service
public class AchievementService implements AddAchievement, GetAchievementById, UpdateAchievement, DeleteAchievement {

    private final AchievementRepository achievementRepository;

    public AchievementService(AchievementRepository achievementRepository) {
        this.achievementRepository = achievementRepository;
    }

    @Override
    public Achievement add(Achievement achievement) {
        return achievementRepository.save(achievement);
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Achievement getById(Long id) {
        return null;
    }

    @Override
    public Achievement update(Long id, Achievement achievement) {
        return null;
    }
}
