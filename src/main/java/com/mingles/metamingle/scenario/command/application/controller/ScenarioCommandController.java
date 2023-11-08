package com.mingles.metamingle.scenario.command.application.controller;

import com.mingles.metamingle.member.query.application.service.MemberQueryService;
import com.mingles.metamingle.scenario.command.application.dto.request.CreateScenarioRequest;
import com.mingles.metamingle.scenario.command.application.service.ScenarioCommandService;
import com.mingles.metamingle.scenario.command.infrastructure.service.ScenarioCommandInfraService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@Tag(name = "AI 생성 대본 Command API")
@RestController
@RequiredArgsConstructor
public class ScenarioCommandController {

    private final ScenarioCommandService scriptCommandService;
    private final MemberQueryService memberQueryService;
    private final ScenarioCommandInfraService scriptCommandInfraService;

//    @Operation(summary = "AI 대본 생성")
//    @PostMapping("api/script")
//    public ResponseEntity<ApiResponse> createScript(@RequestHeader Map<String, String> requestHeader,
//                                                    @RequestBody CreateScriptRequest request) {
//
//        String header = requestHeader.get("Authentication");
//        Long providerId = jwtTokenService.getUserIdFromToken(header);
//
//        Long memberNo = memberQueryService.findMemberNoByProviderId("111111");
//
//        ScriptCommandResponse response = scriptCommandService.createScript(memberNo, request);
//
//        return ResponseEntity.status(HttpStatus.CREATED).body(
//                ApiResponse.success("성공적으로 저장되었습니다." , response)
//        );
//    }

//    @Operation(summary = "AI 대본 재생성")
//    @PostMapping("api/script-recreate")
//    public ResponseEntity<ApiResponse> updateScript(@RequestHeader Map<String, String> requestHeader,
//                                                    @RequestBody UpdateScriptRequest request) {
//
//        String header = requestHeader.get("auth");
//        Long providerId = jwtTokenService.getUserIdFromToken(header);
//
//        Long memberNo = memberQueryService.findMemberNoByProviderId("111111");
//
//        ScriptCommandResponse response = scriptCommandService.updateScript(memberNo, request);
//
//        return ResponseEntity.status(HttpStatus.CREATED).body(
//                ApiResponse.success("성공적으로 수정되었습니다." , response)
//        );
//    }

    @Operation(summary = "AI 대본 스트리밍 요청")
    @PostMapping(value = "/scenario/streaming", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ServerSentEvent<String>> streamScenario(@RequestBody CreateScenarioRequest request) {

        return scriptCommandInfraService.getStreamingData(request.getText());

    }


}
