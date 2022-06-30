package com.ifpb.caelestiabackend.domain.usecases.praticalLesson;

import com.ifpb.caelestiabackend.domain.entities.PraticalLesson.PraticalLesson;

public interface GetById {
    PraticalLesson getById(Long id);
}
