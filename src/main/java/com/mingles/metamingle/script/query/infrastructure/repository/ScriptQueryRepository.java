
package com.mingles.metamingle.script.query.infrastructure.repository;

import com.mingles.metamingle.script.command.domain.aggregate.entity.Script;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScriptQueryRepository extends JpaRepository<Script, Long> {
}