package com.ifpb.caelestiabackend.util;

import com.ifpb.caelestiabackend.domain.entities.TheoricLesson;

public class TheoricLessonFactory {
    public static TheoricLesson makeTheoricLesson() {
        return TheoricLesson.builder()
                .lessonName("Introdução a astronomia")
                .description("Conteúdo da lição")
                .xpEarned(100L)
                .build();
    }
}
