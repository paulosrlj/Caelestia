package com.ifpb.caelestiabackend.controller.module;

import com.ifpb.caelestiabackend.domain.entities.Module;
import com.ifpb.caelestiabackend.repository.ModuleRepository;
import com.ifpb.caelestiabackend.services.module.ModuleService;
import com.ifpb.caelestiabackend.util.ModuleFactory;
import org.junit.jupiter.api.Assertions;
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

import javax.persistence.EntityNotFoundException;

import java.util.Optional;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(IModuleController.class)
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
                .content("{\"name\":\"Astronomia antiga\"}")
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(status().isOk());
    }

    @Test
    public void shouldUpdateAndReturnAModule() throws Exception {
        Module expectedModule = ModuleFactory.makePersistedModuleWithTheoricLesson();
        expectedModule.setName("Astronomia antiga atualizado");
        Mockito.when(moduleService.update(ArgumentMatchers.anyLong(), ArgumentMatchers.any(Module.class)))
                .thenReturn(expectedModule);
        Mockito.when(moduleRepository.save(ArgumentMatchers.any(Module.class))).thenReturn(expectedModule);

        RequestBuilder request = MockMvcRequestBuilders
                .put("/module/{id}", 1L)
                .accept(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Astronomia antiga atualizado\"}")
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(request)
                .andExpect(status().isOk())
                .andReturn();

        Assertions.assertEquals("{\"id\":1,\"name\":\"Astronomia antiga atualizado\"}"
                ,result.getResponse().getContentAsString()
        );
    }

    @Test
    public void shouldReturnModuleIfExists() throws Exception {
        Module expectedModule = ModuleFactory.makePersistedModuleWithTheoricLesson();

        Mockito.when(moduleService.getById(ArgumentMatchers.anyLong())).thenReturn(expectedModule);
        Mockito.when(moduleRepository.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.of(expectedModule));

        RequestBuilder request = MockMvcRequestBuilders
                .get("/module/{id}", 1L)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(request)
                .andExpect(status().isOk())
                .andReturn();

        Assertions.assertEquals(
                "{\"id\":1,\"name\":\"Astronomia antiga\"," +
                        "\"theoricLessons\":" +
                        "[{\"id\":null,\"xpEarned\":100," +
                        "\"lessonName\":\"Introdução a astronomia\"," +
                        "\"description\":\"Conteúdo da lição\"}]}",
                result.getResponse().getContentAsString()
        );
    }

    @Test
    public void shouldReturn204IfModuleIsDeleted() throws Exception {
        Mockito.doNothing().when(moduleService).delete(ArgumentMatchers.anyLong());
        Mockito.doNothing().when(moduleRepository).deleteById(ArgumentMatchers.anyLong());

        RequestBuilder request = MockMvcRequestBuilders
                .delete("/module/{id}", 1L)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldThrowExceptionOnDeleteInANotExistingModule() throws Exception {
        Mockito.doThrow(EntityNotFoundException.class)
                .when(moduleService).delete(ArgumentMatchers.anyLong());

        RequestBuilder request = MockMvcRequestBuilders
                .delete("/module/{id}", 1L)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(status().isNotFound())
                .andExpect(result -> Assertions.assertTrue(result.getResolvedException()
                        instanceof EntityNotFoundException));
    }

    @Test
    public void shouldReturn400IfModuleNameIsNotProvided() throws Exception {
        Module expectedModule = ModuleFactory.makePersistedModuleWithTheoricLesson();

        Mockito.when(moduleService.add(ArgumentMatchers.any(Module.class))).thenReturn(expectedModule);
        Mockito.when(moduleRepository.save(ArgumentMatchers.any(Module.class))).thenReturn(expectedModule);

        RequestBuilder request = MockMvcRequestBuilders
                .post("/module/")
                .accept(MediaType.APPLICATION_JSON)
                .content("")
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(status().isBadRequest());
    }
}