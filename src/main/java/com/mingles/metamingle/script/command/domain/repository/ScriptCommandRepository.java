package com.mingles.metamingle.script.command.domain.repository;

import com.mingles.metamingle.script.command.domain.aggregate.entity.Script;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScriptCommandRepository extends JpaRepository<Script, Long> {
}