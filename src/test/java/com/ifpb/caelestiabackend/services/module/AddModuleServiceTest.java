package com.ifpb.caelestiabackend.services.module;

import com.ifpb.caelestiabackend.domain.entities.Module;
import com.ifpb.caelestiabackend.repository.ModuleRepository;
import com.ifpb.caelestiabackend.util.ModuleFactory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@AutoConfigureMockMvc
class AddModuleServiceTest {

    @Mock
    private ModuleRepository moduleRepository;

    @Autowired
    private AddModuleService addModuleService;

    @Test
    public void shouldAddAModule() {
        Module module = ModuleFactory.makeModule();
        Module expectedModule = ModuleFactory.makePersistedModule();

        Mockito.when(moduleRepository.save(ArgumentMatchers.eq(module))).thenReturn(expectedModule);

        Module resultModule = addModuleService.add(module);

        Assertions.assertThat(resultModule).isEqualTo(expectedModule);
    }
}