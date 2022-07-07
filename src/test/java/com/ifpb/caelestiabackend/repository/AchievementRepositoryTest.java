package com.ifpb.caelestiabackend.repository;

import com.ifpb.caelestiabackend.domain.entities.Achievement.Achievement;
import com.ifpb.caelestiabackend.domain.entities.Achievement.BonusType;
import com.ifpb.caelestiabackend.domain.entities.Module;
import com.ifpb.caelestiabackend.domain.entities.PraticalLesson.PraticalLesson;
import com.ifpb.caelestiabackend.util.AchievementFactory;
import com.ifpb.caelestiabackend.util.ModuleFactory;
import com.ifpb.caelestiabackend.util.PraticalLessonFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@DisplayName("Achievement repository tests")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class AchievementRepositoryTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(ModuleRepositoryTest.class);

    @Autowired
    private AchievementRepository achievementRepository;

    @Autowired
    private ModuleRepository moduleRepository;

    @Test
    public void mustSaveAchievementInTheDatabase() {
        Module module = moduleRepository.save(ModuleFactory.makeModule());
        Achievement ac = AchievementFactory.makeAchievement();
        Achievement expectedAc = AchievementFactory.makePersistedAchievement();
        expectedAc.setModule(module);
        ac.setModule(module);

        Achievement savedAc = achievementRepository.save(ac);

        Assertions.assertEquals(expectedAc, savedAc);
        Assertions.assertNotNull(savedAc.getId());
    }

    @Test
    public void mustFindAchievementById() {
        Module module = moduleRepository.save(ModuleFactory.makeModule());
        Achievement ac = AchievementFactory.makeAchievement();
        ac.setModule(module);

        Achievement expectedAc = achievementRepository.save(ac);

        Optional<Achievement> acFound = achievementRepository.findById(expectedAc.getId());

        Assertions.assertEquals(expectedAc, acFound.get());
    }

    @Test
    public void mustUpdateAAchievement() {
        Module persistedModule = moduleRepository.save(ModuleFactory.makeModule());
        Achievement ac = AchievementFactory.makeAchievement();
        ac.setModule(persistedModule);

        Achievement acToUpdate = achievementRepository.save(ac);
        acToUpdate.setBaseBonusPercentage(new BigDecimal("0.9"));
        acToUpdate.setBonusType(BonusType.PRATICAL_LESSON);

        Achievement acPersisted = achievementRepository.save(acToUpdate);
        Achievement acExpect = AchievementFactory.makePersistedAchievement();
        acExpect.setModule(acPersisted.getModule());
        acExpect.setId(acPersisted.getId());
        acExpect.setBaseBonusPercentage(new BigDecimal("0.9"));
        acExpect.setBonusType(BonusType.PRATICAL_LESSON);

        Assertions.assertEquals(acExpect, acPersisted);
    }

    @Test
    public void mustDeleteAAchievementById() {
        Module persistedModule = moduleRepository.save(ModuleFactory.makeModule());
        Achievement ac = AchievementFactory.makeAchievement();
        ac.setModule(persistedModule);

        Achievement acToUpdate = achievementRepository.save(ac);

        achievementRepository.deleteById(acToUpdate.getId());

        Optional<Achievement> acNotFound = achievementRepository.findById(acToUpdate.getId());

        Assertions.assertFalse(acNotFound.isPresent());
    }
}