package com.mingles.metamingle.scenario.command.application.controller;

import com.mingles.metamingle.global.infra.AiInfraService;
import com.mingles.metamingle.member.query.application.service.MemberQueryService;
import com.mingles.metamingle.scenario.command.application.dto.request.CreateScenarioRequest;
import com.mingles.metamingle.scenario.command.application.service.ScenarioCommandService;
import com.mingles.metamingle.scenario.command.domain.service.ScenarioCommandDomainService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Tag(name = "AI 생성 대본 Command API")
@RestController
@RequiredArgsConstructor
public class ScenarioCommandController {

    private final AiInfraService aiInfraService;
    private final ScenarioCommandDomainService scenarioCommandDomainService;

    @Operation(summary = "AI 대본 스트리밍 요청")
    @PostMapping(value = "/scenario/streaming", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ServerSentEvent<String>> streamScenario(@RequestHeader("Authentication") String token,
                                                        @RequestBody CreateScenarioRequest request) {

        scenarioCommandDomainService.validateScriptLanguage(request.getText());
        scenarioCommandDomainService.validateScriptLength(request.getText());

        return aiInfraService.getStreamingData(request.getText());

    }

    @Operation(summary = "AI 대본 맞춤 이미지 추천")
    @PostMapping(value = "/scenario/image")
    public String getRecommendImage(@RequestHeader("Authentication") String token,
                                    @RequestBody CreateScenarioRequest request) {

        String text = request.getText();

        return aiInfraService.getBackGroundImage(text);
    }

    @Operation(summary = "AI 대본 맞춤 음악 추천")
    @PostMapping(value = "/scenario/bgm")
    public List<String> getRecommendImageAndBGM(@RequestHeader("Authentication") String token,
                                                @RequestBody CreateScenarioRequest request) {

        String text = request.getText();

        return Arrays.asList(
                aiInfraService.getBackGroundImage(text),
                aiInfraService.getBGM(text)
        );

    }


}
