package com.ifpb.caelestiabackend.services.praticalLesson;

import com.ifpb.caelestiabackend.domain.entities.Module;
import com.ifpb.caelestiabackend.domain.entities.PraticalLesson.PraticalLesson;
import com.ifpb.caelestiabackend.domain.entities.TheoricLesson;
import com.ifpb.caelestiabackend.domain.usecases.module.AddModule;
import com.ifpb.caelestiabackend.domain.usecases.module.DeleteModule;
import com.ifpb.caelestiabackend.domain.usecases.module.GetById;
import com.ifpb.caelestiabackend.domain.usecases.module.UpdateModule;
import com.ifpb.caelestiabackend.domain.usecases.praticalLesson.AddPraticalLesson;
import com.ifpb.caelestiabackend.repository.ModuleRepository;
import com.ifpb.caelestiabackend.repository.PraticalLessonRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class PraticalLessonService implements AddPraticalLesson {

    private final PraticalLessonRepository praticalLessonRepository;

    public PraticalLessonService(PraticalLessonRepository praticalLessonRepository) {
        this.praticalLessonRepository = praticalLessonRepository;
    }


    @Override
    public PraticalLesson add(PraticalLesson praticalLesson) {
        return praticalLessonRepository.save(praticalLesson);
    }
}
