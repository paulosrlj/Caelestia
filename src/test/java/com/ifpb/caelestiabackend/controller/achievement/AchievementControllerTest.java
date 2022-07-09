package com.ifpb.caelestiabackend.controller.achievement;

import com.ifpb.caelestiabackend.domain.entities.Achievement.Achievement;
import com.ifpb.caelestiabackend.domain.entities.Achievement.BonusType;
import com.ifpb.caelestiabackend.domain.entities.Module;
import com.ifpb.caelestiabackend.exception.achievement.InvalidUrlImage;
import com.ifpb.caelestiabackend.repository.AchievementRepository;
import com.ifpb.caelestiabackend.services.achievement.AchievementService;
import com.ifpb.caelestiabackend.util.AchievementFactory;
import com.ifpb.caelestiabackend.util.ModuleFactory;
import lombok.extern.log4j.Log4j2;
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
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.io.UnsupportedEncodingException;
import java.util.Optional;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(IAchievementController.class)
@DisplayName("AchievementController tests")
@Log4j2
class AchievementControllerTest {

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
    public void shouldThrowExceptionIfUrlImageIsInvalid() throws Exception {
        Module module = new Module();
        module.setId(1L);
        Achievement acToSave = AchievementFactory.makeAchievement();
        acToSave.setModule(module);

        RequestBuilder request = MockMvcRequestBuilders
                .post("/achievement/")
                .accept(MediaType.APPLICATION_JSON)
                .content("{" +
                        "\"achievementName\":\"Observador Celeste\"," +
                        "\"description\": \"+5% de xp em lições do tipo teórica\"," +
                        "\"baseBonusPercentage\": 0.5," +
                        "\"bonusType\": \"THEORIC_LESSON\"," +
                        "\"urlImage\": \"urlqualquer.com.br\"," +
                        "\"module\": {\"id\":1}" +
                        "}")
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(status().isBadRequest())
                .andExpect(result -> Assertions.assertTrue(result.getResolvedException()
                        instanceof MethodArgumentNotValidException));
    }

    @Test
    public void shouldThrowExceptionIfBonusTypeIsInvalid() throws Exception {
        Module module = new Module();
        module.setId(1L);
        Achievement acToSave = AchievementFactory.makeAchievement();
        acToSave.setModule(module);

        RequestBuilder request = MockMvcRequestBuilders
                .post("/achievement/")
                .accept(MediaType.APPLICATION_JSON)
                .content("{" +
                        "\"achievementName\":\"Observador Celeste\"," +
                        "\"description\": \"+5% de xp em lições do tipo teórica\"," +
                        "\"baseBonusPercentage\": 0.5," +
                        "\"bonusType\": \"THEORIC_LLESSON\"," +
                        "\"urlImage\": \"https://urlqualquer.com.br\"," +
                        "\"module\": {\"id\":1}" +
                        "}")
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(status().isBadRequest())
                .andExpect(result -> Assertions.assertTrue(result.getResolvedException()
                        instanceof HttpMessageNotReadableException));
    }

    @Test
    public void shouldThrowExceptionIfNoModuleIsProvided() throws Exception {
        Module module = new Module();
        module.setId(1L);
        Achievement acToSave = AchievementFactory.makeAchievement();
        acToSave.setModule(module);

        RequestBuilder request = MockMvcRequestBuilders
                .post("/achievement/")
                .accept(MediaType.APPLICATION_JSON)
                .content("{" +
                        "\"achievementName\":\"Observador Celeste\"," +
                        "\"description\": \"+5% de xp em lições do tipo teórica\"," +
                        "\"baseBonusPercentage\": 0.5," +
                        "\"bonusType\": \"THEORIC_LESSON\"," +
                        "\"urlImage\": \"https://urlqualquer.com.br\"" +
                        "}")
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(status().isBadRequest())
                .andExpect(result -> Assertions.assertTrue(result.getResolvedException()
                        instanceof MethodArgumentNotValidException));
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

    @Test
    public void shouldReturnAchievementIfExists() throws Exception {
        Module expectedModule = ModuleFactory.makePersistedModuleWithTheoricLesson();
        Achievement expectedAc = AchievementFactory.makePersistedAchievement();
        expectedAc.setModule(expectedModule);

        Mockito.when(achievementService.getById(ArgumentMatchers.anyLong())).thenReturn(expectedAc);

        RequestBuilder request = MockMvcRequestBuilders
                .get("/achievement/{id}", 1L)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(request)
                .andExpect(status().isOk())
                .andReturn();

        Assertions.assertEquals(
                "{\"id\":1,\"achievementName\":\"Observador Celeste\"," +
                        "\"description\":\"+5% de xp em lições do tipo teórica\"," +
                        "\"baseBonusPercentage\":0.5," +
                        "\"bonusType\":\"THEORIC_LESSON\"," +
                        "\"urlImage\":\"https://urlqualquer.com.br\"," +
                        "\"module\":{\"id\":1,\"name\":\"Astronomia antiga\"}}",
                result.getResponse().getContentAsString()
        );
    }

    @Test
    public void shouldUpdateAndReturnAAchievement() throws Exception {
        Achievement acToUpdate = new Achievement();
        acToUpdate.setAchievementName("Novo nome da conquista");
        acToUpdate.setBonusType(BonusType.PRATICAL_LESSON);

        Module expectedModuleOfAc = ModuleFactory.makePersistedModule();
        Achievement expectedAc = AchievementFactory.makePersistedAchievement();
        expectedAc.setAchievementName("Novo nome da conquista");
        expectedAc.setBonusType(BonusType.PRATICAL_LESSON);
        expectedAc.setModule(expectedModuleOfAc);

        Mockito.when(achievementService.update(ArgumentMatchers.anyLong(), ArgumentMatchers.eq(acToUpdate)))
                .thenReturn(expectedAc);

        log.info("Before Request");
        log.info(acToUpdate);
        RequestBuilder request = MockMvcRequestBuilders
                .put("/achievement/{id}", 1L)
                .accept(MediaType.APPLICATION_JSON)
                .content("{" +
                        "\"achievementName\":\"Novo nome da conquista\"," +
                        "\"bonusType\":\"PRATICAL_LESSON\"" +
                        "}")
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(request)
                .andExpect(status().isOk())
                .andReturn();

        Assertions.assertEquals("{\"id\":1,\"achievementName\":\"Novo nome da conquista\"," +
                        "\"description\":\"+5% de xp em lições do tipo teórica\"," +
                        "\"baseBonusPercentage\":0.5," +
                        "\"bonusType\":\"PRATICAL_LESSON\"," +
                        "\"urlImage\":\"https://urlqualquer.com.br\"," +
                        "\"module\":{\"id\":1,\"name\":\"Astronomia antiga\"}}"
                ,result.getResponse().getContentAsString()
        );
    }

    @Test
    public void shouldThrowExceptionIfTryToUpdateWithInvalidImageUrl() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders
                .put("/achievement/{id}", 1L)
                .accept(MediaType.APPLICATION_JSON)
                .content("{" +
                        "\"achievementName\":\"Novo nome da conquista\"," +
                        "\"bonusType\":\"PRATICAL_LESSON\"," +
                        "\"urlImage\":\"//urlqualquer.com.br\"" +
                        "}")
                .contentType(MediaType.APPLICATION_JSON);


        mockMvc.perform(request)
                .andExpect(status().isBadRequest())
                .andExpect(result -> Assertions.assertTrue(result.getResolvedException()
                        instanceof InvalidUrlImage));

    }
}