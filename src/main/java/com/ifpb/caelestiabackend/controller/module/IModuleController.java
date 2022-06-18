package com.ifpb.caelestiabackend.controller.module;

import com.ifpb.caelestiabackend.domain.entities.Module;
import com.ifpb.caelestiabackend.dto.ModuleDto;
import org.springframework.http.ResponseEntity;

import javax.validation.Valid;

public interface IModuleController {
    ResponseEntity<Module> add(@Valid ModuleDto moduleDto);


}
