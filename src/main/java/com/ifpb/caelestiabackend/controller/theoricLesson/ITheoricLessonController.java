package com.ifpb.caelestiabackend.controller.theoricLesson;

import com.ifpb.caelestiabackend.domain.entities.Module;
import com.ifpb.caelestiabackend.domain.entities.TheoricLesson;
import org.springframework.http.ResponseEntity;

import javax.validation.Valid;
import java.util.AbstractMap;

public interface ITheoricLessonController {
    ResponseEntity<AbstractMap<String, Object>> add(@Valid TheoricLesson theoricLesson);
}
