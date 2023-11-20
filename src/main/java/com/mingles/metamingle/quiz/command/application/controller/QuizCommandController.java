package com.mingles.metamingle.quiz.command.application.controller;

import com.mingles.metamingle.common.ApiResponse;
import com.mingles.metamingle.quiz.command.application.dto.request.QuizCommandRequest;
import com.mingles.metamingle.quiz.command.application.dto.response.QuizCommandResponse;
import com.mingles.metamingle.scenario.command.infrastructure.service.ScenarioCommandInfraService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class QuizCommandController {

    private final ScenarioCommandInfraService scenarioCommandInfraService;
    @PostMapping("/scenario/quiz")
    public ResponseEntity<ApiResponse> getQuizFromScenario(@RequestBody QuizCommandRequest request) {

        String text = request.getText();

        QuizCommandResponse response = scenarioCommandInfraService.getQuizFromScript(text);

        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.success("퀴즈가 성공적으로 저장되었습니다." , response)
        );
    }

}
