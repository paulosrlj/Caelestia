package com.ifpb.caelestiabackend.domain.usecases.module;

import com.ifpb.caelestiabackend.domain.entities.Module;

public interface UpdateModule {
    Module update(Long id, Module module);
}
