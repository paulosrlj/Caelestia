package com.ifpb.caelestiabackend.controller.praticalLesson;

import com.ifpb.caelestiabackend.domain.entities.Module;
import com.ifpb.caelestiabackend.domain.entities.PraticalLesson.PraticalLesson;
import com.ifpb.caelestiabackend.services.praticalLesson.PraticalLessonService;
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

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<AbstractMap<String, Object>> update(@PathVariable("id") Long id,
                                                              @RequestBody PraticalLesson praticalLesson) {
        PraticalLesson plUpdated = praticalLessonService.update(id, praticalLesson);

        AbstractMap<String, Object> obj = new LinkedHashMap<>();
        obj.put("id", plUpdated.getId());
        obj.put("lessonName", plUpdated.getLessonName());
        obj.put("xpEarned", plUpdated.getXpEarned());
        obj.put("answers", plUpdated.getAnswers());

        return ResponseEntity.ok(obj);
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
