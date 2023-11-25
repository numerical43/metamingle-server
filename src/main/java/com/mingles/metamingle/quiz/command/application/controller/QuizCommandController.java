package com.mingles.metamingle.quiz.command.application.controller;

import com.mingles.metamingle.global.common.ApiResponse;
import com.mingles.metamingle.global.infra.AiInfraService;
import com.mingles.metamingle.quiz.command.application.dto.request.QuizCommandRequest;
import com.mingles.metamingle.quiz.command.application.dto.response.QuizCommandResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class QuizCommandController {

    private final AiInfraService aiInfraService;
    @PostMapping("/scenario/quiz")
    public ResponseEntity<ApiResponse> getQuizFromScenario(@RequestHeader("Authentication")String token,
                                                           @RequestBody QuizCommandRequest request) {

        String text = request.getText();

        QuizCommandResponse response = aiInfraService.getQuizFromScript(text);

        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.success("퀴즈가 성공적으로 저장되었습니다." , response)
        );
    }

}
