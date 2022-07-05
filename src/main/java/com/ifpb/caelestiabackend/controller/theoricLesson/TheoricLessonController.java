package com.ifpb.caelestiabackend.controller.theoricLesson;

import com.ifpb.caelestiabackend.domain.entities.DraftJsText;
import com.ifpb.caelestiabackend.domain.entities.Module;
import com.ifpb.caelestiabackend.domain.entities.TheoricLesson;
import com.ifpb.caelestiabackend.repository.ModuleRepository;
import com.ifpb.caelestiabackend.repository.TheoricLessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.AbstractMap;
import java.util.Map;

@RestController
@RequestMapping("/theoriclesson")
@CrossOrigin
public class TheoricLessonController implements ITheoricLessonController {

    @Autowired
    private TheoricLessonRepository theoricLessonRepository;

    @Autowired
    private ModuleRepository moduleRepository;

    @Override
    @PostMapping("/")
    public void add(@RequestBody TheoricLesson theoricLesson) {
        System.out.println(theoricLesson);
        Module module = new Module();
        module.setName("Modulo de teste");

        TheoricLesson tc = new TheoricLesson();

        Module mP = moduleRepository.save(module);

        tc.setModule(mP);

        theoricLessonRepository.save(tc);
    }

}
