package com.ifpb.caelestiabackend.controller.praticalLesson;

import com.ifpb.caelestiabackend.domain.entities.Module;
import com.ifpb.caelestiabackend.domain.entities.PraticalLesson.PraticalLesson;
import com.ifpb.caelestiabackend.domain.entities.TheoricLesson;
import com.ifpb.caelestiabackend.repository.ModuleRepository;
import com.ifpb.caelestiabackend.repository.TheoricLessonRepository;
import com.ifpb.caelestiabackend.services.praticalLesson.PraticalLessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.AbstractMap;
import java.util.LinkedHashMap;

@RestController
@RequestMapping("/pratical-lesson")
@CrossOrigin
public class PraticalLessonController implements IPraticalLessonController {

    private final PraticalLessonService praticalLessonService;

    public PraticalLessonController(PraticalLessonService praticalLessonService) {
        this.praticalLessonService = praticalLessonService;
    }

    @Override
    @PostMapping("/")
    public ResponseEntity<AbstractMap<String, Object>> add(
            @Valid @RequestBody PraticalLesson praticalLesson
    ) {
        return null;
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<AbstractMap<String, Object>> getById(@PathVariable("id") Long id) {
        PraticalLesson pl = praticalLessonService.getById(id);

        return ResponseEntity.ok(makeHttpResponseObject(pl));
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        praticalLessonService.delete(id);
        return ResponseEntity.noContent().build();
    }

    private AbstractMap<String, Object> makeHttpResponseObject(PraticalLesson pl) {
        AbstractMap<String, Object> obj = new LinkedHashMap<>();
        obj.put("id", pl.getId());
        obj.put("lessonName", pl.getLessonName());
        obj.put("description", pl.getDescription());
        obj.put("xpEarned", pl.getXpEarned());
        obj.put("answers", pl.getAnswers());

        obj.put("module", pl.getModule().getName());

        return obj;
    }
}
