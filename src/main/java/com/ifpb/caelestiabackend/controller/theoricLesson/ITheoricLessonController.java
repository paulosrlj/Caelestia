package com.ifpb.caelestiabackend.controller.theoricLesson;

import com.ifpb.caelestiabackend.domain.entities.Module;
import com.ifpb.caelestiabackend.domain.entities.TheoricLesson;
import org.springframework.http.ResponseEntity;

import javax.validation.Valid;
import java.util.AbstractMap;
import java.util.Map;

public interface ITheoricLessonController {
//    ResponseEntity<AbstractMap<String, Object>> add(@Valid TheoricLesson theoricLesson);
void add(@Valid TheoricLesson theoricLesson);

}
