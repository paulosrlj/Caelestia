package com.ifpb.caelestiabackend.services.praticalLesson;

import com.ifpb.caelestiabackend.domain.entities.Module;
import com.ifpb.caelestiabackend.domain.entities.PraticalLesson.Answers;
import com.ifpb.caelestiabackend.domain.entities.PraticalLesson.PraticalLesson;
import com.ifpb.caelestiabackend.repository.ModuleRepository;
import com.ifpb.caelestiabackend.repository.PraticalLessonRepository;
import com.ifpb.caelestiabackend.util.ModuleFactory;
import com.ifpb.caelestiabackend.util.PraticalLessonFactory;
import org.assertj.core.api.Assertions;
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
        Module moduleOfLesson = ModuleFactory.makePersistedModule();
        PraticalLesson plWithExpectedParams = PraticalLessonFactory.makePraticalLesson();
        Module moduleWithId = new Module();
        moduleWithId.setId(moduleOfLesson.getId());
        plWithExpectedParams.setModule(moduleWithId);

        Mockito.when(moduleRepository.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.of(moduleOfLesson));

        PraticalLesson expectedPl = PraticalLessonFactory.makePersistedPraticalLesson();
        expectedPl.setModule(moduleOfLesson);

        System.out.println(plWithExpectedParams);
        Mockito.when(praticalLessonRepository.save(ArgumentMatchers.eq(plWithExpectedParams)))
                .thenReturn(expectedPl);

        PraticalLesson plPersisted = praticalLessonService.add(plWithExpectedParams);

        Assertions.assertThat(plPersisted).isEqualTo(expectedPl);
    }

    @Test
    public void shouldFindPraticalLessonById() {
        Mockito.when(praticalLessonRepository.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.ofNullable(PraticalLessonFactory.makePersistedPraticalLesson()));

        PraticalLesson plFound = praticalLessonService.getById(
                PraticalLessonFactory.makePersistedPraticalLesson().getId()
        );

        Mockito.verify(praticalLessonRepository).findById(ArgumentMatchers.anyLong());

        Assertions.assertThat(plFound.getId()).isNotNull();
        Assertions.assertThat(plFound.getId())
                .isEqualTo(PraticalLessonFactory.makePersistedPraticalLesson().getId());
    }

    @Test
    public void shouldCallDeleteByIdOnce() {
        Mockito.when(praticalLessonRepository.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.ofNullable(PraticalLessonFactory.makePersistedPraticalLesson()));

        Mockito.doNothing().when(praticalLessonRepository).deleteById(ArgumentMatchers.anyLong());

        praticalLessonService.delete(1L);

        Mockito.verify(praticalLessonRepository).deleteById(ArgumentMatchers.anyLong());
    }

    @Test
    public void shouldUpdateAPraticalLesson() {
        PraticalLesson plUpdated = PraticalLessonFactory.makePersistedPraticalLesson();
        plUpdated.setLessonName("Nome atualizado");
        Answers currentAnswers = plUpdated.getAnswers();
        currentAnswers.setAnswer1("Resposta 1 Atualizada");
        plUpdated.setAnswers(currentAnswers);

        PraticalLesson dataToBeUpdated = new PraticalLesson();
        dataToBeUpdated.setLessonName("Nome atualizado");
        Answers answersToBeUpdated = new Answers();
        answersToBeUpdated.setAnswer1("Resposta 1 Atualizada");
        dataToBeUpdated.setAnswers(answersToBeUpdated);

        Mockito.when(praticalLessonRepository.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.ofNullable(PraticalLessonFactory.makePersistedPraticalLesson()));
        Mockito.when(praticalLessonRepository.save(ArgumentMatchers.eq(plUpdated)))
                .thenReturn(plUpdated);

        PraticalLesson pl = praticalLessonService.update(1L, dataToBeUpdated);

        Assertions.assertThat(pl)
//                .usingRecursiveComparison()
//                .ignoringFields("updatedAt", "createdAt",
//                        "module.createdAt", "module.updatedAt")
                .isEqualTo(plUpdated);

    }
}