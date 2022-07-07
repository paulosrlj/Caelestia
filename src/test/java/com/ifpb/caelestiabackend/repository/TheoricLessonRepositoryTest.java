package com.ifpb.caelestiabackend.repository;

import com.ifpb.caelestiabackend.domain.entities.TheoricLesson;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;


@DataJpaTest
@DisplayName("Theoric Lesson repository tests")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class TheoricLessonRepositoryTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(TheoricLessonRepositoryTest.class);

    @Autowired
    private TheoricLessonRepository theoricLessonRepository;

    @BeforeEach
    void setUp() {
    }

    private TheoricLesson makeTheoricLesson() {
        return TheoricLesson.builder()
                .lessonName("Introdução a astronomia")
                .description("Conteúdo da lição")
                .xpEarned(100L)
                .build();
    }

    @Test
    public void mustUpdateTheoricLesson() {
        TheoricLesson tc = makeTheoricLesson();
        tc.setLessonName("Nome de lição atualizado");

        TheoricLesson persistedLesson = theoricLessonRepository.save(tc);

        Assertions.assertThat(persistedLesson.getLessonName()).isEqualTo(tc.getLessonName());
    }
}