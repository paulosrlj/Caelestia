package com.ifpb.caelestiabackend.controller.module;

import com.ifpb.caelestiabackend.domain.entities.Module;
import org.springframework.http.ResponseEntity;

import javax.validation.Valid;
import java.util.Map;

public interface IModuleController {
    ResponseEntity<Map<String, Object>> add(@Valid Module module);

    ResponseEntity<?> delete(Long id);

    ResponseEntity<Module> getById(Long id);

    ResponseEntity<Module> update(Long id, ModuleDto moduleDto);
}
