
package com.mingles.metamingle.script.query.infrastructure.repository;

import com.mingles.metamingle.script.command.domain.aggregate.entity.Script;
import com.mingles.metamingle.script.command.domain.aggregate.vo.ShortFormNoVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ScriptQueryRepository extends JpaRepository<Script, Long> {

    Optional<Script> findScriptByShortFormNoVO(ShortFormNoVO shortFormNoVO);

}