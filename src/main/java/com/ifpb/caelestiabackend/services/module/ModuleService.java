package com.ifpb.caelestiabackend.services.module;

import com.ifpb.caelestiabackend.domain.entities.Module;
import com.ifpb.caelestiabackend.domain.entities.TheoricLesson;
import com.ifpb.caelestiabackend.domain.usecases.module.AddModule;
import com.ifpb.caelestiabackend.domain.usecases.module.DeleteModule;
import com.ifpb.caelestiabackend.domain.usecases.module.GetById;
import com.ifpb.caelestiabackend.domain.usecases.module.UpdateModule;
import com.ifpb.caelestiabackend.dto.ModuleDto;
import com.ifpb.caelestiabackend.dto.TheoricLessonDto;
import com.ifpb.caelestiabackend.repository.ModuleRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class ModuleService implements AddModule, DeleteModule, GetById, UpdateModule {

    private final ModuleRepository moduleRepository;

    public ModuleService(ModuleRepository moduleRepository) {
        this.moduleRepository = moduleRepository;
    }

    @Override
    public Module add(ModuleDto moduleDto) {
        Module module = new Module();
        BeanUtils.copyProperties(moduleDto, module, "qtyLessons");

        Set<TheoricLesson> lessons = new HashSet<>();

        for(TheoricLessonDto l : moduleDto.getTheoricLessons()) {
            TheoricLesson lesson = new TheoricLesson();
            BeanUtils.copyProperties(l, lesson);
            lesson.setModule(module);
            lessons.add(lesson);
        }

        module.setTheoricLessons(lessons);

        return moduleRepository.save(module);
    }

    @Override
    public void delete(Long id) {
        Optional<Module> module = moduleRepository.findById(id);

        if (module.isEmpty()) {
            throw new EntityNotFoundException(String.format("O módulo de Id %d não existe.", id));
        }

         moduleRepository.deleteById(id);
    }

    @Override
    public Module getById(Long id) {
        Optional<Module> module = moduleRepository.findById(id);

        if (module.isEmpty()) {
            throw new EntityNotFoundException(String.format("O módulo de Id %d não existe.", id));
        }

        return module.get();
    }

    @Override
    public Module update(ModuleDto moduleDto) {
        Module module = new Module();

        BeanUtils.copyProperties(moduleDto, module);

        return moduleRepository.save(module);
    }
}
