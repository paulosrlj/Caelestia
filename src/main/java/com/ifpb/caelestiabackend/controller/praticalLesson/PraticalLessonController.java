package com.ifpb.caelestiabackend.controller.praticalLesson;

import com.ifpb.caelestiabackend.domain.entities.Module;
import com.ifpb.caelestiabackend.domain.entities.PraticalLesson.PraticalLesson;
import com.ifpb.caelestiabackend.domain.entities.TheoricLesson;
import com.ifpb.caelestiabackend.repository.ModuleRepository;
import com.ifpb.caelestiabackend.repository.TheoricLessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.AbstractMap;

@RestController
@RequestMapping("/pratical-lesson")
@CrossOrigin
public class PraticalLessonController implements IPraticalLessonController {

    @Autowired
    private TheoricLessonRepository theoricLessonRepository;

    @Autowired
    private ModuleRepository moduleRepository;

    @Override
//    @PostMapping("/")
    public ResponseEntity<AbstractMap<String, Object>> add(@Valid @RequestBody PraticalLesson praticalLesson) {
        return null;
    }

    @PostMapping("/")
    public void add2(@Valid @RequestBody PraticalLesson praticalLesson) {
        System.out.println(praticalLesson);
    }
}
