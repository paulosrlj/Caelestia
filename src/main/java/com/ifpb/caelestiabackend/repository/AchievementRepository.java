package com.ifpb.caelestiabackend.repository;

import com.ifpb.caelestiabackend.domain.entities.Achievement.Achievement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AchievementRepository extends JpaRepository<Achievement, Long> {
}
