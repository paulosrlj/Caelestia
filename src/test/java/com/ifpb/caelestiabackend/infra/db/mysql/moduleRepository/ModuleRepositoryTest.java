package com.ifpb.caelestiabackend.infra.db.mysql.moduleRepository;

import com.ifpb.caelestiabackend.domain.entities.Module;
import com.ifpb.caelestiabackend.domain.entities.TheoricLesson;
import org.apache.logging.log4j.LogManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
@DisplayName("Module repository                                                        tests")
class ModuleRepositoryTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(ModuleRepositoryTest.class);

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private ModuleRepository moduleRepository;

    @BeforeEach
    void setUp() {
//        moduleRepository.deleteAll();
    }

    private TheoricLesson makeTheoricLesson() {
        return TheoricLesson.builder()
                .lessonName("Introdução a astronomia")
                .description("Conteúdo da lição")
                .xpEarned(100L)
                .build();
    }

    private Module makeModule() {
        return Module.builder()
                .name("Astronomia antiga")
                .qtyLessons(0)
                .build();
    }

    @Test
    public void ensureInjectedDependenciesAreNotNull() {
        Assertions.assertThat(testEntityManager).isNotNull();
        Assertions.assertThat(moduleRepository).isNotNull();
    }

    @Test
    public void mustSaveModuleInDatabase() {
        Module module = makeModule();

        Module persistedModule = moduleRepository.save(module);

        Assertions.assertThat(persistedModule.getName()).isEqualTo(module.getName());
        Assertions.assertThat(persistedModule.getQtyLessons()).isEqualTo(module.getQtyLessons());
        Assertions.assertThat(persistedModule.getId()).isNotNull();

        LOGGER.info(String.valueOf(moduleRepository.findAll()));

    }

    @Test
    public void mustSaveModuleWithTheoricLessonInDatabase() {
        TheoricLesson theoricLesson = makeTheoricLesson();
        Module module = makeModule();
        Set<TheoricLesson> lessons = new HashSet<>(Collections.singletonList(theoricLesson));
        module.setLessons(lessons);

        Module modulePersisted = moduleRepository.save(module);

        Assertions.assertThat(modulePersisted.getId()).isNotNull();
        Assertions.assertThat(modulePersisted.getLessons()).isEqualTo(lessons);

        LOGGER.info(String.valueOf(moduleRepository.findAll()));

    }

    @Test
    public void mustFindModuleById() {
        LOGGER.info(String.valueOf(moduleRepository.findAll()));
        TheoricLesson theoricLesson = makeTheoricLesson();
        Module module = makeModule();
        Set<TheoricLesson> lessons = new HashSet<>(Collections.singletonList(theoricLesson));
        module.setLessons(lessons);

        Module moduleInserted = moduleRepository.save(module);

        Optional<Module> moduleFound = moduleRepository.findById(moduleInserted.getId());

        Assertions.assertThat(moduleFound.get().getId()).isEqualTo(moduleInserted.getId());
        Assertions.assertThat(moduleFound.get().getLessons()).isEqualTo(lessons);
    }
}