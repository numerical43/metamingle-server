package com.mingles.metamingle.script.command.application.service;

import com.mingles.metamingle.script.command.application.dto.request.CreateScriptRequest;
import com.mingles.metamingle.script.command.application.dto.request.UpdateScriptRequest;
import com.mingles.metamingle.script.command.application.dto.response.ScriptCommandResponse;
import com.mingles.metamingle.script.command.domain.aggregate.entity.Script;
import com.mingles.metamingle.script.command.domain.aggregate.vo.ScriptMemberNoVO;
import com.mingles.metamingle.script.command.domain.aggregate.vo.ShortFormNoVO;
import com.mingles.metamingle.script.command.domain.repository.ScriptCommandRepository;
import com.mingles.metamingle.script.command.domain.service.ScriptCommandDomainService;
import com.mingles.metamingle.script.command.infrastructure.service.ScriptCommandInfraService;
import com.mingles.metamingle.script.query.infrastructure.repository.ScriptQueryRepository;
import com.mingles.metamingle.shortform.command.domain.aggregate.vo.MemberNoVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.webjars.NotFoundException;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class ScriptCommandService {

    private final ScriptCommandRepository scriptCommandRepository;
    private final ScriptQueryRepository scriptQueryRepository;
    private final ScriptCommandDomainService scriptDomainService;
    private final ScriptCommandInfraService scriptCommandInfraService;

    @Transactional
    public ScriptCommandResponse createScript(Long memberNo, CreateScriptRequest request) {

        //사용자가 입력한 대본이 적합한지 확인
        scriptDomainService.validateScriptLanguage(request.getContent());
        scriptDomainService.validateScriptLength(request.getContent());

        String aiScript = scriptCommandInfraService.getAiScript(request.getContent()).getOutput();

        Script script = scriptCommandRepository.save(Script.builder()
                        .scriptMemberNoVO(new ScriptMemberNoVO(memberNo))
                        .shortFormNoVO(new ShortFormNoVO(request.getShortFormNo()))
                        .aiContent(aiScript)
                        .uploadDate(LocalDate.now())
                        .build());

        return ScriptCommandResponse.from(script);

    }

    @Transactional
    public ScriptCommandResponse updateScript(Long memberNo, UpdateScriptRequest request) {

        Script script = scriptQueryRepository.findScriptByShortFormNoVO(new ShortFormNoVO(request.getShortFormNo()))
                .orElseThrow(() -> new NotFoundException("해당 대본이 존재하지 않습니다."));

        String aiScript = scriptCommandInfraService.getAiScript(script.getMemberContent()).getOutput();

        script.update(aiScript);

        return ScriptCommandResponse.from(script);

    }

}