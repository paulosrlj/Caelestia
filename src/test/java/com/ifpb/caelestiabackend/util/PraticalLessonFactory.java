package com.ifpb.caelestiabackend.util;

import com.ifpb.caelestiabackend.domain.entities.PraticalLesson.Answers;
import com.ifpb.caelestiabackend.domain.entities.PraticalLesson.PraticalLesson;

public class PraticalLessonFactory {
    public static PraticalLesson makePraticalLesson() {
        Answers answers = new Answers();
        answers.setAnswer1("Resposta 1");
        answers.setAnswer2("Resposta 2");
        answers.setAnswer3("Resposta 3");
        answers.setAnswer4("Resposta 4");
        answers.setCorrectAnswer(2L);

        return PraticalLesson.builder()
                .lessonName("Lição prática 1")
                .description("Quantos planetas existem no sistema solar?")
                .xpEarned(150L)
                .answers(answers)
                .build();
    }
}
