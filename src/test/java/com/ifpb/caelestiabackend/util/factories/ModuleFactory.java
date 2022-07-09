package com.ifpb.caelestiabackend.util.factories;

import com.ifpb.caelestiabackend.domain.entities.Module;
import com.ifpb.caelestiabackend.domain.entities.TheoricLesson;

import java.time.LocalDateTime;

public class ModuleFactory {
    public static Module makeModule() {
        return Module.builder()
                .id(null)
                .name("Astronomia antiga")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }

    public static Module makePersistedModule() {
        return Module.builder()
                .id(1L)
                .name("Astronomia antiga")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }

    public static Module makePersistedModuleWithTheoricLesson() {
        Module module = Module.builder()
                .id(1L)
                .name("Astronomia antiga")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        TheoricLesson tc = TheoricLessonFactory.makeTheoricLesson();
        tc.setModule(module);
        module.addTheoricLesson(tc);

        return module;
    }
}
