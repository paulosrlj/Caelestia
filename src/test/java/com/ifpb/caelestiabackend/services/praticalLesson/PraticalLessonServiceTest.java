package com.ifpb.caelestiabackend.services.praticalLesson;

import com.ifpb.caelestiabackend.domain.entities.Module;
import com.ifpb.caelestiabackend.domain.entities.PraticalLesson.PraticalLesson;
import com.ifpb.caelestiabackend.repository.ModuleRepository;
import com.ifpb.caelestiabackend.repository.PraticalLessonRepository;
import com.ifpb.caelestiabackend.util.ModuleFactory;
import com.ifpb.caelestiabackend.util.PraticalLessonFactory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Pratical Lesson tests")
class PraticalLessonServiceTest {

    @Autowired
    private PraticalLessonService praticalLessonService;

    @MockBean
    private PraticalLessonRepository praticalLessonRepository;

    @MockBean
    private ModuleRepository moduleRepository;

    @Test
    public void shouldAddAPraticalLesson() {
        Module module = ModuleFactory.makeModule();
        PraticalLesson pl = PraticalLessonFactory.makePraticalLesson();

        Mockito.when(moduleRepository.save(ArgumentMatchers.any(Module.class)))
                .thenReturn(ModuleFactory.makePersistedModule());
        Mockito.when(praticalLessonRepository.save(ArgumentMatchers.any(PraticalLesson.class)))
                .thenReturn(PraticalLessonFactory.makePersistedPraticalLesson());

        Module persistedModule = moduleRepository.save(module);
        pl.setModule(persistedModule);

        PraticalLesson plPersisted = praticalLessonService.add(pl);

        Assertions.assertThat(plPersisted.getId()).isNotNull();
        Assertions.assertThat(plPersisted.getCreatedAt()).isNotNull();
        Assertions.assertThat(plPersisted.getUpdatedAt()).isNotNull();
    }

    @Test
    public void shouldFindPraticalLessonById() {
        Mockito.when(praticalLessonRepository.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.ofNullable(PraticalLessonFactory.makePersistedPraticalLesson()));

        PraticalLesson plFound = praticalLessonService.getById(
                PraticalLessonFactory.makePersistedPraticalLesson().getId()
        );

        Assertions.assertThat(plFound.getId()).isNotNull();
        Assertions.assertThat(plFound.getId())
                .isEqualTo(PraticalLessonFactory.makePersistedPraticalLesson().getId());
    }
}