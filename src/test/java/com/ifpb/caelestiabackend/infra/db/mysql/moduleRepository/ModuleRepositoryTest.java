package com.ifpb.caelestiabackend.infra.db.mysql.moduleRepository;

import com.ifpb.caelestiabackend.domain.entities.Module;
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

import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
@DisplayName("Module repository tests")
class ModuleRepositoryTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(ModuleRepositoryTest.class);

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private ModuleRepository moduleRepository;

    @BeforeEach
    void setUp() {

    }

    @Test
    public void ensureInjectedDependenciesAreNotNull() {
        Assertions.assertThat(testEntityManager).isNotNull();
        Assertions.assertThat(moduleRepository).isNotNull();
    }

    @Test
    public void mustSaveModuleInDatabase() {
        Module module = Module.builder()
                .name("Astronomia antiga")
                .qtyLessons(0)
                .build();

        Module persistedModule = testEntityManager.persist(module);

        Assertions.assertThat(persistedModule.getName()).isEqualTo(module.getName());
        Assertions.assertThat(persistedModule.getQtyLessons()).isEqualTo(module.getQtyLessons());
        Assertions.assertThat(persistedModule.getId()).isNotNull();

        LOGGER.debug(String.valueOf(persistedModule));
    }
}