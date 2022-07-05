package com.ifpb.caelestiabackend.controller.module;

import com.ifpb.caelestiabackend.domain.entities.Module;
import com.ifpb.caelestiabackend.services.module.ModuleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.AbstractMap;
import java.util.LinkedHashMap;

@RestController
@RequestMapping("/module")
public class ModuleController implements IModuleController {

    private final ModuleService moduleService;

    public ModuleController(ModuleService moduleService) {
        this.moduleService = moduleService;
    }

    @Override
    @PostMapping(value = "/")
    public ResponseEntity<AbstractMap<String, Object>>add(@Valid @RequestBody Module module) {
        Module modulePersisted = moduleService.add(module);

        return ResponseEntity.ok(makeHttpResponseObject(modulePersisted));
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        moduleService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<AbstractMap<String, Object>> getById(@PathVariable("id") Long id) {
        Module module = moduleService.getById(id);

        return ResponseEntity.ok(makeHttpResponseObject(module));
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<AbstractMap<String, Object>> update(@PathVariable("id") Long id
            , @RequestBody Module module) {
        Module moduleUpdated = moduleService.update(id, module);
        AbstractMap<String, Object> obj = new LinkedHashMap<>();
        obj.put("id", moduleUpdated.getId());
        obj.put("name", moduleUpdated.getName());

        return ResponseEntity.ok(obj);
    }

    private AbstractMap<String, Object> makeHttpResponseObject(Module module) {
        AbstractMap<String, Object> obj = new LinkedHashMap<>();
        obj.put("id", module.getId());
        obj.put("name", module.getName());

        if (module.getTheoricLessons() != null)
            obj.put("theoricLessons", module.getTheoricLessons());

        return obj;
    }
}
