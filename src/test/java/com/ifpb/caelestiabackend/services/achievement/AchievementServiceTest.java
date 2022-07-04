package com.ifpb.caelestiabackend.services.achievement;

import com.ifpb.caelestiabackend.domain.entities.Achievement.Achievement;
import com.ifpb.caelestiabackend.domain.entities.Achievement.BonusType;
import com.ifpb.caelestiabackend.domain.entities.Module;
import com.ifpb.caelestiabackend.repository.AchievementRepository;
import com.ifpb.caelestiabackend.repository.ModuleRepository;
import com.ifpb.caelestiabackend.util.AchievementFactory;
import com.ifpb.caelestiabackend.util.CopyNotNullProperties;
import com.ifpb.caelestiabackend.util.ModuleFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Achievement service tests")
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

    @Test
    public void shouldGetAchievementById() {
        Module module = ModuleFactory.makePersistedModule();
        Achievement ac = AchievementFactory.makeAchievement();
        Achievement fakePersistedAc = AchievementFactory.makePersistedAchievement();
        ac.setModule(module);
        fakePersistedAc.setModule(module);

        Mockito.when(achievementRepository.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.of(fakePersistedAc));

        Achievement foundAc = achievementService.getById(1L);

        Assertions.assertEquals(fakePersistedAc, foundAc);
    }

    @Test
    public void shouldUpdateAAchievement() {
        Module module = ModuleFactory.makePersistedModule();
        Achievement dataToUpdate = new Achievement();
        Achievement fakePersistedAc = AchievementFactory.makePersistedAchievement();
        fakePersistedAc.setModule(module);

        String newName = "Novo nome";
        BonusType newBonus = BonusType.PRATICAL_LESSON;

        dataToUpdate.setAchievementName(newName);
        dataToUpdate.setBonusType(newBonus);

        Achievement expectedAc = new Achievement();
        CopyNotNullProperties.copyNonNullProperties(fakePersistedAc, expectedAc);
        expectedAc.setAchievementName(newName);
        expectedAc.setBonusType(newBonus);

        Mockito.when(achievementRepository.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.of(fakePersistedAc));
        Mockito.when(achievementRepository.save(ArgumentMatchers.eq(expectedAc)))
                .thenReturn(expectedAc);

        Achievement resultAc = achievementService.update(1L, dataToUpdate);

        Assertions.assertEquals(expectedAc, resultAc);
    }

    @Test
    public void shouldCallDeleteByIdOnce() {
        Module module = ModuleFactory.makePersistedModule();
        Achievement ac = AchievementFactory.makePersistedAchievement();
        ac.setModule(module);

        Mockito.when(achievementRepository.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.of(ac));

        Mockito.doNothing().when(achievementRepository).deleteById(ArgumentMatchers.anyLong());

        achievementService.delete(1L);

        Mockito.verify(achievementRepository).deleteById(ArgumentMatchers.anyLong());
    }
}