package com.ifpb.caelestiabackend.util;

import com.ifpb.caelestiabackend.domain.entities.PraticalLesson.Answers;
import com.ifpb.caelestiabackend.domain.entities.PraticalLesson.PraticalLesson;
import com.ifpb.caelestiabackend.util.factories.PraticalLessonFactory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CopyNotNullPropertiesTest {

    @Test
    public void shouldCopyNotNullPropertiesOfSourceToDestinationObject() {
        PraticalLesson pl = new PraticalLesson();
        pl.setLessonName("Nome de lição atualizado");
        Answers answers = new Answers();
        answers.setAnswer1("Resposta 1 atualizada");

        pl.setAnswers(answers);

        PraticalLesson persistedPl = PraticalLessonFactory.makePersistedPraticalLesson();
        PraticalLesson expectedPl = PraticalLessonFactory.makePersistedPraticalLesson();
        expectedPl.setLessonName("Nome de lição atualizado");
        expectedPl.getAnswers().setAnswer1("Resposta 1 atualizada");

        var newAnswers = persistedPl.getAnswers();

        CopyNotNullProperties.copyNonNullProperties(pl.getAnswers(), newAnswers);
        pl.setAnswers(newAnswers);
        CopyNotNullProperties.copyNonNullProperties(pl, persistedPl);

        Assertions.assertThat(persistedPl.getLessonName()).isEqualTo(expectedPl.getLessonName());
        Assertions.assertThat(persistedPl.getAnswers()).isEqualTo(expectedPl.getAnswers());
    }

}