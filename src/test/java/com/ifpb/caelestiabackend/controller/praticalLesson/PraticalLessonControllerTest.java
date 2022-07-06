package com.ifpb.caelestiabackend.controller.praticalLesson;

import com.ifpb.caelestiabackend.domain.entities.Module;
import com.ifpb.caelestiabackend.domain.entities.PraticalLesson.Answers;
import com.ifpb.caelestiabackend.domain.entities.PraticalLesson.PraticalLesson;
import com.ifpb.caelestiabackend.repository.PraticalLessonRepository;
import com.ifpb.caelestiabackend.services.praticalLesson.PraticalLessonService;
import com.ifpb.caelestiabackend.util.ModuleFactory;
import com.ifpb.caelestiabackend.util.PraticalLessonFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.bind.MethodArgumentNotValidException;

import javax.persistence.EntityNotFoundException;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(IPraticalLessonController.class)
@DisplayName("PraticalController tests")
class PraticalLessonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PraticalLessonService praticalLessonService;

    @MockBean
    private PraticalLessonRepository praticalLessonRepository;

    @Test
    public void shouldReturn200IfValidDataIsProvided() throws Exception {
        PraticalLesson validData = PraticalLessonFactory.makePraticalLesson();
        Module module = new Module();
        module.setId(1L);
        validData.setModule(module);

        PraticalLesson expectedPl = PraticalLessonFactory.makePersistedPraticalLesson();
        Module expectedModule = ModuleFactory.makePersistedModule();
        expectedPl.setModule(expectedModule);

        Mockito.when(praticalLessonService.add(ArgumentMatchers.eq(validData)))
                .thenReturn(expectedPl);

        RequestBuilder request = MockMvcRequestBuilders
                .post("/pratical-lesson/")
                .accept(MediaType.APPLICATION_JSON)
                .content("""
                        {"module": {
                        \t\t"id": 1
                        \t},"lessonName": "Lição prática 1",
                        \t"description": "Quantos planetas existem no sistema solar?",
                        \t"xpEarned": 150,
                        \t"answers": {
                        \t\t"answer1": "Resposta 1",
                        \t\t"answer2": "Resposta 2",
                        \t\t"answer3": "Resposta 3",
                        \t\t"answer4": "Resposta 4",
                        \t\t"correctAnswer": 2
                        \t}}""")
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturnModuleIfExists() throws Exception {
        PraticalLesson expectedPl = PraticalLessonFactory.makePersistedPraticalLesson();
        Module module = ModuleFactory.makePersistedModule();
        expectedPl.setModule(module);

        Mockito.when(praticalLessonService.getById(ArgumentMatchers.anyLong())).thenReturn(expectedPl);

        RequestBuilder request = MockMvcRequestBuilders
                .get("/pratical-lesson/{id}", 1L)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(request)
                .andExpect(status().isOk())
                .andReturn();

        Assertions.assertEquals(
                "{\"id\":1,\"lessonName\":\"Lição prática 1\"," +
                        "\"description\":\"Quantos planetas existem no sistema solar?\"," +
                        "\"xpEarned\":150,\"answers\":{\"answer1\":\"Resposta 1\"" +
                        ",\"answer2\":\"Resposta 2\",\"answer3\":\"Resposta 3\"," +
                        "\"answer4\":\"Resposta 4\",\"correctAnswer\":2," +
                        "\"answersImages\":null},\"module\":{\"id\":1,\"name\":\"Astronomia antiga\"}}",
                result.getResponse().getContentAsString()
        );
    }

    @Test
    public void shouldReturn204IfModuleIsDeleted() throws Exception {
        Mockito.doNothing().when(praticalLessonService).delete(ArgumentMatchers.anyLong());
        Mockito.doNothing().when(praticalLessonRepository).deleteById(ArgumentMatchers.anyLong());

        RequestBuilder request = MockMvcRequestBuilders
                .delete("/pratical-lesson/{id}", 1L)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldThrowExceptionOnDeleteInANotExistingPraticalLesson() throws Exception {
        Mockito.doThrow(EntityNotFoundException.class)
                .when(praticalLessonService).delete(ArgumentMatchers.anyLong());

        RequestBuilder request = MockMvcRequestBuilders
                .delete("/pratical-lesson/{id}", 1L)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(status().isNotFound())
                .andExpect(result -> Assertions.assertTrue(result.getResolvedException()
                        instanceof EntityNotFoundException));
    }

    @Test
    public void shouldUpdateAndReturnAPraticalLesson() throws Exception {
        PraticalLesson dataToBeUpdated = new PraticalLesson();
        dataToBeUpdated.setLessonName("Nome atualizado");
        Answers answersToBeUpdated = new Answers();
        answersToBeUpdated.setAnswer1("Resposta 1 Atualizada");
        dataToBeUpdated.setAnswers(answersToBeUpdated);

        PraticalLesson expectedPl = PraticalLessonFactory.makePersistedPraticalLesson();
        expectedPl.setLessonName("Nome atualizado");
        Answers currentAnswers = expectedPl.getAnswers();
        currentAnswers.setAnswer1("Resposta 1 Atualizada");
        expectedPl.setAnswers(currentAnswers);

        Mockito.when(praticalLessonService.update(ArgumentMatchers.anyLong(),
                        ArgumentMatchers.eq(dataToBeUpdated)))
                .thenReturn(expectedPl);
        Mockito.when(praticalLessonRepository.save(ArgumentMatchers.eq(expectedPl)))
                .thenReturn(expectedPl);

        RequestBuilder request = MockMvcRequestBuilders
                .put("/pratical-lesson/{id}", 1L)
                .accept(MediaType.APPLICATION_JSON)
                .content("""
                        {"lessonName": "Nome atualizado",
                        \t"answers": {
                        \t\t"answer1": "Resposta 1 Atualizada"
                        \t}
                        }""")
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(request)
                .andExpect(status().isOk())
                .andReturn();

        Assertions.assertEquals("{\"id\":1," +
                        "\"lessonName\":\"Nome atualizado\"," +
                        "\"xpEarned\":150," +
                        "\"answers\":{" +
                        "\"answer1\":\"Resposta 1 Atualizada\"," +
                        "\"answer2\":\"Resposta 2\"," +
                        "\"answer3\":\"Resposta 3\"," +
                        "\"answer4\":\"Resposta 4\"," +
                        "\"correctAnswer\":2," +
                        "\"answersImages\":null" +
                        "}}"
                , result.getResponse().getContentAsString()
        );
    }

    @Test
    public void shouldThrowExceptionIfNoValidDataIsProvided() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders
                .post("/pratical-lesson/")
                .accept(MediaType.APPLICATION_JSON)
                .content("""
                        {"lessonName": "Lição prática 1",
                        \t"description": "Quantos planetas existem no sistema solar?",
                        \t"xpEarned": 150,
                        \t"answers": {
                        \t\t"answer1": "Resposta 1",
                        \t\t"answer2": "Resposta 2",
                        \t\t"answer3": "Resposta 3",
                        \t\t"answer4": "Resposta 4",
                        \t\t"correctAnswer": 2
                        \t}}""")
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(status().isBadRequest())
                .andExpect(result -> Assertions.assertTrue(result.getResolvedException()
                        instanceof MethodArgumentNotValidException));
    }
}