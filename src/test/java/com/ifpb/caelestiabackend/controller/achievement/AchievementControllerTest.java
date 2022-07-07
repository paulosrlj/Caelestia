package com.ifpb.caelestiabackend.controller.achievement;

import com.ifpb.caelestiabackend.domain.entities.Achievement.Achievement;
import com.ifpb.caelestiabackend.domain.entities.Module;
import com.ifpb.caelestiabackend.repository.AchievementRepository;
import com.ifpb.caelestiabackend.services.achievement.AchievementService;
import com.ifpb.caelestiabackend.util.AchievementFactory;
import com.ifpb.caelestiabackend.util.ModuleFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(IAchievementController.class)
@DisplayName("AchievementController tests")
class AchievementControllerTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(AchievementControllerTest.class);

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AchievementService achievementService;

    @MockBean
    private AchievementRepository achievementRepository;

    @Test
    public void shouldReturn200IfValidDataIsProvided() throws Exception {
        Module module = new Module();
        module.setId(1L);
        Achievement acToSave = AchievementFactory.makeAchievement();
        Achievement acPersisted = AchievementFactory.makePersistedAchievement();
        acToSave.setModule(module);
        acPersisted.setModule(module);

        System.out.println(acToSave);
        Mockito.when(achievementService.add(ArgumentMatchers.eq(acToSave))).thenReturn(acPersisted);
        Mockito.when(achievementRepository.save(ArgumentMatchers.any(Achievement.class)))
                .thenReturn(acPersisted);

        RequestBuilder request = MockMvcRequestBuilders
                .post("/achievement/")
                .accept(MediaType.APPLICATION_JSON)
                .content("{" +
                        "\"achievementName\":\"Observador Celeste\"," +
                        "\"description\": \"+5% de xp em lições do tipo teórica\"," +
                        "\"baseBonusPercentage\": 0.5," +
                        "\"bonusType\": \"THEORIC_LESSON\"," +
                        "\"urlImage\": \"https://urlqualquer.com.br\"," +
                        "\"module\": {\"id\":1}" +
                        "}")
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturnCorrectData() throws Exception {
        Module module = new Module();
        module.setId(1L);
        Achievement acToSave = AchievementFactory.makeAchievement();
        Module moduleToReturn = new Module();
        moduleToReturn.setId(1L);
        moduleToReturn.setName("Modulo qualquer");
        Achievement acPersisted = AchievementFactory.makePersistedAchievement();
        acToSave.setModule(module);
        acPersisted.setModule(moduleToReturn);

        System.out.println(acToSave);
        Mockito.when(achievementService.add(ArgumentMatchers.eq(acToSave))).thenReturn(acPersisted);
        Mockito.when(achievementRepository.save(ArgumentMatchers.any(Achievement.class)))
                .thenReturn(acPersisted);

        RequestBuilder request = MockMvcRequestBuilders
                .post("/achievement/")
                .accept(MediaType.APPLICATION_JSON)
                .content("{" +
                        "\"achievementName\":\"Observador Celeste\"," +
                        "\"description\": \"+5% de xp em lições do tipo teórica\"," +
                        "\"baseBonusPercentage\": 0.5," +
                        "\"bonusType\": \"THEORIC_LESSON\"," +
                        "\"urlImage\": \"https://urlqualquer.com.br\"," +
                        "\"module\": {\"id\":1}" +
                        "}")
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(request)
                .andExpect(status().isOk())
                .andReturn();

        Assertions.assertEquals(
                "{" +
                        "\"id\":1," +
                        "\"achievementName\":\"Observador Celeste\"," +
                        "\"description\":\"+5% de xp em lições do tipo teórica\"," +
                        "\"baseBonusPercentage\":0.5," +
                        "\"bonusType\":\"THEORIC_LESSON\"," +
                        "\"urlImage\":\"https://urlqualquer.com.br\"," +
                        "\"module\":{\"id\":1,\"name\":\"Modulo qualquer\"}" +
                        "}", result.getResponse().getContentAsString()
        );
    }
}