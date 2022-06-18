package com.ifpb.caelestiabackend.services.module;

import com.ifpb.caelestiabackend.domain.entities.Module;
import com.ifpb.caelestiabackend.domain.usecases.module.AddModule;
import com.ifpb.caelestiabackend.dto.ModuleDto;
import com.ifpb.caelestiabackend.repository.ModuleRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class ModuleService implements AddModule {

    private final ModuleRepository moduleRepository;

    public ModuleService(ModuleRepository moduleRepository) {
        this.moduleRepository = moduleRepository;
    }

    @Override
    public Module add(ModuleDto moduleDto) {
        Module module = new Module();

        BeanUtils.copyProperties(moduleDto, module);

        return moduleRepository.save(module);
    }
}
