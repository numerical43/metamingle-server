package com.mingles.metamingle.script.command.application.controller;

import com.mingles.metamingle.common.ApiResponse;
import com.mingles.metamingle.script.command.application.dto.request.CreateScriptRequest;
import com.mingles.metamingle.script.command.application.dto.response.ScriptCommandResponse;
import com.mingles.metamingle.script.command.application.service.ScriptCommandService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "AI 생성 대본 Command API")
@RestController
@RequiredArgsConstructor
public class ScriptCommandController {

    private final ScriptCommandService scriptCommandService;

    @Operation(summary = "AI 대본 생성")
    @PostMapping("api/script")
    public ResponseEntity<ApiResponse> createScript(@RequestBody CreateScriptRequest request) {

        ScriptCommandResponse response = scriptCommandService.createScript(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                ApiResponse.success("성공적으로 저장되었습니다.",response)
        );
    }

}
