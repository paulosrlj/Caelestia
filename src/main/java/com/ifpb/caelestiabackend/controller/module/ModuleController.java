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

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        moduleService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Module> getById(@PathVariable("id") Long id) {
        Module module = moduleService.getById(id);
        return ResponseEntity.ok(module);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<Module> update(@PathVariable("id") Long id, @RequestBody ModuleDto moduleDto) {
        Module moduleUpdated = moduleService.update(moduleDto);
        return ResponseEntity.ok(moduleUpdated);
    }


}
