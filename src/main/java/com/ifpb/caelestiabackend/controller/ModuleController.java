package com.ifpb.caelestiabackend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ModuleController implements Controller<Module> {

    @Override
    public ResponseEntity<Module> add(Module module) {
        return null;
    }
}
