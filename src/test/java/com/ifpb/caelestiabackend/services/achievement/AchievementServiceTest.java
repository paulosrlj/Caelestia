package com.ifpb.caelestiabackend.services.achievement;

import com.ifpb.caelestiabackend.domain.entities.Achievement.Achievement;
import com.ifpb.caelestiabackend.domain.entities.Module;
import com.ifpb.caelestiabackend.domain.entities.PraticalLesson.PraticalLesson;
import com.ifpb.caelestiabackend.repository.AchievementRepository;
import com.ifpb.caelestiabackend.repository.ModuleRepository;
import com.ifpb.caelestiabackend.util.AchievementFactory;
import com.ifpb.caelestiabackend.util.ModuleFactory;
import com.ifpb.caelestiabackend.util.PraticalLessonFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Achievement tests")
class AchievementServiceTest {

    @Autowired
    private AchievementService achievementService;

    @MockBean
    private AchievementRepository achievementRepository;

    @MockBean
    private ModuleRepository moduleRepository;

    @Test
    public void shouldAddAAchievement() {
        Module module = ModuleFactory.makePersistedModule();
        Achievement ac = AchievementFactory.makeAchievement();
        Achievement fakePersistedAc = AchievementFactory.makePersistedAchievement();
        ac.setModule(module);
        fakePersistedAc.setModule(module);

        Mockito.when(moduleRepository.save(ArgumentMatchers.any(Module.class)))
                .thenReturn(module);
        Mockito.when(achievementRepository.save(ArgumentMatchers.any(Achievement.class)))
                .thenReturn(fakePersistedAc);

        Achievement expectedAc = AchievementFactory.makePersistedAchievement();
        expectedAc.setModule(module);

        moduleRepository.save(module);
        Achievement persistedAc = achievementService.add(ac);

        Assertions.assertEquals(expectedAc, persistedAc);
    }
}