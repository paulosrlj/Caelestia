package com.ifpb.caelestiabackend.repository;

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
@DisplayName("Module repository tests")
class ModuleRepositoryTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(ModuleRepositoryTest.class);

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private ModuleRepository moduleRepository;

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

    private Module makeModule() {
        return Module.builder()
                .name("Astronomia antiga")
                .qtyLessons(0)
                .build();
    }

    private Module persistModuleWithLessons() {
        Module module = makeModule();
        TheoricLesson theoricLesson = makeTheoricLesson();
        module.addTheoricLesson(theoricLesson);
        theoricLesson.setModule(module);

        return moduleRepository.save(module);
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
    }

    @Test
    public void mustSaveModuleWithTheoricLesson() {
        TheoricLesson theoricLesson = makeTheoricLesson();
        Module module = makeModule();
        module.addTheoricLesson(theoricLesson);
        theoricLesson.setModule(module);

        Module modulePersisted = moduleRepository.save(module);

        Assertions.assertThat(modulePersisted.getId()).isNotNull();
        Assertions.assertThat(modulePersisted.getTheoricLessons()).isNotNull();
        Assertions.assertThat(modulePersisted.getQtyLessons()).isEqualTo(1);
    }

    @Test
    public void mustUpdateModuleInDatabase() {
        Module module = makeModule();

        Module persistedModule = moduleRepository.save(module);
        persistedModule.setName("New name");

        Module updatedModule = moduleRepository.save(persistedModule);

        Assertions.assertThat(updatedModule.getName()).isEqualTo(persistedModule.getName());
        Assertions.assertThat(persistedModule.getId()).isNotNull();

        LOGGER.info(String.valueOf(moduleRepository.findAll()));
    }

    @Test
    public void mustFindModuleById() {
        Module persistedModule = persistModuleWithLessons();

        Optional<Module> moduleFound = moduleRepository.findById(persistedModule.getId());

        Assertions.assertThat(moduleFound).get().isEqualTo(persistedModule);
    }

}