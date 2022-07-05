package com.ifpb.caelestiabackend.controller.achievement;

import com.ifpb.caelestiabackend.domain.entities.Achievement.Achievement;
import org.springframework.http.ResponseEntity;

import javax.validation.Valid;
import java.util.AbstractMap;

public interface IAchievementController {
    ResponseEntity<AbstractMap<String, Object>> add(@Valid Achievement achievement);

    ResponseEntity<?> delete(Long id);

    ResponseEntity<AbstractMap<String, Object>> getById(Long id);

    ResponseEntity<AbstractMap<String, Object>> update(Long id, Achievement achievement);
}
