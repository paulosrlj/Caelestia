package com.ifpb.caelestiabackend.repository;

import com.ifpb.caelestiabackend.domain.entities.Module;
import com.ifpb.caelestiabackend.domain.entities.PraticalLesson.PraticalLesson;
import com.ifpb.caelestiabackend.util.ModuleFactory;
import com.ifpb.caelestiabackend.util.PraticalLessonFactory;
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
@DisplayName("Pratical Lesson repository tests")
class PraticalLessonRepositoryTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(PraticalLessonRepositoryTest.class);

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private PraticalLessonRepository praticalLessonRepository;

    @Autowired
    private ModuleRepository moduleRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    public void mustSaveAPraticalLesson() {
        PraticalLesson pl = PraticalLessonFactory.makePraticalLesson();

        Module module = ModuleFactory.makeModule();
        Module persistedModule = moduleRepository.save(module);

        pl.setModule(persistedModule);
        PraticalLesson persistedPl = praticalLessonRepository.save(pl);

        Assertions.assertThat(persistedPl.getId()).isNotNull();
        Assertions.assertThat(persistedPl.getModule().getId()).isEqualTo(persistedModule.getId());
    }

    @Test
    public void mustFindAPraticalLessonById() {
        PraticalLesson pl = PraticalLessonFactory.makePraticalLesson();
        Module module = ModuleFactory.makeModule();
        Module persistedModule = moduleRepository.save(module);
        pl.setModule(persistedModule);
        PraticalLesson persistedPl = praticalLessonRepository.save(pl);

        Optional<PraticalLesson> praticalLessonFound = praticalLessonRepository.findById(persistedPl.getId());

        Assertions.assertThat(praticalLessonFound.get().getId())
                .isEqualTo(persistedPl.getId());
    }


}