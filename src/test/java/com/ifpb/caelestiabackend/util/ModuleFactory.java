package com.ifpb.caelestiabackend.util;

import com.ifpb.caelestiabackend.domain.entities.Module;
import com.ifpb.caelestiabackend.domain.entities.TheoricLesson;

public class ModuleFactory {
    public static Module makeModule() {
        return Module.builder()
                .name("Astronomia antiga")
                .qtyLessons(0)
                .build();
    }

    public static Module makePersistedModule() {
        return Module.builder()
                .id(1L)
                .name("Astronomia antiga")
                .qtyLessons(0)
                .build();
    }

    public static Module makePersistedModuleWithTheoricLesson() {
        Module module = Module.builder()
                .id(1L)
                .name("Astronomia antiga")
                .qtyLessons(0)
                .build();

        TheoricLesson tc = TheoricLessonFactory.makeTheoricLesson();
        tc.setModule(module);
        module.addTheoricLesson(tc);

        return module;
    }
}
