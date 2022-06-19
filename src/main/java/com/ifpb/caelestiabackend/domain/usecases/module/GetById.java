package com.ifpb.caelestiabackend.domain.usecases.module;

import com.ifpb.caelestiabackend.domain.entities.Module;

public interface GetById {
    Module getById(Long id);
}
