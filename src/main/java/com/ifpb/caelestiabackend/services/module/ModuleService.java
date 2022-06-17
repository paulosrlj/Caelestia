package com.ifpb.caelestiabackend.services.module;

import com.ifpb.caelestiabackend.domain.entities.Module;
import com.ifpb.caelestiabackend.domain.usecases.module.AddModule;
import com.ifpb.caelestiabackend.repository.ModuleRepository;
import org.springframework.stereotype.Service;

@Service
public class ModuleService implements AddModule {

    private final ModuleRepository moduleRepository;

    public ModuleService(ModuleRepository moduleRepository) {
        this.moduleRepository = moduleRepository;
    }

    @Override
    public Module add(Module module) {
        return moduleRepository.save(module);
    }
}
