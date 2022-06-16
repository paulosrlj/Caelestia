package com.ifpb.caelestiabackend.services.module;

import com.ifpb.caelestiabackend.domain.entities.Module;
import com.ifpb.caelestiabackend.domain.entities.TheoricLesson;
import com.ifpb.caelestiabackend.repository.ModuleRepository;
import com.ifpb.caelestiabackend.util.ModuleFactory;
import com.ifpb.caelestiabackend.util.TheoricLessonFactory;
import org.assertj.core.api.Assertions;
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
@DisplayName("Add Module tests")
class AddModuleServiceTest {

    @MockBean
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

    @Test
    public void shouldCallRepositorySaveWithCorrectValues() {
        Module module = ModuleFactory.makeModule();
        TheoricLesson tc = TheoricLessonFactory.makeTheoricLesson();
        module.addTheoricLesson(tc);

        Module expectedModule = ModuleFactory.makePersistedModuleWithTheoricLesson();

        Mockito.when(moduleRepository.save(ArgumentMatchers.eq(module))).thenReturn(expectedModule);
        Module resultModule = addModuleService.add(module);

        Mockito.verify(moduleRepository, Mockito.times(1))
                .save(ArgumentMatchers.eq(module));
        Assertions.assertThat(resultModule.getId()).isNotNull();
    }
}