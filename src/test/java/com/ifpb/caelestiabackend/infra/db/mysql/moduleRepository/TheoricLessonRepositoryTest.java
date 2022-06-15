package com.ifpb.caelestiabackend.infra.db.mysql.moduleRepository;

import com.ifpb.caelestiabackend.domain.entities.Module;
import com.ifpb.caelestiabackend.domain.entities.TheoricLesson;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;


@DataJpaTest
@DisplayName("Theoric Lesson repository tests")
class TheoricLessonRepositoryTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(TheoricLessonRepositoryTest.class);

    @Autowired
    private TestEntityManager testEntityManager;

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