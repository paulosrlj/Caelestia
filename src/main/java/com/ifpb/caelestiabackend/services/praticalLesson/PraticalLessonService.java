package com.ifpb.caelestiabackend.services.praticalLesson;

import com.ifpb.caelestiabackend.domain.entities.Module;
import com.ifpb.caelestiabackend.domain.entities.PraticalLesson.PraticalLesson;
import com.ifpb.caelestiabackend.domain.entities.TheoricLesson;
import com.ifpb.caelestiabackend.domain.usecases.module.AddModule;
import com.ifpb.caelestiabackend.domain.usecases.module.DeleteModule;
import com.ifpb.caelestiabackend.domain.usecases.module.UpdateModule;
import com.ifpb.caelestiabackend.domain.usecases.praticalLesson.AddPraticalLesson;
import com.ifpb.caelestiabackend.domain.usecases.praticalLesson.GetById;
import com.ifpb.caelestiabackend.repository.ModuleRepository;
import com.ifpb.caelestiabackend.repository.PraticalLessonRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class PraticalLessonService implements AddPraticalLesson, GetById {

    private final PraticalLessonRepository praticalLessonRepository;

    public PraticalLessonService(PraticalLessonRepository praticalLessonRepository) {
        this.praticalLessonRepository = praticalLessonRepository;
    }


    @Override
    public PraticalLesson add(PraticalLesson praticalLesson) {
        return praticalLessonRepository.save(praticalLesson);
    }

    @Override
    public PraticalLesson getById(Long id) {
        Optional<PraticalLesson> praticalLesson = praticalLessonRepository.findById(id);

        if (praticalLesson.isEmpty()) {
            throw new EntityNotFoundException(String.format("A lição prática de Id %d não existe.", id));
        }

        return praticalLesson.get();
    }
}
