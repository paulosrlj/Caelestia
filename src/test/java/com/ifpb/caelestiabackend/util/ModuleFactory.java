package com.ifpb.caelestiabackend.util;

import com.ifpb.caelestiabackend.domain.entities.Module;
import com.ifpb.caelestiabackend.domain.entities.TheoricLesson;
import com.ifpb.caelestiabackend.dto.ModuleDto;

public class ModuleFactory {
    public static Module makeModule() {
        return Module.builder()
                .id(null)
                .name("Astronomia antiga")
                .build();
    }

    public static ModuleDto makeModuleDto() {
        return ModuleDto.builder()
                .name("Astronomia antiga")
                .build();
    }

    public static Module makePersistedModule() {
        return Module.builder()
                .id(1L)
                .name("Astronomia antiga")
                .build();
    }

    public static Module makePersistedModuleWithTheoricLesson() {
        Module module = Module.builder()
                .id(1L)
                .name("Astronomia antiga")
                .build();

        TheoricLesson tc = TheoricLessonFactory.makeTheoricLesson();
        tc.setModule(module);
        module.addTheoricLesson(tc);

        return module;
    }
}
