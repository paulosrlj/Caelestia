package com.ifpb.caelestiabackend.domain.usecases.module;

import com.ifpb.caelestiabackend.domain.entities.Module;
import com.ifpb.caelestiabackend.dto.ModuleDto;

public interface UpdateModule {
    Module update(Long id, ModuleDto moduleDto);
}
