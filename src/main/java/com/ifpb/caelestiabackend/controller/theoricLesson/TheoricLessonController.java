package com.ifpb.caelestiabackend.controller.theoricLesson;

import com.ifpb.caelestiabackend.domain.entities.TheoricLesson;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.AbstractMap;

@RestController
@RequestMapping("/theoriclesson")
public class TheoricLessonController implements ITheoricLessonController {

    @Override
    @PostMapping("/")
    public ResponseEntity<AbstractMap<String, Object>> add(TheoricLesson theoricLesson) {
        return null;
    }

}
