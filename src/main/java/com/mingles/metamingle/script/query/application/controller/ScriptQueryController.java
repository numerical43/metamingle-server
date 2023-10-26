package com.mingles.metamingle.script.query.application.controller;

import com.mingles.metamingle.common.ApiResponse;
import com.mingles.metamingle.script.command.application.dto.request.CreateScriptRequest;
import com.mingles.metamingle.script.command.application.dto.response.ScriptCommandResponse;
import com.mingles.metamingle.script.query.application.dto.request.FindScriptRequest;
import com.mingles.metamingle.script.query.application.dto.response.ScriptQueryResponse;
import com.mingles.metamingle.script.query.application.service.ScriptQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Tag(name = "AI 생성 대본 Query API")
@RestController
@RequiredArgsConstructor
public class ScriptQueryController {

    private final ScriptQueryService scriptQueryService;

    @Operation(summary = "AI 대본 조회")
    @GetMapping("api/script/{scriptNo}")
    public ResponseEntity<ApiResponse> findScriptByScriptNo(@RequestHeader Map<String, String> requestHeader,
                                                      @PathVariable("scriptNo") Long scriptNo) {

//        String header = requestHeader.get("auth");
//        Long providerId = jwtTokenService.getUserIdFromToken(header);

        FindScriptRequest request = new FindScriptRequest(scriptNo);

        ScriptQueryResponse response = scriptQueryService.findScriptByScriptNo(request);

        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.success("성공적으로 조회되었습니다." , response)
        );
    }
}
