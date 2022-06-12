package com.ifpb.caelestiabackend.infra.db.mysql.moduleRepository;

import com.ifpb.caelestiabackend.domain.entities.Module;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModuleRepository extends JpaRepository<Module, Long> {

}
