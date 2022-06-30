package com.ifpb.caelestiabackend.controller.praticalLesson;

import com.ifpb.caelestiabackend.domain.entities.Module;
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

import java.util.Optional;

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
        PraticalLesson expectedPl = PraticalLessonFactory.makePersistedPraticalLesson();

        Mockito.when(praticalLessonService.add(ArgumentMatchers.any(PraticalLesson.class)))
                .thenReturn(expectedPl);

        Mockito.when(praticalLessonRepository.save(ArgumentMatchers.any(PraticalLesson.class)))
                .thenReturn(expectedPl);

        RequestBuilder request = MockMvcRequestBuilders
                .post("/pratical-lesson/")
                .accept(MediaType.APPLICATION_JSON)
                .content("{" +
                        "\"lessonName\": \"Nome qualquer\",\n" +
                        "\t\"description\": \"Descafisaifas\",\n" +
                        "\t\"xpEarned\": 22,\n" +
                        "\t\"answers\": {\n" +
                        "\t\t\"answer1\": \"Resposta1\",\n" +
                        "\t\t\"answer2\": \"Resposta2\",\n" +
                        "\t\t\"answer3\": \"Resposta3\",\n" +
                        "\t\t\"answer4\": \"Resposta4\",\n" +
                        "\t\t\"correctAnswer\": 2\n" +
                        "\t}}")
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
        Mockito.when(praticalLessonRepository.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.of(expectedPl));

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
                        "\"answersImages\":null},\"module\":\"Astronomia antiga\"}",
                result.getResponse().getContentAsString()
        );

    }
}