package com.ifpb.caelestiabackend.controller.module;

import com.ifpb.caelestiabackend.domain.entities.Module;
import com.ifpb.caelestiabackend.dto.ModuleDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import javax.validation.Valid;

public interface IModuleController {
    ResponseEntity<Module> add(@Valid ModuleDto moduleDto);

    ResponseEntity<?> delete(@PathVariable("id") Long id);

    ResponseEntity<Module> getById(@PathVariable("id") Long id);
}
