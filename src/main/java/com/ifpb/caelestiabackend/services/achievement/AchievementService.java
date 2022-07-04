package com.ifpb.caelestiabackend.services.achievement;

import com.ifpb.caelestiabackend.domain.entities.Achievement.Achievement;
import com.ifpb.caelestiabackend.domain.usecases.achievement.AddAchievement;
import com.ifpb.caelestiabackend.domain.usecases.achievement.DeleteAchievement;
import com.ifpb.caelestiabackend.domain.usecases.achievement.GetAchievementById;
import com.ifpb.caelestiabackend.domain.usecases.achievement.UpdateAchievement;
import com.ifpb.caelestiabackend.repository.AchievementRepository;
import com.ifpb.caelestiabackend.util.CopyNotNullProperties;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

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
        Optional<Achievement> ac = achievementRepository.findById(id);

        if (ac.isEmpty()) {
            throw new EntityNotFoundException(String.format("A conquista de Id %d não existe.", id));
        }

        return ac.get();
    }

    @Override
    public Achievement update(Long id, Achievement achievement) {
        Optional<Achievement> acFound = achievementRepository.findById(id);

        if (acFound.isEmpty()) {
            throw new EntityNotFoundException(String.format("A conquista de Id %d não existe.", id));
        }

        Achievement acToPersist = acFound.get();

        CopyNotNullProperties.copyNonNullProperties(achievement, acToPersist);

        return achievementRepository.save(acToPersist);
    }
}
