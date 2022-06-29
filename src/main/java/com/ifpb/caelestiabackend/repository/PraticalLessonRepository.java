package com.ifpb.caelestiabackend.repository;

import com.ifpb.caelestiabackend.domain.entities.PraticalLesson.PraticalLesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PraticalLessonRepository extends JpaRepository<PraticalLesson, Long> {

}
