package com.ifpb.caelestiabackend.controller.module;

import com.ifpb.caelestiabackend.domain.entities.Module;
import org.springframework.http.ResponseEntity;

import javax.validation.Valid;
import java.util.AbstractMap;

public interface IModuleController {
    ResponseEntity<AbstractMap<String, Object>> add(@Valid Module module);

    ResponseEntity<?> delete(Long id);

    ResponseEntity<AbstractMap<String, Object>> getById(Long id);

    ResponseEntity<AbstractMap<String, Object>> update(Long id, Module module);
}
