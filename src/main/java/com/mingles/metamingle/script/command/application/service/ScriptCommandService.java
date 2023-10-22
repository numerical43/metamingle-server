package com.mingles.metamingle.script.command.application.service;

import com.mingles.metamingle.script.command.application.dto.request.CreateScriptRequest;
import com.mingles.metamingle.script.command.application.dto.response.ScriptCommandResponse;
import com.mingles.metamingle.script.command.domain.aggregate.entity.Script;
import com.mingles.metamingle.script.command.domain.aggregate.vo.ScriptMemberNoVO;
import com.mingles.metamingle.script.command.domain.aggregate.vo.ShortFormNoVO;
import com.mingles.metamingle.script.command.domain.repository.ScriptCommandRepository;
import com.mingles.metamingle.script.command.domain.service.ScriptCommandDomainService;
import com.mingles.metamingle.script.command.infrastructure.service.ScriptCommandInfraService;
import com.mingles.metamingle.shortform.command.domain.aggregate.vo.MemberNoVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class ScriptCommandService {

    private final ScriptCommandRepository scriptCommandRepository;
    private final ScriptCommandDomainService scriptDomainService;
    private final ScriptCommandInfraService scriptCommandInfraService;

    @Transactional
    public ScriptCommandResponse createScript(Long memberNo, CreateScriptRequest request) {

        //사용자가 입력한 대본이 적합한지 확인
        scriptDomainService.validateScriptContent(request.getContent());

        String aiScript = scriptCommandInfraService.getAiScript(request.getContent()).getText();

        Script script = scriptCommandRepository.save(Script.builder()
                        .scriptMemberNoVO(new ScriptMemberNoVO(memberNo))
                        .shortFormNoVO(new ShortFormNoVO(request.getShortFormNo()))
                        .scriptContent(aiScript)
                        .uploadDate(LocalDate.now())
                        .build());

        return ScriptCommandResponse.from(script);

    }



}