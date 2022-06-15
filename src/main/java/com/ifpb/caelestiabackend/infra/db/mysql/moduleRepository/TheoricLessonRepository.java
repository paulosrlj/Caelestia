package com.ifpb.caelestiabackend.infra.db.mysql.moduleRepository;

import com.ifpb.caelestiabackend.domain.entities.TheoricLesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TheoricLessonRepository extends JpaRepository<TheoricLesson, Long> {

}
