package com.ifpb.caelestiabackend.controller.module;

import com.ifpb.caelestiabackend.domain.entities.Module;
import com.ifpb.caelestiabackend.repository.ModuleRepository;
import com.ifpb.caelestiabackend.services.module.ModuleService;
import com.ifpb.caelestiabackend.util.ModuleFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.assertj.core.api.Assertions;


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ModuleController.class)
class ModuleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ModuleService moduleService;

    @MockBean
    private ModuleRepository moduleRepository;

    @Test
    public void shouldReturn200IfValidDataIsProvided() throws Exception {
        Module expectedModule = ModuleFactory.makePersistedModuleWithTheoricLesson();

        Mockito.when(moduleService.add(ArgumentMatchers.any(Module.class))).thenReturn(expectedModule);
        Mockito.when(moduleRepository.save(ArgumentMatchers.any(Module.class))).thenReturn(expectedModule);

        RequestBuilder request = MockMvcRequestBuilders
                .post("/module/")
                .accept(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Astronomia antiga\"," +
                        "\"theoricLessons\": [" +
                        "\"xpEarned\": 100, \"lessonName\": \"Introdução a astronomia\", " +
                        "\"description\": \"Conteúdo da lição\"" +
                        "]")
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(status().isOk());
    }

}