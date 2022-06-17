package com.ifpb.caelestiabackend.controller.module;

import com.ifpb.caelestiabackend.domain.entities.Module;
import org.springframework.http.ResponseEntity;

public interface IModuleController {
    ResponseEntity<Module> add(Module module);
}
