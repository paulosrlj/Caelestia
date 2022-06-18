package com.ifpb.caelestiabackend.controller.module;

import com.ifpb.caelestiabackend.domain.entities.Module;
import com.ifpb.caelestiabackend.dto.ModuleDto;
import com.ifpb.caelestiabackend.services.module.ModuleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/module")
public class ModuleController implements IModuleController {

    private final ModuleService moduleService;

    public ModuleController(ModuleService moduleService) {
        this.moduleService = moduleService;
    }

    @Override
    @PostMapping(value = "/")
    public ResponseEntity<Module> add(@Valid @RequestBody ModuleDto moduleDto) {
        Module modulePersisted = moduleService.add(moduleDto);
        return ResponseEntity.ok(modulePersisted);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        System.out.println("ID" + id);
        moduleService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
