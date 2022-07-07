package com.ifpb.caelestiabackend.services.achievement;

import com.ifpb.caelestiabackend.domain.entities.Achievement.Achievement;
import com.ifpb.caelestiabackend.domain.entities.Module;
import com.ifpb.caelestiabackend.domain.usecases.achievement.AddAchievement;
import com.ifpb.caelestiabackend.domain.usecases.achievement.DeleteAchievement;
import com.ifpb.caelestiabackend.domain.usecases.achievement.GetAchievementById;
import com.ifpb.caelestiabackend.domain.usecases.achievement.UpdateAchievement;
import com.ifpb.caelestiabackend.repository.AchievementRepository;
import com.ifpb.caelestiabackend.repository.ModuleRepository;
import com.ifpb.caelestiabackend.util.CopyNotNullProperties;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class AchievementService implements AddAchievement, GetAchievementById, UpdateAchievement, DeleteAchievement {

    private final AchievementRepository achievementRepository;

    private final ModuleRepository moduleRepository;

    public AchievementService(AchievementRepository achievementRepository, ModuleRepository moduleRepository) {
        this.achievementRepository = achievementRepository;
        this.moduleRepository = moduleRepository;
    }

    @Override
    public Achievement add(Achievement achievement) {
        Optional<Module> moduleofLesson = moduleRepository.findById(achievement.getModule().getId());

        if (moduleofLesson.isEmpty()) {
            throw new EntityNotFoundException(String.format("O módulo de Id %d não existe.",
                    achievement.getModule().getId()));
        }

        return achievementRepository.save(achievement);
    }

    @Override
    public void delete(Long id) {
        Optional<Achievement> ac = achievementRepository.findById(id);

        if (ac.isEmpty()) {
            throw new EntityNotFoundException(String.format("A conquista de Id %d não existe.", id));
        }

        achievementRepository.deleteById(id);
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
