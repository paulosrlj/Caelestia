package com.ifpb.caelestiabackend.controller.module;

import com.ifpb.caelestiabackend.domain.entities.Module;
import com.ifpb.caelestiabackend.services.module.ModuleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/module")
public class ModuleController implements IModuleController {

    private final ModuleService moduleService;

    public ModuleController(ModuleService moduleService) {
        this.moduleService = moduleService;
    }

    @Override
    @PostMapping(value = "/")
    public ResponseEntity<Module> add(@RequestBody Module module) {
        Module modulePersisted = moduleService.add(module);
        return ResponseEntity.ok(modulePersisted);
    }

}
