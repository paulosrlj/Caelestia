package com.ifpb.caelestiabackend.services.praticalLesson;

import com.ifpb.caelestiabackend.domain.entities.Module;
import com.ifpb.caelestiabackend.domain.entities.PraticalLesson.Answers;
import com.ifpb.caelestiabackend.domain.entities.PraticalLesson.PraticalLesson;
import com.ifpb.caelestiabackend.domain.usecases.praticalLesson.AddPraticalLesson;
import com.ifpb.caelestiabackend.domain.usecases.praticalLesson.DeletePraticalLesson;
import com.ifpb.caelestiabackend.domain.usecases.praticalLesson.GetById;
import com.ifpb.caelestiabackend.domain.usecases.praticalLesson.UpdatePraticalLesson;
import com.ifpb.caelestiabackend.repository.ModuleRepository;
import com.ifpb.caelestiabackend.repository.PraticalLessonRepository;
import com.ifpb.caelestiabackend.util.CopyNotNullProperties;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class PraticalLessonService implements AddPraticalLesson, GetById,
        DeletePraticalLesson, UpdatePraticalLesson {

    private final PraticalLessonRepository praticalLessonRepository;
    private final ModuleRepository moduleRepository;

    public PraticalLessonService(PraticalLessonRepository praticalLessonRepository, ModuleRepository moduleRepository) {
        this.praticalLessonRepository = praticalLessonRepository;
        this.moduleRepository = moduleRepository;
    }


    @Override
    public PraticalLesson add(PraticalLesson praticalLesson) {
        Optional<Module> moduleofLesson = moduleRepository.findById(praticalLesson.getModule().getId());

        if (moduleofLesson.isEmpty()) {
            throw new EntityNotFoundException(String.format("O módulo de Id %d não existe.",
                    praticalLesson.getModule().getId()));
        }

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

    @Override
    public void delete(Long id) {
        Optional<PraticalLesson> praticalLesson = praticalLessonRepository.findById(id);

        if (praticalLesson.isEmpty()) {
            throw new EntityNotFoundException(String.format("A lição prática de Id %d não existe.", id));
        }

        praticalLessonRepository.deleteById(id);
    }


    @Override
    public PraticalLesson update(Long id, PraticalLesson praticalLesson) {
        Optional<PraticalLesson> plFound = praticalLessonRepository.findById(id);

        if (plFound.isEmpty()) {
            throw new EntityNotFoundException(String.format("A lição pŕatica de Id %d não existe.", id));
        }

        PraticalLesson plToPersist = plFound.get();

        if (praticalLesson.getModule() != null) {
            Optional<Module> moduleFound = moduleRepository.findById(praticalLesson.getModule().getId());
            if (moduleFound.isEmpty()) {
                throw new EntityNotFoundException(String.format("O módulo de Id %d não existe.", id));
            }
            praticalLesson.setModule(moduleFound.get());
        }

        if (praticalLesson.getAnswers() != null) {
            Answers answersToPersist = plToPersist.getAnswers();
            CopyNotNullProperties.copyNonNullProperties(praticalLesson.getAnswers(), answersToPersist);
            praticalLesson.setAnswers(answersToPersist);
        }


        CopyNotNullProperties.copyNonNullProperties(praticalLesson, plToPersist);

        return praticalLessonRepository.save(plToPersist);
    }
}
