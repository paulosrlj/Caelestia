package com.ifpb.caelestiabackend.controller.praticalLesson;

import com.ifpb.caelestiabackend.domain.entities.PraticalLesson.PraticalLesson;
import org.springframework.http.ResponseEntity;

import javax.validation.Valid;
import java.util.AbstractMap;

public interface IPraticalLessonController {
    ResponseEntity<AbstractMap<String, Object>> add(@Valid PraticalLesson praticalLesson);

    ResponseEntity<AbstractMap<String, Object>> getById(Long id);

    ResponseEntity<?> delete(Long id);

    ResponseEntity<AbstractMap<String, Object>> update(Long id, PraticalLesson praticalLesson);
}
