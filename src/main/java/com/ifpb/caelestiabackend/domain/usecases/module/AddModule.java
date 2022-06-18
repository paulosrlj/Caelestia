package com.ifpb.caelestiabackend.domain.usecases.module;

import com.ifpb.caelestiabackend.domain.entities.Module;
import com.ifpb.caelestiabackend.dto.ModuleDto;

public interface AddModule {
    Module add(ModuleDto moduleDto);
}
