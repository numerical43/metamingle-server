package com.mingles.metamingle.script.query.application.service;

import com.mingles.metamingle.script.command.domain.aggregate.entity.Script;
import com.mingles.metamingle.script.query.application.dto.request.FindScriptRequest;
import com.mingles.metamingle.script.query.application.dto.response.ScriptQueryResponse;
import com.mingles.metamingle.script.query.infrastructure.repository.ScriptQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.webjars.NotFoundException;

@Service
@RequiredArgsConstructor
public class ScriptQueryService {

    private final ScriptQueryRepository scriptQueryRepository;

    @Transactional(readOnly = true)
    public ScriptQueryResponse findScriptByScriptNo(FindScriptRequest request) {

        Script script = scriptQueryRepository.findById(request.getScriptNo())
                .orElseThrow(() -> new NotFoundException("해당 대본이 존재하지 않습니다."));

        return ScriptQueryResponse.from(script);
    }

}